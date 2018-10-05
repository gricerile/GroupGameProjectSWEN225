package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class EventOutputPanel extends JPanel {

  JLabel textArea;

  private int dimensionHeight = 50;
  private int dimensionWidth = 650;
  private String panelName = "Event Output";

  private GameFrame frame;

  /**
   * EventOutputPanel contains a textbox where all game related events are to be
   * reported. e.g. "this door is locked"
   *
   * @param frame
   *          instance of GameFrame which this class is contained in, encase class
   *          needs to reference back.
   */
  public EventOutputPanel(GameFrame frame) {
    this.frame = frame;

    // set dimensions
    Dimension dimension = getPreferredSize();
    dimension.width = dimensionWidth;
    dimension.height = dimensionHeight;
    setPreferredSize(dimension);

    // set border
    setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelName));

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
