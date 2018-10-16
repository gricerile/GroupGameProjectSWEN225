package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.GUI.MoveDirection;

/**
 * NavigationButton contains navigation buttons for the player to navigate the
 * map.
 *
 * @author millerdani1
 *
 */
@SuppressWarnings("serial")
public class NavigationButtons extends JPanel implements ActionListener {

  private GUI gui;

  private int buttonWidth = 30;
  private int buttonHeight = 30;

  private JButton upLeft;
  private JButton upRight;
  private JButton downLeft;
  private JButton downRight;

  /**
   * initializes buttons, sets button icon to images, adds actionListeners to
   * buttons and sets layout of button.
   *
   * @param gui
   *          instance of GUI to reference back to when calling methods from
   *          actionListeners.
   */
  public NavigationButtons(GUI gui) {
    this.gui = gui;

    // set button dimensions
    Dimension buttonSize = new Dimension();
    buttonSize.setSize(buttonWidth, buttonHeight);

    // add elements
    upLeft = new JButton(
        GUI.resizeImage("arrowImages/arrowUpLeft.png", this.buttonWidth, this.buttonHeight));
    upLeft.setMargin(new Insets(0, 0, 0, 0));
    upRight = new JButton(
        GUI.resizeImage("arrowImages/arrowUpRight.png", this.buttonWidth, this.buttonHeight));
    upRight.setMargin(new Insets(0, 0, 0, 0));
    downLeft = new JButton(
        GUI.resizeImage("arrowImages/arrowDownLeft.png", this.buttonWidth, this.buttonHeight));
    downLeft.setMargin(new Insets(0, 0, 0, 0));
    downRight = new JButton(
        GUI.resizeImage("arrowImages/arrowDownRight.png", this.buttonWidth, this.buttonHeight));
    downRight.setMargin(new Insets(0, 0, 0, 0));

    // set background color of navigation buttons to fill in empty padding
    upLeft.setBackground(Color.WHITE);
    upRight.setBackground(Color.WHITE);
    downLeft.setBackground(Color.WHITE);
    downRight.setBackground(Color.WHITE);

    // action listeners
    upLeft.addActionListener(this);
    upRight.addActionListener(this);
    downLeft.addActionListener(this);
    downRight.addActionListener(this);

    // set layout
    setLayout(new GridBagLayout());
    GridBagConstraints grid = new GridBagConstraints();

    ///// first column/////

    grid.gridx = 0;
    grid.gridy = 0;
    add(upLeft, grid);

    grid.gridx = 0;
    grid.gridy = 1;
    add(downLeft, grid);

    ///// second column/////
    grid.gridx = 1;
    grid.gridy = 0;
    add(upRight, grid);

    grid.gridx = 1;
    grid.gridy = 1;
    add(downRight, grid);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == upLeft) {
      this.gui.getMain().movePlayer(MoveDirection.upRight);
    } else if (e.getSource() == upRight) {
      this.gui.getMain().movePlayer(MoveDirection.upLeft);
    } else if (e.getSource() == downLeft) {
      this.gui.getMain().movePlayer(MoveDirection.downRight);
    } else if (e.getSource() == downRight) {
      this.gui.getMain().movePlayer(MoveDirection.downLeft);
    }
  }
}
