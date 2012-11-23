

import java.sql.Timestamp;

/**
 * The plane is  in respect to the Scheduling one Task
 * @author Torsten
 *
 */

public class Plane {

	private static int	planeCounter	= 0;
	
	private boolean emergencyFlag;
	private Timestamp landingDeadline;
	private int landingDuration;
	private String planeName;
	
	
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
	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}
	public static int getPlaneCounter() {
		return planeCounter;
	}
	
	public Plane(String name, boolean emergency, int duration, long deadline){
		planeCounter++;
		this.emergencyFlag = emergency;
		this.landingDeadline = new Timestamp(deadline);
		this.landingDuration = duration;
		this.planeName = name;
		
	}
}
