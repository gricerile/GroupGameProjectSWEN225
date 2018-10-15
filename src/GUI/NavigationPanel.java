package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import GUI.GUI.moveDirection;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel {

	private GUI gui;

	private int dimensionHeight = 100;

	NavigationButtons navButtons;
	GameButtons gameButtons;

	/**
	 * Navigation panel contains navigation buttons for the player to navigate the
	 * map.
	 *
	 * @param frame
	 *            instance of GameFrame which the instance of this class is
	 *            contained in to reference back.
	 */
	public NavigationPanel(GUI gui) {
		this.gui = gui;
		//this.setBackground(new Color(51, 153, 255));

		// set dimensions
		Dimension dimension = getPreferredSize();
		dimension.height = dimensionHeight;
		setPreferredSize(dimension);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, dimensionHeight));



		// set border
		//setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// add elements
		navButtons = new NavigationButtons(this.gui);
		gameButtons = new GameButtons(this.gui);


		// set layout
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();

		///// 1 column/////
		grid.gridx = 0;
		grid.gridy = 0;
		grid.ipadx=10;
		add(navButtons, grid);

		///// 2 column/////
		grid.gridx = 1;
		grid.gridy = 0;
		add(gameButtons, grid);




	}
}
