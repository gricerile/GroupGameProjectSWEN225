package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;



public class GameFrame extends JFrame {

	private NavigationPanel navigationPanel;



	public GameFrame(String s) {
		super(s);


		//set layout
		setLayout(new BorderLayout());
		
		//components
		navigationPanel = new NavigationPanel();
		
		//add components to content pane
		Container c = getContentPane();

		c.add(navigationPanel, BorderLayout.NORTH);


		//behaviour
	


	}
}
