package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel {

	private GUI gui;

	private int dimensionHeight = 100;

	NavigationButtons navButtons;
	GameButtons gameButtons;

	/**
	 * NavigationPanel contains 2 JPanels containing buttons to navigate the map and
	 * interact with it
	 *
	 * @param gui
	 *            instance of GUI to reference back to when buttons are pressed
	 */
	public NavigationPanel(GUI gui) {
		this.gui = gui;

		// set dimensions
		Dimension dimension = getPreferredSize();
		dimension.height = dimensionHeight;
		setPreferredSize(dimension);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, dimensionHeight));

		// add elements
		navButtons = new NavigationButtons(this.gui);
		gameButtons = new GameButtons(this.gui);

		// set layout
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();

		///// 1 column/////
		grid.gridx = 0;
		grid.gridy = 0;
		grid.ipadx = 10;
		add(navButtons, grid);

		///// 2 column/////
		grid.gridx = 1;
		grid.gridy = 0;
		add(gameButtons, grid);
	}
}
