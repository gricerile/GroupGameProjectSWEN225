package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EventOutputPanel extends JPanel {

  JLabel textArea;

  private int dimensionHeight = 50;


  /**
   * EventOutputPanel contains a textbox where all game related events are to be
   * reported. e.g. "this door is locked"
   *
   * @param frame
   *          instance of GameFrame which this class is contained in, encase class
   *          needs to reference back.
   */
  public EventOutputPanel() {

    // set dimensions
    Dimension dimension = getPreferredSize();
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);
    setMaximumSize(new Dimension(Integer.MAX_VALUE, dimensionHeight));
    setMinimumSize(new Dimension(Integer.MIN_VALUE, dimensionHeight));

    // set border
    //setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelName));
    //setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

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

  public String getTextOutput() {
    return textArea.getText();
  }

  public void setTextOutput(String textOutput) {
    textArea.setText(textOutput);
  }
}
