

import java.util.LinkedList;

public class EarliestDeadlineFirst implements Algorithm {

	public Plane getNextTask(LinkedList<Plane> planeList) {
		// TODO Auto-generated method stub
		if(planeList.size()>0){
			Plane nextPlane = planeList.get(0);
			for (int i = 0; i < planeList.size(); i++){
				Plane canidatePlane = planeList.get(i);
					if( nextPlane.getLandingDeadline().before(canidatePlane.getLandingDeadline())){
						nextPlane = canidatePlane;
						}
					}
			return nextPlane;
		}else{
			return null;
			}
	}

}


