package GUI;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.Main;

public class GUI {

	private String gameName = "Team 25 Game";
	private int frameWidth = 750;
	private int frameHeight = 750;

	private Main main;
	private GameFrame frame;

	public enum moveDirection {
		upLeft, upRight, downLeft, downRight;
	}

	/**
	 * GUI class starts a new thread for the GUI, and constructs the GameFrame.
	 *
	 * @param main
	 *            takes in instance of Main as a parameter to keep a reference to
	 *            that outer class
	 */

	public GUI(Main main) {
		this.main = main;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				initialise();
			}
		});
	}

	public void redraw() {
		frame.getGraphicsWindow().redraw();
	}

	/**
	 * initialises the GameFrame (JFrame) for the GUI.
	 */
	public void initialise() {

		frame = new GameFrame(gameName, this);

		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public Main getMain() {
		return main;
	}

	public GameFrame getFrame() {
		return this.frame;
	}

	/**
	 * Static method to resize images used in the GUI.
	 *
	 * @param imageAddress
	 *            address of image to be resized
	 * @param width
	 *            required width of final image
	 * @param height
	 *            required height of final image
	 * @return
	 */
	public static ImageIcon resizeImage(String imageAddress, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(imageAddress);
		Image image = imageIcon.getImage();
		Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newImage);
		return imageIcon;
	}

}
