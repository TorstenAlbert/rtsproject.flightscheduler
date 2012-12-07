import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

public class Airstrip {
	private int	airstripCounter	= 0;
	//private int length; in upcoming version
	
	public  int getAirstripCounter() {
		return airstripCounter;
	}

	private LinkedList<Plane> landedPlanes;
	private List<Plane> RunWayPlanes = new ArrayList<Plane>();
	
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
	

	public void addLandedPlane(Plane landedPlane) {
		landedPlanes.add(landedPlane);
	}
	
	public void addPlane(Plane Plane) {
		RunWayPlanes.add(Plane);
	}
	
	public void removeLastPlane() {
		
		RunWayPlanes.remove(RunWayPlanes.size() - 1);
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
	
	public List<Plane> getPlanes() {
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
