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
		setLayout(new BorderLayout());

		// components
		graphicsWindow = new GraphicsWindow();
		eventOutputPanel = new EventOutputPanel();
		inventoryPanel = new InventoryPanel();
		navigationPanel = new NavigationPanel();


		// add components to content pane
		Container c = getContentPane();

		c.add(graphicsWindow, BorderLayout.NORTH);
		c.add(eventOutputPanel, BorderLayout.CENTER);
		c.add(inventoryPanel, BorderLayout.CENTER);
		c.add(navigationPanel, BorderLayout.SOUTH);

		// behaviour

	}

}
