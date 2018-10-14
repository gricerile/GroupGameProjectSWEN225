package renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

import GUI.GUI;
import GUI.GUI.moveDirection;
import GUI.GameFrame;
import main.Main;
import main.Segment;
import main.Wall;

public class Renderer{

  private Main m;
  private main.Segment[][] board;
  private Image image;
  public int isox;
  public int isoy;

  /**
   * Renderer constructor that takes in the main.
   * @param main from the main class that sets the board up.
   */
  public Renderer(Main main) {
    this.m = main;
    // this.board = m.getSegments();
    // initialise the board with the 2D array of segs stored in the main class
    this.board = main.makeTestSegment();


  }

  /**
   * Draw method that should draw the map and the player based on the segment type
   * that is stored in the 2d array setup from the main.
   * @param g graphics.
   * @param windowWidth graphics frame width.
   * @param windowHeight graphics frame height.
   */
  public void draw(Graphics g, int windowWidth, int windowHeight) {

    for (int i = board.length - 1; i >= 0; --i) {
      for (int j = board[0].length - 1; j >= 0; j--) {
        Texture t = new Texture();
        if(board[i][j].getObject().getType().equals("WinTile")) {
          this.image = t.onLoad("player64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j) + image.getWidth(null)/2, null);
        } else {
          this.image = t.onLoad("grass64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
            (windowHeight / 2) - isoY(i, j) + image.getWidth(null)/2, null);
        }
      }
    }

    for (int i = board.length - 1; i >= 0; --i) {
      for (int j = board[0].length - 1; j >= 0; j--) {
        Texture t = new Texture();
        if (board[i][j].getObject().getType().equals("Wall")) {
          this.image = t.onLoad("wall64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j), null);

        } else if (board[i][j].getObject().getType().equals("YellowChest")) {
          this.image = t.onLoad("closedChest64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j), null);

        } else if (board[i][j].getObject().getType().equals("RedChest")) {
          this.image = t.onLoad("openedChest64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j), null);

        } else if (board[i][j].getObject().getType().equals("Door Locked")) {
          this.image = t.onLoad("door64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j), null);

        } else if (board[i][j].hasPlayer()) { // else if check for the player
          this.image = t.onLoad("player64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j), null);

        }

        // else just draw a empty tile (dead space)
      }
    }
  }


  public boolean checkWall() {
	  for(int i = 0; i < board.length; i++) {
		  for(int j = 0; j < board[i].length; j++) {
			  if(board[i][j] == null) {
				  return false;
			  }
			  if(board[i][j].getObject().getType().equals("Wall")) {
				  return true;
			  }
		  }
	  }
	  return false;
  }

  /**
   * Method to convert cartisian to isometric, helps render out the images in a diamond shape.
	 * turning regular 2d array into isometric.
	 * @param x coord from loop.
	 * @param y coord from loop.
	 * @return new isometric coord.
	 */
  private int isoX(int x, int y) {
    int segWidth = image.getWidth(null);
    int isoX;
    isoX = (int) ((x * segWidth / 2) - (y * segWidth / 2));
    return isoX;
  }

  private int isoY(int x, int y) {
    // convert y into isometric y
//    int segHeight = image.getHeight(null)-32;
    int segHeight = image.getHeight(null)/2;
    int isoY;
    isoY = (int) ((x * segHeight / 2) + (y * segHeight / 2));
    return isoY;
  }

  /*each square in a 2d grid is an segment object
   * get each segment and use coords to use in polygons to draw the new isometric square
   *
   * */


}
