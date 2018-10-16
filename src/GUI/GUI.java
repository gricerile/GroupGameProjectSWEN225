package GUI;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.Main;
import parser.Parser;

public class GUI {

	private Main main;

	private String gameName = "Team 25 Game";
	private int frameWidth = 750;
	private int frameHeight = 750;

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
	private void initialise() {

		loadMap();

		frame = new GameFrame(gameName, this);
		frame.setSize(frameWidth, frameHeight);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public Main getMain() {
		return main;
	}

	public GameFrame getFrame() {
		return this.frame;
	}

	public void loadMap() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Select Map file.");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (fileChooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
			// this.main.loadSpecificSegmentGame(fileChooser.getSelectedFile());
		} else {
			loadMap();
		}
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
