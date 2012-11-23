package controller;

import java.util.LinkedList;

import model.Plane;

public class EarliestDeadlineFirst implements Algorithm {

	public Plane getNextTask(LinkedList<Plane> planeList) {
		// TODO Auto-generated method stub
		if(planeList.size()>0){
			Plane nextPlane = planeList.get(0);
			for (int i = 0; i < planeList.size(); i++){
				Plane canidatePlane = planeList.get(i);
					if( nextPlane.getLandingDeadline()  > canidatePlane.getLandingDeadline() ){
						nextPlane = canidatePlane;
						}
					}
			return nextPlane;
		}else{
			return null;
			}
	}

}


