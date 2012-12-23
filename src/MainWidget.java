import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MainWidget extends JFrame {
	private static final long serialVersionUID = 1L;
	AircraftScheduler airScheduler;
	JPanel mainPanel;
	JTabbedPane airstripPanel;
	JTable incFlightsTable;
	JTable airstripTable1;
	JTable airstripTable2;
	JTable airstripTable3;
	JButton addFlightButton;
	JMenuItem openFileItem;
	JMenuItem closeItem;
	JCheckBoxMenuItem isActivScheduleItem;
	JLabel LandingAirstrip1Label;
	JLabel LandingAirstrip2Label;
	JLabel LandingAirstrip3Label;
	JLabel LandingEmergencyLabel;
	JLabel statusLabel;
	JSpinner AddingTimeSpinner;
	JSpinner AddingGapSpinner;
	JTextField AddingNameField;
	JButton AddingCreateButton;
	JRadioButton AddingEmergencyTrue;
	JRadioButton AddingEmergencyFalse;
	JFrame AddingFrame;
	JMenuItem helpItem;
	MainWidget originGUI;
	
	MainWidget(AircraftScheduler rScheduler)
	{
		this.originGUI = this;
		this.airScheduler = rScheduler;
		JPanel operationPanel = new JPanel();
		GridLayout addPlanePanelLayout = new GridLayout(9,1);
		operationPanel.setLayout(addPlanePanelLayout);
		operationPanel.setBorder(BorderFactory.createTitledBorder("Operations"));
		addFlightButton = new JButton();
		addFlightButton.setText("ADD Flight");
		addFlightButton.addActionListener(new ButtonListener());
		LandingAirstrip1Label = new JLabel();
		LandingAirstrip2Label = new JLabel();
		LandingAirstrip3Label = new JLabel();
		LandingEmergencyLabel = new JLabel();
		statusLabel = new JLabel("Created at " + new Timestamp(System.currentTimeMillis()).toString());
		operationPanel.add(new JLabel ("Airstip 1"));
		operationPanel.add(LandingAirstrip1Label);
		operationPanel.add(new JLabel ("Airstrip 2"));
		operationPanel.add(LandingAirstrip2Label);
		operationPanel.add(new JLabel ("Airstrip 3"));
		operationPanel.add(LandingAirstrip3Label);
		operationPanel.add(new JLabel ("Emergency Call"));
		operationPanel.add(LandingEmergencyLabel);
		operationPanel.add(addFlightButton);
		JMenuBar widgetMenuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu schedule = new JMenu("Scheduler");
		JMenu help = new JMenu("Help");
		openFileItem = new JMenuItem("Open File");
		openFileItem.addActionListener(new MenuListener());
		closeItem = new JMenuItem("Close");
		closeItem.addActionListener(new MenuListener());
		isActivScheduleItem = new JCheckBoxMenuItem("Activate Schedule");
		isActivScheduleItem.addActionListener(new MenuListener());
		helpItem = new JMenuItem("About");
		helpItem.addActionListener(new MenuListener());
		file.add(openFileItem);
		file.add(closeItem);
		schedule.add(isActivScheduleItem);
		help.add(helpItem);
		widgetMenuBar.add(file);
		widgetMenuBar.add(schedule);
		widgetMenuBar.add(help);
		this.add(widgetMenuBar);
		incFlightsTable = new JTable();
		airstripTable1 = new JTable();
		airstripTable2 = new JTable();
		airstripTable3 = new JTable();
		incFlightsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		incFlightsTable.setColumnSelectionAllowed(true);
		incFlightsTable.setRowSelectionAllowed(false);
		mainPanel = new JPanel(new BorderLayout(2,2));
		airstripPanel = new JTabbedPane();
		JPanel airstripPanel1 = new JPanel();
		JPanel airstripPanel2 = new JPanel();
		JPanel airstripPanel3 = new JPanel();
		airstripPanel1.add(new JScrollPane(airstripTable1));
		airstripPanel2.add(new JScrollPane(airstripTable2));
		airstripPanel3.add(new JScrollPane(airstripTable3));
		airstripPanel.addTab("Airstrip 1", airstripPanel1);
		airstripPanel.addTab("Airstrip 2", airstripPanel2);
		airstripPanel.addTab("Airstrip 3", airstripPanel3);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(d.width,d.height);
		this.setLocation( (d.width - this.getSize().width)/2,
						  (d.height - this.getSize().height)/2);
		this.setResizable(true);
		this.setTitle("Flight Scheduler");
		this.add(widgetMenuBar,BorderLayout.PAGE_START);
		this.add(statusLabel,BorderLayout.PAGE_END);
		this.add(operationPanel,BorderLayout.LINE_START);
		this.add(new JScrollPane(incFlightsTable),BorderLayout.CENTER);
		this.add(airstripPanel,BorderLayout.LINE_END);
		this.setVisible(true);
		this.pack();
	}
	
	public void refreshTables()
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				buildIncFlightsTable(airScheduler.getFlights());
				buildAirstrip1Table(airScheduler.getResourceAirstrips()[0].getPlanes());
				buildAirstrip2Table(airScheduler.getResourceAirstrips()[1].getPlanes());
				buildAirstrip3Table(airScheduler.getResourceAirstrips()[2].getPlanes());
				if(airScheduler.getResourceAirstrips()[0].getPlanes().isEmpty())
				{setTitleLandingAirstrip1Label("No Plane");}else{
				setTitleLandingAirstrip1Label(airScheduler.getResourceAirstrips()[0].getPlanes().get(0).getPlaneName());}
				if(airScheduler.getResourceAirstrips()[1].getPlanes().isEmpty())
				{setTitleLandingAirstrip2Label("No Plane");}else{
				setTitleLandingAirstrip2Label(airScheduler.getResourceAirstrips()[1].getPlanes().get(0).getPlaneName());}
				if(airScheduler.getResourceAirstrips()[2].getPlanes().isEmpty())
				{setTitleLandingAirstrip3Label("No Plane");}else{
				setTitleLandingAirstrip3Label(airScheduler.getResourceAirstrips()[2].getPlanes().get(0).getPlaneName());}
				for (Plane plane: airScheduler.getFlights())
					{if(plane.isEmergencyFlag())
						{LandingEmergencyLabel.setText(plane.getPlaneName());
							break;}
					else{LandingEmergencyLabel.setText("");}
					}
				statusLabel.setText("Last Refresh at " + new Timestamp(System.currentTimeMillis()).toString());
				
			}
			});

	}
	
	public void buildIncFlightsTable(LinkedList<Plane> allplanes)
	{
		incFlightsTable.setModel(new FlightTableModel(allplanes));
		incFlightsTable.setRowSorter(new TableRowSorter<TableModel>(incFlightsTable.getModel()));
	}
	
	public void buildAirstrip1Table(LinkedList<Plane> allplanes)
	{
		airstripTable1.setModel(new FlightTableModel(allplanes));
		airstripTable1.setRowSorter(new TableRowSorter<TableModel>(airstripTable1.getModel()));
	}
	
	public void buildAirstrip2Table(LinkedList<Plane> allplanes)
	{
		airstripTable2.setModel(new FlightTableModel(allplanes));
		airstripTable2.setRowSorter(new TableRowSorter<TableModel>(airstripTable2.getModel()));
	}
	
	public void buildAirstrip3Table(LinkedList<Plane> allplanes)
	{
		airstripTable3.setModel(new FlightTableModel(allplanes));
		airstripTable2.setRowSorter(new TableRowSorter<TableModel>(airstripTable2.getModel()));
	}
	
	public void setTitleLandingAirstrip1Label(String str)
	{
	LandingAirstrip1Label.setText(str);
	}
	
	public void setTitleLandingAirstrip2Label(String str)
	{
	LandingAirstrip2Label.setText(str);
	}
	
	public void setTitleLandingAirstrip3Label(String str)
	{
	LandingAirstrip3Label.setText(str);
	}
	
	public void setTitleLandingEmergencyLabel(String str)
	{
	LandingEmergencyLabel.setText(str);
	}
	
	class FlightTableModel extends AbstractTableModel
	{	
		private static final long serialVersionUID = 1L;
		int row,column=5;
		String[][] columnData;
		String[] dataHeader = new String[5];
		
		FlightTableModel(LinkedList<Plane> allplanes){
			columnData = new String[allplanes.size()][column];
			for(int i=0;i<allplanes.size();i++)
			{
				columnData[i][0] = allplanes.get(i).getScheduledTime().toString().substring(10,19);
				columnData[i][1] = allplanes.get(i).getPlaneName();
				columnData[i][2] = allplanes.get(i).getLandingDeadline().toString().substring(10,19);
				if(allplanes.get(i).isEmergencyFlag())
				{columnData[i][3] = "true";}else{columnData[i][3] = "false";}
				columnData[i][4] = ""+allplanes.get(i).getLandingDuration();
			}
			
			this.row = columnData.length;
			dataHeader[0] = "Scheduled Time";
			dataHeader[1] = "Plane Name";
			dataHeader[2] = "Target Time";
			dataHeader[3] = "Emergency Flag";
			dataHeader[4] = "Landing Duration";
		}
		
		public int getColumnCount() {
			return this.column;
		}

		public String getColumnName(int col)
		{
			return this.dataHeader[col];
		}
		
		
		public int getRowCount() {
			return this.row;
		}

		public Object getValueAt(int x, int y) {
			
			return columnData[x][y];
		}
	}

	class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource()== addFlightButton )
			{
				if(AddingFrame==null)
				AddingFrame = new AddingPlaneWidget();
				AddingFrame.setVisible(true);
			}
			if(evt.getSource()== AddingCreateButton )
			{
					try {
						Date date = (Date)AddingTimeSpinner.getValue();
						airScheduler.addPlane(AddingNameField.getText(), AddingEmergencyTrue.isSelected(),Integer.parseInt(AddingGapSpinner.getValue().toString()), date.getTime());
						airScheduler.landPlanes();	
						originGUI.refreshTables();
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	class MenuListener implements ActionListener{
		
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource()== openFileItem )
			{
			    JFileChooser fc = new JFileChooser();
			    int state = fc.showOpenDialog( null );
			    if ( state == JFileChooser.APPROVE_OPTION )
			    {
			      File file = fc.getSelectedFile();
			      System.out.println(file.getPath());
			      try {
					airScheduler.createPlanes(file.getPath());
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      airScheduler.landPlanes();
			      originGUI.refreshTables();
			    }
			    else
			    	statusLabel.setText("Open File Procedure aborted");
			}
			if(evt.getSource()== closeItem )
			{
				System.exit(0);
			}
			if(evt.getSource()== isActivScheduleItem )
			{
				if(isActivScheduleItem.isSelected())
				{airScheduler.setRemovingActiv(true);}
				else
				{airScheduler.setRemovingActiv(false);}
			}
			if(evt.getSource()== helpItem )
			{
				JOptionPane.showMessageDialog(null, "Information Text");
			}
			
		}

	}
	
	class AddingPlaneWidget extends JFrame{
		private static final long serialVersionUID = 1L;

		AddingPlaneWidget(){
			JPanel addPlanePanel = new JPanel();
			this.getContentPane().add(addPlanePanel);
			GridLayout addPlanePanelLayout = new GridLayout(10,1);
			addPlanePanel.setLayout(addPlanePanelLayout);
			addPlanePanel.setBorder(BorderFactory.createTitledBorder("Create Plane Konfiguration"));
			this.setSize(250,250);
			this.setTitle("Add Plane");
			AddingEmergencyTrue = new JRadioButton("True",false);
			AddingEmergencyFalse = new JRadioButton("False",true);
			ButtonGroup emergencyFlag = new ButtonGroup();
			emergencyFlag.add(AddingEmergencyTrue);
			emergencyFlag.add(AddingEmergencyFalse);
			AddingEmergencyFalse.setToolTipText("deactivate the Emergency Flag");
			AddingEmergencyTrue.setToolTipText("activate the Emergency Flag");
			addPlanePanel.add(new JLabel ("Name"));
			AddingNameField = new JTextField();
			addPlanePanel.add(AddingNameField);
			addPlanePanel.add(new JLabel ("Emergency Flag"));
			addPlanePanel.add(AddingEmergencyTrue);
			addPlanePanel.add(AddingEmergencyFalse);
			addPlanePanel.add(new JLabel ("Gap"));
			SpinnerNumberModel modelGap = new SpinnerNumberModel(1,1,30,1); 
			AddingGapSpinner = new JSpinner(modelGap);
			JSpinner.NumberEditor editorGap = (JSpinner.NumberEditor)AddingGapSpinner.getEditor();
			editorGap.getTextField().setEditable(false);
			addPlanePanel.add(AddingGapSpinner);
			addPlanePanel.add(new JLabel ("Target Time"));
			SpinnerDateModel modelTime = new SpinnerDateModel(); 
			modelTime.setCalendarField(Calendar.MINUTE);
			AddingTimeSpinner = new JSpinner(modelTime);
			AddingTimeSpinner.setEditor(new JSpinner.DateEditor(AddingTimeSpinner, "yyyy-MM-dd HH:mm:ss"));
			JSpinner.DateEditor editor = (JSpinner.DateEditor)AddingTimeSpinner.getEditor();
			editor.getTextField().setEditable(false);
			addPlanePanel.add(AddingTimeSpinner);
			AddingCreateButton = new JButton ("Create");
			AddingCreateButton.addActionListener(new ButtonListener());
			addPlanePanel.add(AddingCreateButton);
			this.setVisible(true);
		}
		
	}
}


