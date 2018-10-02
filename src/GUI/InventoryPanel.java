package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import main.GameObject;

public class InventoryPanel extends JPanel {

	private int dimensionHeight =100;
	private int dimensionWidth =650;
	private String panelName = "Inventory";

	public InventoryPanel() {

		//set dimensions
		Dimension dimension = getPreferredSize();
		dimension.width = dimensionWidth;
		dimension.height = dimensionHeight;
		setPreferredSize(dimension);

		//set border
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelName));

		//add elements
		JList<GameObject> listOfItems = new JList<GameObject>();
		listOfItems.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);////////
		listOfItems.setLayoutOrientation(JList.HORIZONTAL_WRAP);////////
		listOfItems.setVisibleRowCount(-1);////////

		JTextField itemDescriptions = new JTextField();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listOfItems, itemDescriptions);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		Dimension minimumSize = new Dimension(100, 50);
		listOfItems.setMinimumSize(minimumSize);
		itemDescriptions.setMinimumSize(minimumSize);

		//set layout
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();

		//set components

		grid.gridx=0;
		grid.gridy=0;
		add(splitPane, grid);

	}
}
