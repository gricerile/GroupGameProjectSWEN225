package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * GameFrame extends JFrame and contains JPanel components for the GUI.
 *
 * @author millerdani1
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements ActionListener {

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
   * Initializes elements for menu, and JPanel elements contained within
   * GameFrame, sets layout and ActionListener/KeyListener.
   *
   * @param gameName
   *          Name of frame to be set in the superclass (JFrame) of GameFrame.
   * @param gui
   *          reference to the GUI instance which contains the instance of
   *          GameFrame
   */
  public GameFrame(String gameName, GUI gui) {
    super(gameName);
    this.gui = gui;

    // Initialize and setup menu

    loadMap = new JMenuItem("Load New Game");
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

    setFocusable(true);
    this.pack();
  }

  /**
   * getter method for graphics window contained within GameFrame.
   *
   * @return GraphicsWindoe contained within GameFrame
   */
  public GraphicsWindow getGraphicsWindow() {
    return this.graphicsWindow;
  }

  /**
   * getter method for EventOutputPanel contained within GameFrame.
   *
   * @return EventOutputPanel contained within GameFrame
   */
  public EventOutputPanel getEventOutputPanel() {
    return this.eventOutputPanel;
  }

  /**
   * getter method for InventoryPanel contained within GameFrame.
   *
   * @return InventoryPanel contained within GameFrame
   */
  public InventoryPanel getInventoryPanel() {
    return this.inventoryPanel;
  }

  /**
   * getter method for NavigationPanel contained within GameFrame.
   *
   * @return NavigationPanel contained within GameFrame
   */
  public NavigationPanel getNavigationPanel() {
    return this.navigationPanel;
  }

  ///////// ActionListener///////////////

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == save) {
      this.gui.getMain().saveGame();
    } else if (e.getSource() == load) {
      this.gui.getMain().loadGame();
    } else if (e.getSource() == quit) {
      this.gui.getMain().quitGame();
    } else if (e.getSource() == loadMap) {
      this.gui.loadMap();
    }
  }
}
