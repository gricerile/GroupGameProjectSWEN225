package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
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
public class InventoryPanel extends JPanel {// implements ListSelectionListener {

  private int dimensionHeight = 100;
  private int dimensionWidth = 650;
  private int splitPaneWidth = 600;
  private int splitPaneHeight = 80;
  private String panelName = "Inventory";

  private GameFrame frame;

  JList<tempGameObject> listOfItems;
  DefaultListModel<tempGameObject> listModel;

  JSplitPane splitPane;

  JPanel rightPanel;
  JLabel image;
  JTextArea itemDescriptions;

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

    //////////////////////////// Add Elements///////////////////////////////////

    // JList (left section of JSplitPane)
    listOfItems = new JList<>();
    listModel = new DefaultListModel<>();

    listOfItems.setModel(listModel);
    listOfItems.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    listOfItems.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    listOfItems.setVisibleRowCount(-1);

    listModel.addElement(new TempKey("Red Key", "This is a reddish-brown key. it is very old and speccled with rust", null));
    listModel.addElement(new TempKey("Torch", "This is an old torch. The torch allows you to see much further", null));
    listModel.addElement(new TempKey("Beans",
        "The gift from the gods, the holy treasure. This is your most valuable possetion. Use it well.", null));



    // JPanel containing Jlabel and JTextArea (right section of JSplitPane)

    JLabel image = new JLabel(GUI.resizeImage("arrowImages/arrowDownLeft.png", 50, 50));

    itemDescriptions = new JTextArea();
    itemDescriptions.setLineWrap(true);

    rightPanel = new JPanel();
    rightPanel.setLayout(new BorderLayout());

    rightPanel.add(image, BorderLayout.WEST);
    rightPanel.add(itemDescriptions, BorderLayout.CENTER);

    // JSPlitPane
    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listOfItems, rightPanel);
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


    //add listener
    listOfItems.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

      @Override
      public void valueChanged(ListSelectionEvent e) {

        tempGameObject ob = listOfItems.getSelectedValue();
        itemDescriptions.setText(ob.getDescription());
        image.setIcon(ob.getImage());

      }
    });





  }
}
