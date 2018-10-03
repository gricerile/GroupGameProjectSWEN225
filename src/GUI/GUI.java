package GUI;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import renderer.Canvas;

public abstract class GUI {
	private String gameName = "Team 25 Game";
	private int frameWidth = 700;
	private int frameHeight = 700;

	private GameFrame frame;
	private Canvas canvas;

	protected abstract BufferedImage render();



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

		//frame.graphicsWindow.setImage(render());

		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);


	}

}
