import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 * @author chenkelv
 *
 */
public class Renderer {

  public float SF = 1.2f;

	/**
	 * @author chenkelv
	 *
	 */
	public enum direction{
		NORTH, EAST, SOUTH, WEST;
	}

	/**
	 * render out the frame when a movement is made
	 * @return boolean
	 */
	public boolean render() {
		return false;
	}

	public void scale() {
	  // when the player moves forward call the scale method, to give the effect
	  // that the player is walking towards the wall

	}

	public void draw(Graphics g) {
	  // if player direction = forward

	  g.drawRect(100, 100, 80, 80);
	}

}
