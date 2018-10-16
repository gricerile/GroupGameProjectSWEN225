package GUI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import GUI.GUI.moveDirection;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements KeyListener, ActionListener {

	private GUI gui;

	private GraphicsWindow graphicsWindow;
	private EventOutputPanel eventOutputPanel;
	private InventoryPanel inventoryPanel;
	private NavigationPanel navigationPanel;

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem loadMap;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem quit;

	/**
	 * GameFrame is an exention of JFrame and contains JPanel components for the
	 * GUI.
	 *
	 * @param s
	 *            String name for the GameFrame
	 * @param gui
	 *            reference to the GUI instance which contains the instance of
	 *            GameFrame
	 */
	public GameFrame(String gameName, GUI gui) {
		super(gameName);
		this.gui = gui;

		// Initialize and setup menu

		loadMap = new JMenuItem("Load Map");
		save = new JMenuItem("Save Game");
		load = new JMenuItem("Load Game");
		quit = new JMenuItem("Quit");

		save.addActionListener(this);
		load.addActionListener(this);
		quit.addActionListener(this);
		loadMap.addActionListener(this);

		menu = new JMenu("Menu");
		menu.add(loadMap);
		menu.add(save);
		menu.add(load);
		menu.add(quit);

		menuBar = new JMenuBar();
		menuBar.add(menu);

		setJMenuBar(menuBar);

		// Initialize JPanel components
		graphicsWindow = new GraphicsWindow(this.gui);
		eventOutputPanel = new EventOutputPanel();
		inventoryPanel = new InventoryPanel();
		navigationPanel = new NavigationPanel(this.gui);

		// add components to content pane
		Container c = getContentPane();
		c.add(navigationPanel);
		c.add(graphicsWindow);
		c.add(eventOutputPanel);
		c.add(inventoryPanel);

		// set layout
		setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		// add keyListener
		addKeyListener(this);

		setFocusable(true);
		this.pack();
	}

	public GraphicsWindow getGraphicsWindow() {
		return this.graphicsWindow;
	}

	public EventOutputPanel getEventOutputPanel() {
		return this.eventOutputPanel;
	}

	public InventoryPanel getInventoryPanel() {
		return this.inventoryPanel;
	}

	public NavigationPanel getNavigationPanel() {
		return this.navigationPanel;
	}

	///////// KeyListener///////////////

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 39 || e.getKeyCode() == 68) { // left
			gui.getMain().movePlayer(moveDirection.downLeft);
		} else if (e.getKeyCode() == 37 || e.getKeyCode() == 65) { // right
			gui.getMain().movePlayer(moveDirection.upRight);
		} else if (e.getKeyCode() == 38 || e.getKeyCode() == 87) { // up
			gui.getMain().movePlayer(moveDirection.upLeft);
		} else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) { // down
			gui.getMain().movePlayer(moveDirection.downRight);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			this.gui.getMain().saveGame();
		} else if (e.getSource() == load) {
			this.gui.getMain().loadGame();
		} else if (e.getSource() == quit) {
			this.gui.getMain().quitGame();
		} else if(e.getSource() == loadMap) {
			this.gui.loadMap();
		}
	}
}
