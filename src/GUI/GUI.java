package GUI;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.Main;
import renderer.Canvas;

public class GUI {

	private String gameName = "Team 25 Game";
	private int frameWidth = 700;
	private int frameHeight = 700;

	private Main main;
	private GameFrame frame;



	//protected abstract void move();



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
		frame.graphicsWindow.redraw();
	}

	public void initialise() {

		frame = new GameFrame(gameName, this);

		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);



	}

}
