package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationPanel extends JPanel implements ActionListener {

  private int dimensionHeight = 80;
  private int dimensionWidth = 650;
  private String panelName = "Navigation";

  private JButton left;
  private JButton right;
  private JButton up;
  private JButton down;

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

    // set dimensions
    Dimension dimension = getPreferredSize();
    dimension.width = dimensionWidth;
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);

    // set border
    setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelName));

    // add elements
    left = new JButton("\u2190");
    right = new JButton("\u2192");
    up = new JButton("\u2191");
    down = new JButton("\u2193");

    // actionlistners

    left.addActionListener(this);
    right.addActionListener(this);
    up.addActionListener(this);
    down.addActionListener(this);

    // set layout
    setLayout(new GridBagLayout());
    GridBagConstraints grid = new GridBagConstraints();

    ///// first column/////

    grid.gridx = 0;
    grid.gridy = 0;

    grid.gridx = 0;
    grid.gridy = 1;
    add(left, grid);

    ///// second column/////
    grid.gridx = 1;
    grid.gridy = 0;
    add(up, grid);

    grid.gridx = 1;
    grid.gridy = 1;
    add(down, grid);

    ///// third column/////
    grid.gridx = 2;
    grid.gridy = 0;
    add(Box.createRigidArea(right.getSize()), grid);

    grid.gridx = 2;
    grid.gridy = 1;
    add(right, grid);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == left) {
      // TODO add functionality
    } else if (e.getSource() == right) {
      // TODO
    } else if (e.getSource() == up) {
      // TODO
    } else if (e.getSource() == down) {
      // TODO
    }

  }
}
