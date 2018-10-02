package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationPanel extends JPanel {

	private int dimensionHeight =80;
	private int dimensionWidth =650;
	private String panelName = "Navigation";


	public NavigationPanel() {

		//set dimensions
		Dimension dimension = getPreferredSize();
		dimension.width = dimensionWidth;
		dimension.height=dimensionHeight;
		setPreferredSize(dimension);

		//set border
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelName));

		//add elements
		JButton left = new JButton("\u2190");
		JButton right = new JButton("\u2192");
		JButton up = new JButton("\u2191");
		JButton down = new JButton("\u2193");

		//set layout
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();

		/////first column/////

		//grid.weightx=0.5;
		//grid.weighty=0.5;

		grid.gridx=0;
		grid.gridy=0;

		grid.gridx=0;
		grid.gridy=1;
		add(left, grid);

		/////second column/////
		grid.gridx=1;
		grid.gridy=0;
		add(up, grid);

		grid.gridx=1;
		grid.gridy=1;
		add(down, grid);

		/////third column/////
		grid.gridx=2;
		grid.gridy=0;
		add(Box.createRigidArea(right.getSize()), grid);

		grid.gridx=2;
		grid.gridy=1;
		add(right, grid);
	}
}
