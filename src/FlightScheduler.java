

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Scheduler is calling scheduling algorithm and should preform a landing sequence
 * @author Torsten
 *
 */

public class FlightScheduler {
private Timer timer = new Timer();	
private TimerTask createPlanesTask;
private TimerTask landPlanesTask;
public LinkedList<Plane> flights;
private AlgorithmAdapter schedulingAdapter;
public Airstrip resourceAirstrips[] = new Airstrip[3];

public FlightScheduler(){
	createPlanesTask= new CreatePlanes();
	landPlanesTask= new LandPlanes();
	flights = new LinkedList<Plane>();
	timer = new Timer();
	schedulingAdapter  = new EarliestDeadlineFirstDelegate();
	this.setDelegate(schedulingAdapter);
	
	for (int i = 0; i < resourceAirstrips.length; i++) {
		 resourceAirstrips[i] =new Airstrip();
	}
	
	
}

public void setDelegate(AlgorithmAdapter scheduling) {
	this.schedulingAdapter = scheduling;
}

public Plane preformGetNextPlane()
{return schedulingAdapter.getNextPlane(flights);}
	

	
public LinkedList<Plane> getFlights() {
		return flights;
	}
	
public void removePlane(Plane rmvPlane) {
		flights.remove(rmvPlane);
	}
		
		
public void createPlane(){

		timer.schedule(createPlanesTask,1000,60000*3);	
}

public void landPlane(){
	timer.schedule(landPlanesTask,2000,60000);
	
}

class CreatePlanes extends TimerTask{
	private Random rndgen = new Random();

	@Override
	public void run() 
	{

		rndgen.setSeed(System.currentTimeMillis());
		int rndnbr = rndgen.nextInt(30)+1;
		// TODO Auto-generated method stub
			
			Plane incomingPlane = new Plane("a",false,rndnbr,(System.currentTimeMillis()+((rndnbr)*60000)));
			if((Plane.getPlaneCounter() % 3) == 0)
				incomingPlane.setEmergencyFlag(true);
			System.out.println( "\n");
			System.out.println( "---------------------------------BeginCreatePlanes-------------------------------------");
			System.out.println("Plane Landing Deadline: " +incomingPlane.getLandingDeadline().toString() + "\nPlane Landing Duration: " + incomingPlane.getLandingDuration() + "\n");
			System.out.println("Plane Number: " + Plane.getPlaneCounter() + "\nPlane EmergencyFlag :" + incomingPlane.isEmergencyFlag() + "\n");
			System.out.println( "Current Time: " + new Timestamp(System.currentTimeMillis()).toString());
			System.out.println( "---------------------------------EndCreatePlanes---------------------------------------");
			
			flights.add(incomingPlane);
		}	
	}
	
class LandPlanes extends TimerTask{
	Airstrip landingAirstrip;
	Plane landingPlane;
	boolean emergencyactiv=false;

	@Override
	public void run() 
	{
		/* check flights are non emegerncy and delegate correct algorithm
		 * for each airstrip check if it is not busy otherwise wait one second
		 * choose one airstrip and bring plane to land by
		 * removing plane from flights list and adding to the airstrip
		 */
		for(Plane flight:flights)
		{
			if(flight.isEmergencyFlag())
			{
				emergencyactiv=true;
				FlightScheduler.this.setDelegate(new EmergencyMonotonicDelegate());
			}
		}
		
		do{
			for(Airstrip airstrip:resourceAirstrips)
			{
				if(airstrip.isBusy()==false)
				{
					landingAirstrip = airstrip;
				}
				else
				{
					landingAirstrip = null;
				}
		
			}
			while(landingAirstrip == null)
			{
				try {
						this.wait(60000);
					} catch (InterruptedException e) {
				// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}while(landingAirstrip == null);
		
		landingPlane = FlightScheduler.this.preformGetNextPlane();
		flights.remove(landingPlane);
		landingAirstrip.addLandedPlane(landingPlane);
		landingAirstrip.setBusy(true);
		//occupy the airstrip!

			System.out.println( "\n");
			System.out.println( "-----------------------------------BeginLandPlanes-------------------------------------");
			System.out.println( "Emergency Flag: " + emergencyactiv);
			if(landingPlane != null)
				System.out.println( "Landing Plane: " + landingPlane.getLandingDeadline().toString());
			else
				System.out.println( "Landing Plane: NULL\n");
			System.out.println( "Landing Airstrip Counter: " + landingAirstrip.getAirstripCounter());
			System.out.println( "Current Time: " + new Timestamp(System.currentTimeMillis()).toString());
			System.out.println( "-----------------------------------EndLandPlanes---------------------------------------");
			FlightScheduler.this.setDelegate(new EarliestDeadlineFirstDelegate());
		}	
	}


}

