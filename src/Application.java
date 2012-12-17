import java.io.IOException;
import java.text.ParseException;


public class Application {

	/**
	 * Call the Application
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 */
	public static void main(String[] args) throws IOException, ParseException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
        long startN = System.nanoTime();
		
		AircraftScheduler app = new AircraftScheduler();
		app.createPlanes();
		app.landPlanes();

		long endN = System.nanoTime();
		
        double diffN = endN - startN;
        diffN /= 1000 * 1000 * 1000;
        
        System.out.println("Nano seconds: " + diffN);
		
        new MainWidget();
	}

}
