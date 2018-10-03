package renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GUI.GraphicsWindow;

//in progress
public class Canvas {

	GraphicsWindow graphicsWindow;
	Graphics g;
	BufferedImage image;

	public Canvas(GraphicsWindow graphicsWindow, BufferedImage image) {
		g = graphicsWindow.getGraphics();
		this.image=image;
	}

	public void drawScene(Graphics g) {
		// test draw
		g.drawImage(image, 0, 0, null);

	}

}
