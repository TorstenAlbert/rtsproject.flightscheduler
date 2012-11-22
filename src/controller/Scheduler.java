package controller;

import model.Plane;
import java.util.LinkedList;

public interface Scheduler {
	public Plane getNextPlane(LinkedList<Plane> taskList);
	
}
