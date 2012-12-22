import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Collections;

public class Airstrip {
	private int	airstripCounter	= 0;
	//private int length; in upcoming version
	
	public  int getAirstripCounter() {
		return airstripCounter;
	}

	private LinkedList<Plane> landedPlanes;
	private LinkedList<Plane> RunWayPlanes = new LinkedList<Plane>();
	
	public boolean isBusy(Plane newPlane) {
		
		Timestamp tempPlaneTargetTime;
		Timestamp newPlaneTargetTime = newPlane.getScheduledTime();
		
		Collections.sort(RunWayPlanes, Plane.PlaneByTimeComparator);
		
		for(int i=0; i < RunWayPlanes.size(); i++)
		{
			//Get plane landing time and add duration of landing/taking off to check if line is available
			tempPlaneTargetTime = new Timestamp(RunWayPlanes.get(i).getScheduledTime().getTime() 
					                             + RunWayPlanes.get(i).getLandingDuration()*60*1000);
			
			if( tempPlaneTargetTime.compareTo( newPlaneTargetTime ) != -1  )
			 { return true; }			
		}
		
		return false;
	}
	

	public long getGapBetweenNewAndLast( Plane newPlane )
	{
		if(RunWayPlanes.size() == 0) return 0;
		
		Collections.sort(RunWayPlanes, Plane.PlaneByTimeComparator);
		
		Timestamp lastPlaneTime = new Timestamp(RunWayPlanes.get(RunWayPlanes.size()-1).getScheduledTime().getTime() 
                + RunWayPlanes.get(RunWayPlanes.size()-1).getLandingDuration()*60*1000);
						
		if(lastPlaneTime.getTime() - newPlane.getScheduledTime().getTime() > 0)
		 return Math.abs(lastPlaneTime.getTime() - newPlane.getScheduledTime().getTime());
		
		else return 0;
	}
	


	
	public void addPlane(Plane Plane) {
		RunWayPlanes.add(Plane);
	}
	
	public void removeAllPlane()
	{
		RunWayPlanes.clear();
	}
	
	public void removeLastPlane() {
		
		RunWayPlanes.remove(RunWayPlanes.size() - 1);
	}
	
	public void removePlane(Plane rmvPlane) {
		RunWayPlanes.remove(rmvPlane);
		landedPlanes.add(rmvPlane);
	}
	
	public boolean isLastPlaneEmergency()
	{
		if( RunWayPlanes.size() == 0) return false; 
			
		return RunWayPlanes.get(RunWayPlanes.size() - 1).isEmergencyFlag();		
	}
	
	public Plane getLastPlane()
	{
		if( RunWayPlanes.size() == 0) return null; 
		
		return RunWayPlanes.get(RunWayPlanes.size() - 1);		
	}
	
	public LinkedList<Plane> getPlanes() {
		return RunWayPlanes;
	}
	
	public LinkedList<Plane> getLandedPlanes() {
		return landedPlanes;
	}
	
	public Airstrip() {
		airstripCounter++;
		landedPlanes= new LinkedList<Plane>();
	}
	
	public void addPlaneToAirStrip( Plane PlaneToAdd )
	{
		Collections.sort(RunWayPlanes, Plane.PlaneByTimeComparator);
		RunWayPlanes.add(PlaneToAdd);
	}
	
/*	public Airstrip(int rlength) {
		planeCounter++;
		this.length = rlength}  upcoming version*/


}
