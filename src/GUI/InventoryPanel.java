package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.GameObject;

@SuppressWarnings("serial")
public class InventoryPanel extends JPanel implements ListSelectionListener {

  private int dimensionHeight = 100;
  private int dimensionWidth = 650;
  private int splitPaneWidth = 600;
  private int splitPaneHeight = 80;
  private String panelName = "Inventory";

  private GameFrame frame;

  JList<String> listOfItems;
  JTextField itemDescriptions;
  JSplitPane splitPane;
  String[] listData = {"Red Key", "Yellow Key", "Brown Key", "Beans"};

  /**
   * InventoryPanel stores a list and a text box. The list lists each item in the
   * players inventory, and when an item is clicked on the text box displays a
   * Description of that item.
   *
   * @param frame
   *          instance of GameFrame which this class is contained in, encase class
   *          needs to reference back.
   */
  public InventoryPanel(GameFrame frame) {
    this.frame = frame;

    // set dimensions
    Dimension dimension = getPreferredSize();
    dimension.width = dimensionWidth;
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);

    // set border
    setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelName));

    // add elements
    JList<String> listOfItems = new JList<String>(listData);

    listOfItems.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);////////
    listOfItems.setLayoutOrientation(JList.HORIZONTAL_WRAP);////////
    listOfItems.setVisibleRowCount(-1);////////

    itemDescriptions = new JTextField();

    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listOfItems, itemDescriptions);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(splitPaneWidth / 2);
    splitPane.setPreferredSize(new Dimension(splitPaneWidth, splitPaneHeight));

    Dimension minimumSize = new Dimension(100, 50);
    listOfItems.setMinimumSize(minimumSize);
    itemDescriptions.setMinimumSize(minimumSize);

    // set layout
    setLayout(new GridBagLayout());
    GridBagConstraints grid = new GridBagConstraints();

    // set components

    grid.gridx = 0;
    grid.gridy = 0;
    add(splitPane, grid);

  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // TODO Auto-generated method stub

  }
}
