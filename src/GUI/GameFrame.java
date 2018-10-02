package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

	
	GraphicsWindow graphicsWindow;
	EventOutputPanel eventOutputPanel;
	InventoryPanel inventoryPanel;
	NavigationPanel navigationPanel;

	public GameFrame(String s) {
		super(s);

		// set layout
		//setLayout(new BorderLayout());
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();

		// components
		
		graphicsWindow = new GraphicsWindow();
		eventOutputPanel = new EventOutputPanel();
		inventoryPanel = new InventoryPanel();
		navigationPanel = new NavigationPanel();
		

		// add components to content pane
		Container c = getContentPane();

		////c.add(navigationPanel, BorderLayout.NORTH);
		////c.add(graphicsWindow, BorderLayout.CENTER);
		
		grid.gridx=0;
		grid.gridy=0;
		c.add(graphicsWindow, grid);
		
		grid.gridx=0;
		grid.gridy=1;
		c.add(navigationPanel, grid);

		// behaviour

	}

}
