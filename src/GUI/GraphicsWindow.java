package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
public class GraphicsWindow extends JPanel implements MouseListener {

  private int dimensionHeight = 600;
  private int dimensionWidth = 600;

  private GameFrame frame;

  private JPopupMenu popUp;

  private JMenuItem pickupItem;
  private JMenuItem dropItem;
  private JMenuItem openDoor;
  private JMenuItem unlockDoor;

  /**
   * GraphicsWindow is the JPanel which the game graphics will be painted on.
   * GraphicsWindow also implements mouseListener to track click events.
   *
   * @param frame
   *          instance of GameFrame which this class is contained in, encase class
   *          needs to reference back.
   */
  public GraphicsWindow(GameFrame frame) {
    this.frame = frame;
    this.popUp = new JPopupMenu();

    // set dimensions
    Dimension dimension = getPreferredSize();
    dimension.width = dimensionWidth;
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);
    // setMaximumSize(dimension);
    // setMinimumSize(new Dimension(100, 100));

    // set border
    //setBorder(BorderFactory.createEtchedBorder());
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    // popupMenu elements
    pickupItem = new JMenuItem("Pickup Item");
    dropItem = new JMenuItem("Drop Item");
    openDoor = new JMenuItem("Open Door");
    unlockDoor = new JMenuItem("Unlock Door");

    this.popUp.add(pickupItem);
    this.popUp.add(dropItem);
    this.popUp.add(openDoor);
    this.popUp.add(unlockDoor);

    // add mouseListeners
    addMouseListener(this);
  }

  public void redraw() {
    repaint();
  }

  /**
   * Paint component of GraphicsWindow, allows window to be painted on and is
   * refreshed by a repaint call on this class.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);

    if (this.frame.getGui().getMain().getRenderer() == null) {

      g.setColor(Color.BLACK);
      g.drawString("NO IMAGE", this.getWidth() / 2, this.getHeight() / 2);

    } else {

      this.frame.getGui().getMain().getRenderer().draw(g, getWidth(), getHeight());
    }

  }

  //////////////// Mouse Listeners/////////////////////////////////

  @Override
  public void mouseReleased(MouseEvent e) {

    if (e.getButton() == 1) {
      frame.getGui().getMain().clickedScreen(e.getX(), e.getY());
    } else if (e.getButton() == 3) {
      // getType of tile clicked on and show different pop ups depending
      this.popUp.show(this, e.getX(), e.getY());
    }

  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }
}
