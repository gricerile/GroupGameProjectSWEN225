package gui;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import main.Main;

/**
 * GUI class contains the GameFrame and stores static methods used in the GUI.
 *
 * @author millerdani1
 *
 */
public class GUI {

  private Main main;

  private String gameName = "Team 25 Game";
  private int frameWidth = 750;
  private int frameHeight = 750;

  private GameFrame frame;

  /**
   * the movement directions for player movement, as game is set in isometric
   * movement directions are in diagonal directions. Is declared public as this
   * enum is used in several classes and packages
   *
   * @author millerdani1
   *
   */
  public enum MoveDirection {
    /**
     * player movement to the upper Left (NW).
     */
    upLeft,
    /**
     * player movement to the upper Right (NE).
     */
    upRight,
    /**
     * player movement to the Lower Left (SW).
     */
    downLeft,
    /**
     * player movement to the Lower Right (SE).
     */
    downRight;
  }

  /**
   * calls initialize method to setup the GUI.
   *
   * @param main
   *          takes in instance of Main as a parameter to keep a reference to that
   *          outer class
   */

  public GUI(Main main) {
    this.main = main;
    initialise();
    /*
     * SwingUtilities.invokeLater(new Runnable() {
     *
     * @Override public void run() { initialise(); } });
     */
  }

  /**
   * method can be used to manually access the GraphicsWindow and redraw the
   * paintComponent.
   */
  public void redraw() {
    frame.getGraphicsWindow().redraw();
  }

  /**
   * Initializes the GameFrame (JFrame) for the GUI.
   */
  private void initialise() {

    frame = new GameFrame(gameName, this);
    frame.setSize(frameWidth, frameHeight);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * getter method for main, which GUI is contained in.
   *
   * @return instance of Main which GUI is contained in
   */
  public Main getMain() {
    return main;
  }

  /**
   * Getter method for GameFrame which is contained in GUI.
   *
   * @return instance of GameFrame stored in GUI
   */
  public GameFrame getFrame() {
    return this.frame;
  }

  /**
   * called when user selects Load New Game from GameFrame menu. Opens
   * JFileChooser to select map file to open. Relevant method in main is then
   * called passing through the file.
   */
  public void loadMap() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File("."));
    fileChooser.setDialogTitle("Select Map file.");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    if (fileChooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      this.main.loadSpecificMap(file);
    }
  }

  /**
   * Static method to resize images used in the GUI.
   *
   * @param imageAddress
   *          address of image to be resized
   * @param width
   *          required width of final image
   * @param height
   *          required height of final image
   * @return processed ImageIcon, ready to be used in GUI.
   */
  public static ImageIcon resizeImage(String imageAddress, int width, int height) {
    ImageIcon imageIcon = new ImageIcon(imageAddress);
    Image image = imageIcon.getImage();
    Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    imageIcon = new ImageIcon(newImage);
    return imageIcon;
  }

}
