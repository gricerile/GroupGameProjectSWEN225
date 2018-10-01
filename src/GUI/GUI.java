package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public abstract class GUI {
	private String gameName = "Game";
	private int frameWidth = 500;
	private int frameHeight = 400;

	public GUI() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				initialise();
			}
		});
	}

	public void initialise() {

		GameFrame frame = new GameFrame(gameName);

		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
