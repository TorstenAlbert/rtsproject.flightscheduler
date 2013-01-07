import java.sql.Timestamp;


public class CreateRandomPlane implements Runnable{
	private AircraftScheduler app;
	private MainWidget appWidget;
		
	CreateRandomPlane(AircraftScheduler rapp,MainWidget rappWidget)
		{
			this.app = rapp;
			this.appWidget = rappWidget;
		}
	
	public void run() {
		long startN = System.nanoTime();
		System.out.println("---Task-Begin---");
		System.out.println("------Add-------");
		if(app.isCreatingActiv())
		{	
			app.createPlanes();
			app.landPlanes();
			appWidget.refreshTables();
		}
		else{System.out.println("Nothing to do");}
		
		long endN = System.nanoTime();
        double diffN = endN - startN;
        diffN /= 1000 * 1000 * 1000;
        System.out.println("Nano seconds: " + diffN);
        System.out.println("---Task-End---");
		
	} 

}
