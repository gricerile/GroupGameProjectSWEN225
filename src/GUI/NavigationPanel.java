package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import GUI.GUI.moveDirection;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel implements ActionListener {

  private int dimensionHeight = 100;

  private int buttonWidth = 30;
  private int buttonHeight = 30;

  private String panelName = "Navigation";

  private JButton upLeft;
  private JButton upRight;
  private JButton downLeft;
  private JButton downRight;

  private GameFrame frame;

  /**
   * Navigation panel contains navigation buttons for the player to navigate the
   * map.
   *
   * @param frame
   *          instance of GameFrame which the instance of this class is contained
   *          in to reference back.
   */
  public NavigationPanel(GameFrame frame) {
    this.frame = frame;
    this.setBackground(new Color(51, 153, 255));

    // set dimensions
    Dimension dimension = getPreferredSize();
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);
    setMaximumSize(new Dimension(Integer.MAX_VALUE, dimensionHeight));

    // set button dimensions
    Dimension buttonSize = new Dimension();
    buttonSize.setSize(buttonWidth, buttonHeight);

    // set border
    //setBorder(BorderFactory.createEtchedBorder());
      setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    // add elements
    upLeft = new JButton(GUI.resizeImage("arrowImages/arrowUpLeft.png", this.buttonWidth, this.buttonHeight));
    upLeft.setMargin(new Insets(0, 0, 0, 0));
    upRight = new JButton(GUI.resizeImage("arrowImages/arrowUpRight.png", this.buttonWidth, this.buttonHeight));
    upRight.setMargin(new Insets(0, 0, 0, 0));
    downLeft = new JButton(GUI.resizeImage("arrowImages/arrowDownLeft.png", this.buttonWidth, this.buttonHeight));
    downLeft.setMargin(new Insets(0, 0, 0, 0));
    downRight = new JButton(GUI.resizeImage("arrowImages/arrowDownRight.png", this.buttonWidth, this.buttonHeight));
    downRight.setMargin(new Insets(0, 0, 0, 0));

    ////////////////////////
    upLeft.setBackground(Color.BLACK);
    upRight.setBackground(Color.BLACK);
    downLeft.setBackground(Color.BLACK);
    downRight.setBackground(Color.BLACK);
    //////////////////////////////////////
    // actionlistners

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
      frame.getGui().getMain().movePlayer(moveDirection.upRight);
    } else if (e.getSource() == upRight) {
      frame.getGui().getMain().movePlayer(moveDirection.upLeft);
    } else if (e.getSource() == downLeft) {
      frame.getGui().getMain().movePlayer(moveDirection.downRight);
    } else if (e.getSource() == downRight) {
      frame.getGui().getMain().movePlayer(moveDirection.downLeft);
    }
  }
}
