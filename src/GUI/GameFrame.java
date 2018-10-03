package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import GUI.GUI.moveDirection;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements KeyListener {

  private GUI gui;

  GraphicsWindow graphicsWindow;
  EventOutputPanel eventOutputPanel;
  InventoryPanel inventoryPanel;
  NavigationPanel navigationPanel;

  /**
   * GameFrame is an exention of JFrame and contains all JPanel components for the
   * GUI.
   *
   * @param s
   *          String name for the GameFrame
   * @param gui
   *          reference to the GUI instance which contains the instance of
   *          GameFrame
   */
  public GameFrame(String s, GUI gui) {
    super(s);
    this.gui = gui;

    // components
    graphicsWindow = new GraphicsWindow(this);
    eventOutputPanel = new EventOutputPanel(this);
    inventoryPanel = new InventoryPanel(this);
    navigationPanel = new NavigationPanel(this);

    // add components to content pane
    Container c = getContentPane();

    // set layout
    setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

    c.add(graphicsWindow);
    c.add(eventOutputPanel);
    c.add(inventoryPanel);
    c.add(navigationPanel);

    // add keyListener
    addKeyListener(this);
    setFocusable(true);

  }

  /**
   * returns the instance of GUI which contains this classes instance, used by
   * JPanel classes to reference back through to the GUI.
   *
   * @return instance of GUI
   */
  public GUI getGui() {
    return gui;
  }

  ///////// KeyListener///////////////

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {

    if (e.getKeyCode() == 37 || e.getKeyCode() == 65) { // left
      //frame.getGui().getMain().movePlayer(moveDirection.left);
    } else if (e.getKeyCode() == 39 || e.getKeyCode() == 68) { // right
        //frame.getGui().getMain().movePlayer(moveDirection.right);
    } else if (e.getKeyCode() == 38 || e.getKeyCode() == 87) { // up
        //frame.getGui().getMain().movePlayer(moveDirection.up);
    } else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) { // down
        //frame.getGui().getMain().movePlayer(moveDirection.down);
    }

  }
}
