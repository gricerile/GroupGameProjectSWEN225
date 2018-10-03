package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.Main;

public class GUI {

  private String gameName = "Team 25 Game";
  private int frameWidth = 700;
  private int frameHeight = 700;

  private Main main;
  private GameFrame frame;

  enum moveDirection{
    up, down, left, right;
  }

  /**
   * GUI class starts a new thread for the GUI, and constructs the GameFrame.
   *
   * @param main
   *          takes in instance of Main as a parameter to keep a reference to that
   *          outer class
   */

  public GUI(Main main) {
    this.main = main;
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        initialise();
      }
    });
  }

  public void redraw() {
    frame.graphicsWindow.redraw();
  }

  /**
   * initialises the GameFrame (JFrame) for the GUI.
   */
  public void initialise() {

    frame = new GameFrame(gameName, this);

    frame.setSize(frameWidth, frameHeight);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }

  public Main getMain() {
    return main;
  }

}
