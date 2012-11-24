

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Timer;

public class Airstrip {
	private int	airstripCounter	= 0;
	//private int length; in upcoming version
	
	public  int getAirstripCounter() {
		return airstripCounter;
	}

	private LinkedList<Plane> landedPlanes;
	private boolean isBusy;
	
	public boolean isBusy() {
		return isBusy;
	}
	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
	
	public void occupyAirstrip(int duration)
	{long waitingTime= 60000*duration;
		System.out.println("---BeginWaitAirstrip#:" +this.getAirstripCounter() +"---");


		//	timer.wait(waitingTime);
		System.out.println( "!!!Throws InterruptedException");
		this.isBusy = false;
		System.out.println( "Current Time: " + new Timestamp(System.currentTimeMillis()).toString());
		System.out.println("---EndWaitAirstrip#:" +this.getAirstripCounter() +"---");
	}
	
	public void addLandedPlane(Plane landedPlane) {
		landedPlanes.add(landedPlane);
	}
	public LinkedList<Plane> getLandedPlanes() {
		return landedPlanes;
	}
	
	public Airstrip() {
		airstripCounter++;
		landedPlanes= new LinkedList<Plane>();
		isBusy=false;}
	
/*	public Airstrip(int rlength) {
		planeCounter++;
		this.length = rlength}  upcoming version*/


}
