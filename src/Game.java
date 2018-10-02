import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable{

  private static final long serialVersionUID = 1L;
  public int mapWidth = 15;
  public int mapHeight = 15;
  private Thread thread;
  private boolean running;  // a boolean to check if the player is moving
  private BufferedImage image;
  public int[] pixels;

  // just a general map for drawing practice
  public static int[][] map = {
      {1,1,1,1,1,1,1,1,2,2,2,2,2,2,2},
      {1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
      {1,0,3,3,3,3,3,0,0,0,0,0,0,0,2},
      {1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
      {1,0,3,0,0,0,3,0,2,2,2,0,2,2,2},
      {1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
      {1,0,3,3,0,3,3,0,2,0,0,0,0,0,2},
      {1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
      {1,1,1,1,1,1,1,1,4,4,4,0,4,4,4},
      {1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
      {1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
      {1,0,0,2,0,0,1,4,0,3,3,3,3,0,4},
      {1,0,0,0,0,0,1,4,0,3,3,3,3,0,4},
      {1,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
      {1,1,1,1,1,1,1,4,4,4,4,4,4,4,4}
  };

  /**
   * constructor for the game.
   */
  public Game() {
    thread = new Thread(this);
    image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
    pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    // set up a basic board -> this part will be where Daneil will have his canvas connected

    setSize(640, 480);
    setResizable(false);
    setTitle("3D Engine");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(Color.black);
    setLocationRelativeTo(null);
    setVisible(true);
    start();
  }

  // Start and stop methods are here to make it starts properly

  /**
   * when the player moves then start it up.
   */
  private synchronized void start() {
    running = true;
    thread.start();
  }

  /**
   * when the player stops moving set boolean to false.
   */
  public synchronized void stop() {
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * rendering method to draw.
   * buffer strategy is used so when the the walls and floors are rendered its smoother.
   */
  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    bs.show();
  }


  /* (non-Javadoc)
   *
   * refresh rate, get as smooth as possible
   *
   *
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
//    // TODO Auto-generated method stub
//    long lastTime = System.nanoTime();
//    final double ns = 100000000000.0;
//    double delta = 0;
//    requestFocus();
//    while (running) {
//      long now = System.nanoTime();
//      delta = delta + ((now - lastTime) / ns);
//      lastTime = now;
//      while (delta >= 1) {   // make sure the update is only happening 60 times a sec
//        // handles all of the logic restricted time
//        delta--;
//      }
//      render(); //displays to the screen unrestricted time
//    }
  }




  public static void main(String [] args) {
    Game game = new Game();
  }





}
