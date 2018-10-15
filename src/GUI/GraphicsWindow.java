package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
public class GraphicsWindow extends JPanel implements MouseListener, ActionListener {

	private GUI gui;

	private boolean hasWon;
	private int dimensionHeight = 600;
	private int dimensionWidth = 600;

	private JPopupMenu popUp;
	private JMenuItem takeFromChest;
	private JMenuItem unlockDoor;
	private JMenuItem openChest;

	/**
	 * GraphicsWindow is the JPanel which the game graphics will be painted on.
	 * GraphicsWindow also implements mouseListener to track click events.
	 *
	 * @param gui
	 *            instance of GUI to reference back to when calling draw method in
	 *            renderer class.
	 */
	public GraphicsWindow(GUI gui) {
		this.gui = gui;

		// set dimensions
		Dimension dimension = getPreferredSize();
		dimension.width = dimensionWidth;
		dimension.height = dimensionHeight;
		setPreferredSize(dimension);

		// set border
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// popupMenu elements
		unlockDoor = new JMenuItem("Unlock Door");
		takeFromChest = new JMenuItem("Take items From Chest");
		openChest = new JMenuItem("Open Chest");

		// button action listener
		unlockDoor.addActionListener(this);
		takeFromChest.addActionListener(this);
		openChest.addActionListener(this);

		// add popup elements to popup menu
		this.popUp = new JPopupMenu();
		this.popUp.add(openChest);
		this.popUp.add(takeFromChest);
		this.popUp.add(unlockDoor);

		// add mouseListener
		addMouseListener(this);

	}

	public void redraw() {
		repaint();
	}

	/**
	 * Paint component of GraphicsWindow, allows window to be painted on and is
	 * refreshed by a repaint call on this class.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);

		if (this.gui.getMain().getRenderer() == null) {
			g.setColor(Color.BLACK);
			g.drawString("NO IMAGE", this.getWidth() / 2, this.getHeight() / 2);
		} else if(this.hasWon) {
			this.gui.getMain().getRenderer().draw(g, getWidth(), getHeight());
			
			String s = "Congratulations You Have Won!";
			Font text = new Font("Dialog", Font.PLAIN, 25);
			
			g.setFont(text);
			g.drawString(s, (getWidth()/2), (getHeight()/2));
			
			
			
			
		} else {
			this.gui.getMain().getRenderer().draw(g, getWidth(), getHeight());
		}

	}

	//////////////// Mouse Listeners/////////////////////////////////

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getButton() == 1) {
			// currently has no functionality
			this.gui.getMain().clickedScreen(1, 1);
		} else if (e.getButton() == 3) {
			this.popUp.show(this, e.getX(), e.getY());
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == unlockDoor) {
			this.gui.getMain().playerAttempUnlock();
		} else if (e.getSource() == takeFromChest) {
			this.gui.getMain().takeFromChest();
		} else if (e.getSource() == openChest) {
			this.gui.getMain().openChest();
		}
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}
}
