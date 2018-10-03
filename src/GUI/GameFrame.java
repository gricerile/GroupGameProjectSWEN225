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

public class GameFrame extends JFrame implements KeyListener {

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

		// add keyListener
		addKeyListener(this);
		setFocusable(true);

	}

	public GUI getGUI() {
		return gui;
	}

	/////////KeyListener///////////////

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {// left
			// TODO add functionality
			System.out.print("left");
		} else if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {// right
			// TODO
			System.out.print("right");

		} else if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {// up
			// TODO
			System.out.print("up");

		} else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {// down
			// TODO
			System.out.print("down");
		}
		
	}
}
