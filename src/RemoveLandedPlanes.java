import java.sql.Timestamp;
import java.util.TimerTask;


	class RemoveLandedPlanes extends TimerTask{
		private AircraftScheduler app;
		private MainWidget appWidget;
		
		RemoveLandedPlanes(AircraftScheduler rapp,MainWidget rappWidget)
		{
			this.app = rapp;
			this.appWidget = rappWidget;
		}
		

		public void run() {
			long startN = System.nanoTime();
			System.out.println("---Task-Begin---");
			if(app.isRemovingActiv()){
				Timestamp cmpTime = new Timestamp(System.currentTimeMillis());	
				app.cmpFlightsWithCurrentTime(cmpTime);
				appWidget.refreshTables();
			}else{System.out.println("Nothing to do");}
			
			long endN = System.nanoTime();
			
	        double diffN = endN - startN;
	        diffN /= 1000 * 1000 * 1000;
	        
	        System.out.println("Nano seconds: " + diffN);
	        System.out.println("---Task-End---");
		}
		
		

	}