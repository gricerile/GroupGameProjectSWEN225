package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * GameButtons contains buttons for the player to interact with objects in the
 * game world. e.g. Unlock a Door or take items from a chest.
 *
 * @author millerdani1
 *
 */
@SuppressWarnings("serial")
public class GameButtons extends JPanel implements ActionListener {

  private GUI gui;

  private int buttonWidth = 119;
  private int buttonHeight = 24;

  private JButton openChest;
  private JButton lootChest;
  private JButton unlockDoor;

  /**
   * Sets size of buttons in GameButtons JPanel, adds buttons, sets
   * actionListeners to buttons and sets layout of buttons within the JPanel.
   *
   * @param gui
   *          instance of GUI to reference back to when calling methods from
   *          actionListeners.
   */
  public GameButtons(GUI gui) {
    this.gui = gui;

    // set button dimensions
    Dimension buttonSize = new Dimension();
    buttonSize.setSize(buttonWidth, buttonHeight);

    // add elements
    openChest = createSimpleButton("Open Chest", buttonSize);
    lootChest = createSimpleButton("Loot Chest", buttonSize);
    unlockDoor = createSimpleButton("Unlock Door", buttonSize);

    // actionlistners
    lootChest.addActionListener(this);
    openChest.addActionListener(this);
    unlockDoor.addActionListener(this);

    // set layout
    setLayout(new GridBagLayout());
    GridBagConstraints grid = new GridBagConstraints();

    ///// first column/////
    grid.gridx = 0;
    grid.gridy = 0;
    add(lootChest, grid);

    grid.gridx = 0;
    grid.gridy = 1;
    add(openChest, grid);

    grid.gridx = 0;
    grid.gridy = 2;
    add(unlockDoor, grid);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == unlockDoor) {
      this.gui.getMain().playerAttempUnlock();
    } else if (e.getSource() == lootChest) {
      this.gui.getMain().takeFromChest();
    } else if (e.getSource() == openChest) {
      this.gui.getMain().openChest();
    }
  }

  private static JButton createSimpleButton(String text, Dimension buttonSize) {
    JButton button = new JButton(text);
    button.setForeground(Color.BLACK);
    button.setBackground(Color.WHITE);
    button.setPreferredSize(buttonSize);

    return button;
  }
}
