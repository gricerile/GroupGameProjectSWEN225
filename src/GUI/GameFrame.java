package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private GUI gui;

	GraphicsWindow graphicsWindow;
	EventOutputPanel eventOutputPanel;
	InventoryPanel inventoryPanel;
	NavigationPanel navigationPanel;

	public GameFrame(String s, GUI gui) {
		super(s);
		this.gui = gui;

		// set layout
		setLayout(new BorderLayout());

		// components
		graphicsWindow = new GraphicsWindow(this);
		eventOutputPanel = new EventOutputPanel(this);
		inventoryPanel = new InventoryPanel(this);
		navigationPanel = new NavigationPanel(this);


		// add components to content pane
		Container c = getContentPane();

		c.add(graphicsWindow, BorderLayout.NORTH);
		c.add(eventOutputPanel, BorderLayout.CENTER);
		c.add(inventoryPanel, BorderLayout.CENTER);
		c.add(navigationPanel, BorderLayout.SOUTH);

		// behaviour

	}

}
