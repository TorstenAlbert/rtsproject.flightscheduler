package controller;

import java.util.LinkedList;

import model.Plane;

public class RateMonotonicScheduling implements Algorithm {

	public Plane getNextTask(LinkedList<Plane> planeList) {
		// TODO Auto-generated method stub
		if(planeList.size()>0){
			Plane nextPlane = planeList.get(0);
			for (int i = 0; i < planeList.size(); i++){
				Plane canidatePlane = planeList.get(i);
				if((nextPlane.isEmergencyFlag() == false)&&(canidatePlane.isEmergencyFlag() == true)){
					nextPlane = canidatePlane;
				}
			}
			return nextPlane;
			
		}else{
			return null;
		}
	}

}
