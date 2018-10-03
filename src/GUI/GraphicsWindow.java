package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import renderer.Canvas;

public class GraphicsWindow extends JPanel implements MouseListener {

	private BufferedImage image;

	private int dimensionHeight = 400;
	private int dimensionWidth = 650;

	private GameFrame frame;

	public GraphicsWindow(GameFrame frame) {
		this.frame = frame;

		// set dimensions
		Dimension dimension = getPreferredSize();
		dimension.width = dimensionWidth;
		dimension.height = dimensionHeight;
		setPreferredSize(dimension);

		// set border
		setBorder(BorderFactory.createEtchedBorder());

		// add mouseListeners
		addMouseListener(this);
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

	//////////////// Mouse Listeners/////////////////////////////////

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.print("x: " + e.getX() + "y: " + e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
