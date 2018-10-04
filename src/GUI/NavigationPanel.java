package GUI;

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
import javax.swing.JPanel;



import GUI.GUI.moveDirection;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel implements ActionListener {

  private int dimensionHeight = 80;
  private int dimensionWidth = 650;

  private int buttonWidth = 30;
  private int buttonHeight = 30;

  private String panelName = "Navigation";

  private JButton upLeft;
  private JButton upRight;
  private JButton downLeft;
  private JButton downRight;

  private JButton save;
  private JButton load;
  private JButton quit;

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

    //set button dimensions
    Dimension buttonSize = new Dimension();
    buttonSize.setSize(buttonWidth, buttonHeight);

    // set border
    setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelName));

    // add elements
   // left = new JButton("\u2190");
    //right = new JButton("\u2192");
    //up = new JButton("\u2191");
    //down = new JButton("\u2193");
    upLeft = new JButton(shrinkImage("arrowImages/arrowUpLeft.png"));
    upLeft.setMargin(new Insets(0, 0, 0, 0));
    upRight = new JButton(shrinkImage("arrowImages/arrowUpRight.png"));
    upRight.setMargin(new Insets(0, 0, 0, 0));
    downLeft = new JButton(shrinkImage("arrowImages/arrowDownLeft.png"));
    downLeft.setMargin(new Insets(0, 0, 0, 0));
    downRight = new JButton(shrinkImage("arrowImages/arrowDownRight.png"));
    downRight.setMargin(new Insets(0, 0, 0, 0));




    save = new JButton("Save");
    load = new JButton("Load");
    quit = new JButton("Quit");
////////////////////////
   // JButton left = new JButton(new ImageIcon("tempArrow.png"));
    //////////////////////////////////////
    // actionlistners

    upLeft.addActionListener(this);
    upRight.addActionListener(this);
    downLeft.addActionListener(this);
    downRight.addActionListener(this);

    save.addActionListener(this);
    load.addActionListener(this);
    quit.addActionListener(this);

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
      frame.getGui().getMain().movePlayer(moveDirection.left);
    } else if (e.getSource() == upRight) {
        frame.getGui().getMain().movePlayer(moveDirection.right); // TODO
    } else if (e.getSource() == downLeft) {
        frame.getGui().getMain().movePlayer(moveDirection.up);
    } else if (e.getSource() == downRight) {
        frame.getGui().getMain().movePlayer(moveDirection.down);
    }


    else if (e.getSource() == save) {
        frame.getGui().getMain().saveGame();
    } else if (e.getSource() == load) {
        frame.getGui().getMain().loadGame();
    } else if (e.getSource() == quit) {
        frame.getGui().getMain().quitGame();
    }

  }
  
  private ImageIcon shrinkImage(String imageAddress) {
    ImageIcon imageIcon = new ImageIcon(imageAddress);
    Image image = imageIcon.getImage();
    Image newImage = image.getScaledInstance(this.buttonWidth, this.buttonHeight, Image.SCALE_SMOOTH);
    imageIcon = new ImageIcon(newImage);
    return imageIcon;
  }
  
}
