package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import renderer.Canvas;

public class GraphicsWindow extends JPanel {

	private int dimensionHeight = 200;
	private int dimensionWidth = 500;

	public GraphicsWindow() {

		// set dimensions
		Dimension dimension = getPreferredSize();
		dimension.width = dimensionWidth;
		dimension.height = dimensionHeight;
		setPreferredSize(dimension);

		// set border
		setBorder(BorderFactory.createEtchedBorder());
	}

	public void redraw() {
		repaint();
	}

	//in progress
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);

		Canvas canvas = new Canvas(this);
		canvas.draw( g );

	}

}
