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

/*
	public void draw(Graphics g) {
		// test draw
    g.drawLine((int)(1+sf), (int)(1+sf), (int)(100+sf), (int)(100+sf));
    g.drawLine((int)(300+sf), (int)(1+sf), (int)(200+sf), (int)(100+sf));
    g.drawRect(100, 100, 100, 100);
    g.drawLine(1, 300, 100, 200);
    g.drawLine(300, 300, 200, 200);
  }



*/