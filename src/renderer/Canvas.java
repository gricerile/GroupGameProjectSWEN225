package renderer;

import java.awt.Graphics;

import GUI.GraphicsWindow;

//in progress
public class Canvas {

	GraphicsWindow graphicsWindow;
	Graphics g;

	public Canvas(GraphicsWindow graphicsWindow) {
		g = graphicsWindow.getGraphics();
	}

	public void draw(Graphics g) {
		// test draw
		g.fillRect(20, 20, 100, 50);
		
	}

}
