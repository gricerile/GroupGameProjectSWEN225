package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GraphicsWindow extends JPanel implements MouseListener {

  //private BufferedImage image;

  private int dimensionHeight = 400;
  private int dimensionWidth = 650;

  private GameFrame frame;

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

    // set dimensions
    Dimension dimension = getPreferredSize();
    dimension.width = dimensionWidth;
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);

    // set border
    setBorder(BorderFactory.createEtchedBorder());

    // add mouseListeners
    addMouseListener(this);
  }

 // public void setImage(BufferedImage image) {
 //   this.image = image;
 // }

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
      this.frame.getGui().getMain().getRenderer().draw(g);
    }

  }

  //////////////// Mouse Listeners/////////////////////////////////

  @Override
  public void mouseReleased(MouseEvent e) {
    frame.getGui().getMain().clickedScreen(e.getX(), e.getY());
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
