package renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GUI.GraphicsWindow;

//in progress
public class Canvas {

	GraphicsWindow graphicsWindow;
	Graphics g;
	double sf = 1.2;

	public Canvas(GraphicsWindow graphicsWindow) {
		g = graphicsWindow.getGraphics();

	}

	public void drawScene(Graphics g) {
		// test draw
	    g.drawLine((int)(1+sf), (int)(1+sf), (int)(100+sf), (int)(100+sf));
	    g.drawLine((int)(500+sf), (int)(1+sf), (int)(300+sf), (int)(100+sf));


	    g.drawRect(100, 100, 200, 100);

	    g.drawLine(1, 300, 100, 200);
	    g.drawLine(500, 300, 300, 200);



	}

}
