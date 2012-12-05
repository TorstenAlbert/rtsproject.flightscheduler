import java.awt.*;
import java.sql.Timestamp;

import javax.swing.*;



public class MainWidget extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	JPanel airstripPanel;
	
	JList incFlighsList;
	JList landedFlightsList;
	JButton addFlightButton;
	
	final String[] data =
		{"1","2","3"};
	
	MainWidget()
	{
		addFlightButton = new JButton();
		addFlightButton.setText("ADD Flight");

		incFlighsList = new JList(data);
		landedFlightsList = new JList(data);
		
		
		mainPanel = new JPanel(new BorderLayout(2,2));
		airstripPanel = new JPanel(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((d.width - this.getSize().width)/2,
				  (d.height - this.getSize().height)/2);
		
		this.setLocation( (d.width - this.getSize().width)/2,
						  (d.height - this.getSize().height)/2);
		this.setResizable(true);
		this.setTitle("Flight Scheduler");
		

		
		this.add( new JLabel(new Timestamp(System.currentTimeMillis()).toString()),BorderLayout.PAGE_START);
		
		this.add(addFlightButton,BorderLayout.PAGE_END);
		this.add(incFlighsList,BorderLayout.LINE_START);
		this.add(landedFlightsList,BorderLayout.LINE_END);
		this.add(airstripPanel);
		
		airstripPanel.add(new JLabel("test"));

		

		this.setVisible(true);
	}

	/**
	 * GUI 
	 */

}
