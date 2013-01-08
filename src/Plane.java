

import java.sql.Timestamp;
import java.util.Comparator;



public class Plane {

	private static  int	planeCounter	= 0;
	private   int	planeNumber;


	private boolean emergencyFlag;
	private Timestamp landingDeadline;
	private Timestamp landingScheduledTime;
	private int landingDuration;
	private String planeName;
	private int airstripNumber;
	
	public int getPlaneNumber() {
		return planeNumber;
	}
	
	public int getAirstripNumber() {
		return airstripNumber;
	}
	public void setAirstripNumber(int airstripNumber) {
		this.airstripNumber = airstripNumber;
	}
	public boolean isEmergencyFlag() {
		return emergencyFlag;
	}
	public void setEmergencyFlag(boolean emergencyFlag) {
		this.emergencyFlag = emergencyFlag;
	}
	public Timestamp getLandingDeadline() {
		return landingDeadline;
	}
	public void setLandingDeadline(long landingDeadline) {
		this.landingDeadline.setTime(landingDeadline);
	}
	public int getLandingDuration() {
		return landingDuration;
	}
	public void setLandingDuration(int landingDuration) {
		this.landingDuration = landingDuration;
	}
	public String getPlaneName() {
		return planeName;
	}
	public Timestamp getScheduledTime(){
		return landingScheduledTime;		
	}
	public void setScheduledTime(Timestamp newScheduledTime){
		this.landingScheduledTime = newScheduledTime;		
	}
	
	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}
	public static int getPlaneCounter() {
		return planeCounter;
	}
	

	
	public Plane(String name, boolean emergency, int duration, long deadline){
		planeCounter++;
		this.planeNumber = planeCounter;
		this.emergencyFlag = emergency;
		this.landingDeadline = new Timestamp(deadline);
		this.landingScheduledTime = new Timestamp(deadline);
		this.landingDuration = duration;
		this.planeName = name;	
		this.airstripNumber = 0;
	}
	
	public static Comparator<Plane> PlaneByTimeComparator = new Comparator<Plane>() 
    {
		public int compare(Plane plane0, Plane plane1) 
		{	
			int EmergencyWeight = 1;	
			boolean pl0IsLand = false;
			boolean pl1IsLand = false;
			
			if( new Timestamp(plane0.getScheduledTime().getTime()).before( new Timestamp( System.currentTimeMillis() ) ) 
		           && new Timestamp(plane0.getScheduledTime().getTime() + plane0.getLandingDuration() *
				       60000).after(new Timestamp( System.currentTimeMillis() ) ) )
			{ pl0IsLand =  true; }
			
			if( new Timestamp(plane1.getScheduledTime().getTime()).before( new Timestamp( System.currentTimeMillis() ) ) 
			           && new Timestamp(plane1.getScheduledTime().getTime() + plane1.getLandingDuration() *
					       60000).after(new Timestamp( System.currentTimeMillis() ) ) )
			{ pl1IsLand =  true; }
			
			if( pl0IsLand && ! pl1IsLand )
     		 return plane0.landingScheduledTime.compareTo(new Timestamp(plane1.landingScheduledTime.getTime() + EmergencyWeight*plane1.landingDuration*60*1000 ));
			
			if( !pl0IsLand && pl1IsLand )
			 return new Timestamp(plane0.landingScheduledTime.getTime() + EmergencyWeight*plane0.landingDuration*60*1000 ).compareTo(plane1.landingScheduledTime);
			
			if( pl0IsLand && pl1IsLand )
			 return plane0.landingScheduledTime.compareTo(plane1.landingScheduledTime);
				
			if( plane0.isEmergencyFlag() && ! plane1.isEmergencyFlag() )
			 return plane0.landingScheduledTime.compareTo(new Timestamp(plane1.landingScheduledTime.getTime() + EmergencyWeight*plane1.landingDuration*60*1000 ));
			
			if( ! plane0.isEmergencyFlag() && plane1.isEmergencyFlag() )
			 return new Timestamp(plane0.landingScheduledTime.getTime() + EmergencyWeight*plane0.landingDuration*60*1000 ).compareTo(plane1.landingScheduledTime);
			
			return plane0.landingScheduledTime.compareTo(plane1.landingScheduledTime);
		}
	};


}
