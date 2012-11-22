package controller;

import model.Plane;
import java.util.LinkedList;

public interface scheduler {
	public Plane getNextPlane(LinkedList<Plane> taskList);
	
}
