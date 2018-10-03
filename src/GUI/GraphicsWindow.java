package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import renderer.Canvas;

public class GraphicsWindow extends JPanel {

	private BufferedImage image;

	private int dimensionHeight = 400;
	private int dimensionWidth = 650;

	public GraphicsWindow() {

		// set dimensions
		Dimension dimension = getPreferredSize();
		dimension.width = dimensionWidth;
		dimension.height = dimensionHeight;
		setPreferredSize(dimension);

		// set border
		setBorder(BorderFactory.createEtchedBorder());
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void redraw() {
		repaint();
	}

	// in progress
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);

		if (image == null) {

			g.setColor(Color.BLACK);
			g.drawString("NO IMAGE", this.getWidth() / 2, this.getHeight() / 2);

		} else {

			g.drawImage(image, 0, 0, null);

		}

	}

}
