package GUI;

import java.awt.Color;
import java.awt.Dimension;
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

	private int dimensionHeight = 600;
	private int dimensionWidth = 600;

	private GameFrame frame;

	private JPopupMenu popUp;

	private JMenuItem takeFromChest;
	private JMenuItem unlockDoor;
	private JMenuItem openChest;

	/**
	 * GraphicsWindow is the JPanel which the game graphics will be painted on.
	 * GraphicsWindow also implements mouseListener to track click events.
	 *
	 * @param frame
	 *            instance of GameFrame which this class is contained in, encase
	 *            class needs to reference back.
	 */
	public GraphicsWindow(GameFrame frame) {
		this.frame = frame;
		this.popUp = new JPopupMenu();

		// set dimensions
		Dimension dimension = getPreferredSize();
		dimension.width = dimensionWidth;
		dimension.height = dimensionHeight;
		setPreferredSize(dimension);
		// setMaximumSize(dimension);
		// setMinimumSize(new Dimension(100, 100));

		// set border
		// setBorder(BorderFactory.createEtchedBorder());
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// popupMenu elements
		unlockDoor = new JMenuItem("Unlock Door");
		takeFromChest = new JMenuItem("Take items From Chest");
		openChest = new JMenuItem("Open Chest");
		
		//button action listener
		unlockDoor.addActionListener(this);
		takeFromChest.addActionListener(this);
		openChest.addActionListener(this);

		this.popUp.add(openChest);
		this.popUp.add(takeFromChest);
		this.popUp.add(unlockDoor);

		// add mouseListeners
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

		if (this.frame.getGui().getMain().getRenderer() == null) {

			g.setColor(Color.BLACK);
			g.drawString("NO IMAGE", this.getWidth() / 2, this.getHeight() / 2);

		} else {

			this.frame.getGui().getMain().getRenderer().draw(g, getWidth(), getHeight());
		}

	}

	//////////////// Mouse Listeners/////////////////////////////////

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getButton() == 1) {
			frame.getGui().getMain().clickedScreen(e.getX(), e.getY());
		} else if (e.getButton() == 3) {
			// getType of tile clicked on and show different pop ups depending
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
		if(e.getSource() == unlockDoor) {
			this.frame.getGui().getMain().playerAttempUnlock();
		} else if(e.getSource() == takeFromChest) {
			this.frame.getGui().getMain().takeFromChest();
		} else if(e.getSource() == openChest) {
			this.frame.getGui().getMain().openChest();
		}
	}
}
