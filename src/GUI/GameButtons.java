package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import GUI.GUI.moveDirection;

public class GameButtons extends JPanel implements ActionListener {

	private GUI gui;

	private int dimensionHeight = 100;

	private int buttonWidth = 119;
	private int buttonHeight = 24;

	private JButton openChest;
	private JButton lootChest;
	private JButton unlockDoor;

	/**
	 * Navigation panel contains navigation buttons for the player to navigate the
	 * map.
	 *
	 * @param frame
	 *            instance of GameFrame which the instance of this class is
	 *            contained in to reference back.
	 */
	public GameButtons(GUI gui) {
		this.gui = gui;
		//this.setBackground(new Color(51, 153, 255));

		// set dimensions
		//Dimension dimension = getPreferredSize();
		//dimension.height = dimensionHeight;
		//setPreferredSize(dimension);
		//setMaximumSize(new Dimension(Integer.MAX_VALUE, dimensionHeight));

		// set button dimensions
		Dimension buttonSize = new Dimension();
		buttonSize.setSize(buttonWidth, buttonHeight);


		// add elements
		openChest = new JButton("Open Chest");
		lootChest = new JButton("Loot Chest");
		unlockDoor = new JButton("Unlock Door");

		openChest.setPreferredSize(buttonSize);
		lootChest.setPreferredSize(buttonSize);
		unlockDoor.setPreferredSize(buttonSize);

		// actionlistners

		lootChest.addActionListener(this);
		openChest.addActionListener(this);
		unlockDoor.addActionListener(this);

		// set layout
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();

		///// first column/////

		grid.gridx = 0;
		grid.gridy = 0;
		add(lootChest, grid);

		grid.gridx = 0;
		grid.gridy = 1;
		add(openChest, grid);

		grid.gridx = 0;
		grid.gridy = 2;
		add(unlockDoor, grid);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == unlockDoor) {
			this.gui.getMain().playerAttempUnlock();
		} else if(e.getSource() == lootChest) {
			this.gui.getMain().takeFromChest();
		} else if(e.getSource() == openChest) {
			this.gui.getMain().openChest();
		}
	}
}
