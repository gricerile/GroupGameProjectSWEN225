package GUI;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public abstract class GUI {
	private String gameName = "Game";
	private int frameWidth = 500;
	private int frameHeight = 400;

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

	//public void redraw() {
	//	frame.repaint();
	//}

	public void initialise() {

		frame = new GameFrame(gameName);

		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
