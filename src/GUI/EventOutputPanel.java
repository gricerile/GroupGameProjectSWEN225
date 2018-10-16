package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * EventOutputPanel contains a JLable where all game related events are to be
 * reported. e.g. "this door is locked".
 *
 * @author millerdani1
 *
 */
@SuppressWarnings("serial")
public class EventOutputPanel extends JPanel {

  JLabel textArea;

  private int dimensionHeight = 50;

  /**
   * Dimensions of EventOutput are set, elements added and layout set.
   */
  public EventOutputPanel() {

    // set dimensions
    Dimension dimension = getPreferredSize();
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);
    setMaximumSize(new Dimension(Integer.MAX_VALUE, dimensionHeight));
    setMinimumSize(new Dimension(Integer.MIN_VALUE, dimensionHeight));

    // add elements
    textArea = new JLabel();

    // set layout
    setLayout(new GridBagLayout());
    GridBagConstraints grid = new GridBagConstraints();

    ///// first column/////

    grid.gridx = 0;
    grid.gridy = 0;
    add(textArea, grid);

  }

  /**
   * sets the string value of the JLabel contained within EventOutputPanel.
   *
   * @param textOutput
   *          String JLabel will be set to
   */
  public void setTextOutput(String textOutput) {
    textArea.setText(textOutput);
  }
}
