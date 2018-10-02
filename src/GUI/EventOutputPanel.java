package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EventOutputPanel extends JPanel {

	private int dimensionHeight =50;
	private int dimensionWidth =650;
	private String panelName = "Event Output";


	public EventOutputPanel() {

		//set dimensions
		Dimension dimension = getPreferredSize();
		dimension.width = dimensionWidth;
		dimension.height=dimensionHeight;
		setPreferredSize(dimension);

		//set border
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelName));

		//add elements
		JTextArea textArea = new JTextArea();
		textArea.setText("This door is locked");//temp

		//set layout
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();

		/////first column/////

		grid.gridx=0;
		grid.gridy=0;
		add(textArea, grid);

	}
}
