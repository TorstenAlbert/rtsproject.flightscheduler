
import java.util.LinkedList;

/**
 * Interface for Scheduling Algorithm
 * @author Torsten
 *
 */

public interface Algorithm {
	public Plane getNextTask(LinkedList<Plane> planeList);
}
