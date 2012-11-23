package model;

import java.util.LinkedList;

public class Airstrip {
	private static int	airstripCounter	= 0;
	//private int length; in upcoming version
	
	private LinkedList<Plane> landedPlanes;
	private boolean isBusy;
	
	public boolean isBusy() {
		return isBusy;
	}
	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
	
	public void addLandedPlane(Plane landedPlane) {
		landedPlanes.add(landedPlane);
	}
	public LinkedList<Plane> getLandedPlanes() {
		return landedPlanes;
	}
	
	public Airstrip() {
		airstripCounter++;}
	
/*	public Airstrip(int rlength) {
		planeCounter++;
		this.length = rlength}  upcoming version*/


}
