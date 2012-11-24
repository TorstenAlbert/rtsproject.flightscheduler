
import java.util.LinkedList;

/**
 * Interface for Scheduling Algorithm
 * @author Torsten
 *
 */

public interface AlgorithmAdapter {
	public Plane getNextPlane(LinkedList<Plane> planeList);
}
