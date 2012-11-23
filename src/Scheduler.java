

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

public class Scheduler {
	
	LinkedList<Plane> flights=null;
	
	
	public LinkedList<Plane> getFlights() {
		return flights;
	}
	
	public void addPlane(Plane incPlane) {
		flights.add(incPlane);
	}
		
		
	public void createPlane()
	{
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{Random r = new Random();
			public void run()
			{
				Plane incomingPlane = new Plane("a",false,r.nextInt(10)+1,(System.currentTimeMillis()+(r.nextInt(30)*60000)));
				System.out.println("Plane Landing Deadline: " +incomingPlane.getLandingDeadline().toString() + "\nPlane Landing Duration: " + incomingPlane.getLandingDuration() + "\n");	
				System.out.println( "Current Time: " + new Timestamp(System.currentTimeMillis()).toString());
			}	
		}, 1000,60000*3);	
	}

		
	
}
