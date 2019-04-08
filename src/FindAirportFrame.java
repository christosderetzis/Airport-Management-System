import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FindAirportFrame extends JFrame{
	public JTextField airportField = new JTextField(12);
	public JButton findButton = new JButton("Find");
	public JButton NetworkBtn = new JButton("Visualise Network");
	public JPanel panel = new JPanel(new FlowLayout());
	
	public FindAirportFrame() {
		
		
		panel.add(airportField);
		panel.add(findButton);
		panel.add(NetworkBtn);
		
		this.setContentPane(panel);
		
		this.setTitle("Find Airport");
		this.setSize(260,150);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		findButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Airport airportFound = CentralRegistry.getAirport(airportField.getText());
				if(airportFound != null) {
					AirportPageFrame airport = new AirportPageFrame(airportFound);
				}else {
					JOptionPane.showMessageDialog(new JPanel(), airportField.getText() + " Does not have an airport");
				}
				
				
			}
			
		});
		
		// Button to open the graph
		NetworkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UndirectedGraphFrame graph = new UndirectedGraphFrame();
				
			}
			
		});
		
		
		
	}
}
