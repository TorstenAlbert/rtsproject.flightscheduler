import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



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
		
		long endN = System.nanoTime();
		
        double diffN = endN - startN;
        diffN /= 1000 * 1000 * 1000;
        
        System.out.println("Duration for creating Application\nNano seconds: " + diffN);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task1 = new RemoveLandedPlanes(app,appWidget);
        Runnable task2 = new CreateRandomPlane(app,appWidget);
        executor.scheduleAtFixedRate(task1,0,10, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(task2,5,3, TimeUnit.MINUTES);
	}



	

}
