package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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

  JList<String> listOfItems;
  JTextArea itemDescriptions;
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
    JList<tempGameObject> listOfItems = new JList<>();

    DefaultListModel<tempGameObject> listModel = new DefaultListModel<>();
    listOfItems.setModel(listModel);
    
    listModel.addElement(new TempKey("Red Key","This is a reddish-brown key. it is very old and speccled with rust"));
    listModel.addElement(new TempKey("Torch","This is an old torch. The torch allows you to see much further"));
    listModel.addElement(new TempKey("Beans","The gift from the gods, the holy treasure. This is your most valuable possetion. Use it well."));

    listOfItems.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

      @Override
      public void valueChanged(ListSelectionEvent e) {
        tempGameObject ob = listOfItems.getSelectedValue();
        itemDescriptions.setText(ob.getDescription());
      }
    });
    
    
    
    listOfItems.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    listOfItems.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    listOfItems.setVisibleRowCount(-1);

    itemDescriptions = new JTextArea();
    itemDescriptions.setLineWrap(true);

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

    
    //listOfItems.addListSelectionListener(this);
  }

//  @Override
//  public void valueChanged(ListSelectionEvent e) {
//    // TODO Auto-generated method stub
//    //this.itemDescriptions.setText("gog");
//    if(this.itemDescriptions!=null && this.listOfItems!=null) {
//    this.itemDescriptions.setText("eoe");
//    }
//  }
}
