package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.GameItem;

/**
 * InventoryPanel stores a list and a text box within a splitPane. The list
 * lists each item in the players inventory, and when an item is clicked on the
 * text box displays a Description of that item.
 *
 * @author millerdani1
 *
 */
@SuppressWarnings("serial")
public class InventoryPanel extends JPanel {

  private int dimensionHeight = 120;
  private int splitPaneWidth = 600;
  private int splitPaneHeight = 80;

  JScrollPane scrollPane;
  JList<GameItem> listOfItems;
  DefaultListModel<GameItem> listModel;

  JSplitPane splitPane;
  JTextArea itemDescriptions;

  /**
   * Sets dimensions for InventoryPanel, adds and configures JList and JTextArea
   * within JSplitPane. Sets up actionListener interaction between JList and
   * JTextArea.
   *
   */
  public InventoryPanel() {

    // set dimensions
    Dimension dimension = getPreferredSize();
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);
    setMaximumSize(new Dimension(Integer.MAX_VALUE, dimensionHeight));
    setMinimumSize(new Dimension(Integer.MIN_VALUE, dimensionHeight));

    //////////////////////////// Add Elements///////////////////////////////////

    // JList (left section of JSplitPane)

    listOfItems = new JList<>();
    listOfItems.setBorder(BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 0), "Inventory"));
    listModel = new DefaultListModel<>();

    listOfItems.setModel(listModel);
    listOfItems.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    listOfItems.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    listOfItems.setVisibleRowCount(-1);

    scrollPane = new JScrollPane(listOfItems);

    // JTextArea (right section of JSplitPane)

    itemDescriptions = new JTextArea();
    itemDescriptions.setLineWrap(true);
    itemDescriptions.setEditable(false);
    itemDescriptions.setBorder(BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 0), "Description"));

    // JSPlitPane
    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, itemDescriptions);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(splitPaneWidth / 2);
    splitPane.setPreferredSize(new Dimension(splitPaneWidth, splitPaneHeight));

    // set layout
    setLayout(new GridBagLayout());
    GridBagConstraints grid = new GridBagConstraints();

    // set components

    grid.gridx = 0;
    grid.gridy = 0;
    add(splitPane, grid);

    // add listener
    listOfItems.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

      @Override
      public void valueChanged(ListSelectionEvent e) {

        GameItem object = listOfItems.getSelectedValue();
        itemDescriptions.setText(object.getDescription());

      }
    });
  }

  /**
   * called to add item to inventory.
   *
   * @param item
   *          GameItem to be added to inventory
   */
  public void addItemToInventory(GameItem item) {
    listModel.addElement(item);
  }

  /**
   * clears inventory of all GameItems.
   */
  public void clearInventory() {
    this.listModel.clear();
  }
}
