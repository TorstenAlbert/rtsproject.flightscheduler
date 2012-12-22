import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;



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
		MainWidget appWidget = new MainWidget(app);
		app.createPlanes();
		app.landPlanes();
		appWidget.refreshTables();

		long endN = System.nanoTime();
		
        double diffN = endN - startN;
        diffN /= 1000 * 1000 * 1000;
        
        System.out.println("Nano seconds: " + diffN);
        
		Timer timer = new Timer();
		TimerTask removePlanesTask = new RemoveLandedPlanes(app,appWidget);
		timer.schedule(removePlanesTask, 1000, 60000);
		
	}



	

}
