import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.border.Border;

public class AirportPageFrame extends JFrame{
	private JTextField nameField;
	private JTextField codeField;
	private JTextField cityField;
	private JTextField countryField;
	
	private JPanel mainPanel = new JPanel(new FlowLayout());
	private JPanel flowPanel1 = new JPanel();
	private JPanel flowPanel2 = new JPanel();
	private JPanel flowPanel3 = new JPanel();
	private JPanel flowPanel4 = new JPanel();
	
	private Border aBorder = BorderFactory.createLineBorder(Color.BLACK);;
	
	private HashSet<String> listCompanies;
	private JTextField destination;
	private JButton FindFlights;
	private JTextArea directFlights;
	private JTextArea indirectFlights;
	private JButton backbtn;
	
	public AirportPageFrame(Airport anAirport) {
		// 1st row
		nameField = new JTextField(anAirport.getName(), 10);
		codeField = new JTextField(anAirport.getCoded_name(), 10);
		cityField = new JTextField(anAirport.getCity(), 10);
		countryField = new JTextField(anAirport.getCountry(), 10);
		
		
		listCompanies = new HashSet<String>(anAirport.getCompanies());
		TreeSet<String> comp = new TreeSet<String>(listCompanies);
		
		JList<String> compList = new JList<>();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String c: comp) {
			listModel.addElement(c);
		}
		compList.setModel(listModel);
//		JScrollPane j = new JScrollPane(compList);
		compList.setPreferredSize(new Dimension(100,80));
		
		flowPanel1.add(nameField);
		flowPanel1.add(codeField);
		flowPanel1.add(cityField);
		flowPanel1.add(countryField);
		flowPanel1.add(compList);
		flowPanel1.setBorder(aBorder);
		
		mainPanel.add(flowPanel1);
		
		// 2nd row
		destination = new JTextField(10);
		FindFlights = new JButton("Find Flights");
		
		FindFlights.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Airport destinationAirport = CentralRegistry.getAirport(destination.getText());
				if(destinationAirport != null && (destinationAirport.getCity() != anAirport.getCity())) {
					directFlights.setText(CentralRegistry.getDirectFlightsDetails(anAirport, destinationAirport));
					indirectFlights.setText(CentralRegistry.getIndirectFlightsDetails(anAirport, destinationAirport));
				}else {
					JOptionPane.showMessageDialog(new JPanel(), "Arrival and departure city cannot be the same!");
				}
				
				writeFile(anAirport, destinationAirport);
			}
			
		});
		
		flowPanel2.add(destination);
		flowPanel2.add(FindFlights);
		
		mainPanel.add(flowPanel2);
		 
		// 3rd row
		directFlights = new JTextArea(15,30);
		indirectFlights = new JTextArea(15,30);
		
		flowPanel3.add(directFlights);
		flowPanel3.add(indirectFlights);
		
		mainPanel.add(flowPanel3);
		
		// 4th row
		backbtn = new JButton("Back To Search Screen");
		
		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				dispose();
			}
			
		});
		
		mainPanel.add(backbtn);
	
		
		this.setContentPane(mainPanel);
		
		this.setTitle("Airport Page");
		this.setVisible(true);
		this.setSize(750, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void writeFile(Airport anAirport, Airport destinationAirport) {
		String fileName = cityField.getText() + "To" + destination.getText() + ".txt";
		
		try
		{
			File f = new File(fileName);
			FileWriter writer = new FileWriter(f);
			
			writer.write("CITY: " + anAirport.getCity() + ", " + anAirport.getCountry());
			writer.write(System.lineSeparator());
			writer.write("AIRPORT: " + anAirport.getName() + " (" + anAirport.getCoded_name() + ")");
			writer.write(System.lineSeparator());
			writer.write(System.lineSeparator());
			writer.write("DESTINATION: " + destination.getText());
			writer.write(System.lineSeparator());
			writer.write(System.lineSeparator());
			writer.write(CentralRegistry.getDirectFlightsDetails(anAirport, destinationAirport));
			writer.write(System.lineSeparator());
			writer.write(CentralRegistry.getIndirectFlightsDetails(anAirport, destinationAirport));
			
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
