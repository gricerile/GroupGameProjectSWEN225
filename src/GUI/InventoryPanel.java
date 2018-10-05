package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Door;
import main.GameItem;
import main.Key;

@SuppressWarnings("serial")
public class InventoryPanel extends JPanel {

  private int dimensionHeight = 120;
  private int splitPaneWidth = 600;
  private int splitPaneHeight = 80;
  private String panelName = "Inventory";

  private GameFrame frame;

  JScrollPane scrollPane;
  JList<GameItem> listOfItems;
  DefaultListModel<GameItem> listModel;

  JSplitPane splitPane;

  JPanel rightPanel;
  //JLabel image;
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
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);
	setMaximumSize(new Dimension(Integer.MAX_VALUE, dimensionHeight));
	setMinimumSize(new Dimension(Integer.MIN_VALUE, dimensionHeight));

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
    
    scrollPane = new JScrollPane(listOfItems);

    //listModel.addElement(new Key(null, "A gift from the gods, the holy treasure. This is your most valuable possession. Use it well.", "Beans"));



    // JPanel containing Jlabel and JTextArea (right section of JSplitPane)

    //JLabel image = new JLabel(GUI.resizeImage("arrowImages/arrowDownLeft.png", 50, 50));

    itemDescriptions = new JTextArea();
    itemDescriptions.setLineWrap(true);
    itemDescriptions.setEditable(false);

    //rightPanel = new JPanel();
    //rightPanel.setLayout(new BorderLayout());

    //rightPanel.add(image, BorderLayout.WEST);
    //rightPanel.add(itemDescriptions, BorderLayout.CENTER);





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


    //add listener
    listOfItems.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

      @Override
      public void valueChanged(ListSelectionEvent e) {

        GameItem ob = listOfItems.getSelectedValue();
        itemDescriptions.setText(ob.getDescription());
       // image.setIcon(ob.getImage());
        //image.setIcon(GUI.resizeImage("arrowImages/noImage.bmp", 50, 50));

      }
    });
  }
  
  public DefaultListModel<GameItem> getItems() {
	  return this.listModel;
  }
}
