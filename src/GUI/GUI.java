package GUI;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public abstract class GUI {
	private String gameName = "Game";
	private int frameWidth = 700;
	private int frameHeight = 700;

	private GameFrame frame;

	/**
	 * redraws graphical interface when called
	 *
	 * @param g
	 */
	protected abstract void redraw(Graphics g);

	public GUI() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				initialise();
			}
		});
	}

	public void redraw() {
		frame.graphicsWindow.redraw();
	}

	public void initialise() {

		frame = new GameFrame(gameName);

		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
