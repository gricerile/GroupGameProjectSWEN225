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
    this.board = m.makeTestSegment();
//    this.board = m.createBoard();


  }

  /**
   * Draw method that should draw the map and the player based on the segment type
   * that is stored in the 2d array setup from the main.
   * first layer is rendered as grass, that is where the player walks,
   * second layer is all the obstacles / objects in the game, such as walls, doors, chests etc.
   *
   * @param g graphics.
   * @param windowWidth graphics frame width.
   * @param windowHeight graphics frame height.
   */
  public void draw(Graphics g, int windowWidth, int windowHeight) {
    // render the first layer of the game (ground / win tile)
    for (int i = board.length - 1; i >= 0; --i) {
      for (int j = board[0].length - 1; j >= 0; j--) {
        Texture t = new Texture();
        // render the win tile that the player needs to land on
        if (board[i][j].getObject().getType().equals("WinTile")) {
          this.image = t.onLoad("winTile64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j) + image.getHeight(null) * 4 , null);
        } else {
          // otherwise render the ground tiles (free tiles)
          this.image = t.onLoad("grass64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j) + image.getHeight(null) * 4 , null);
        }
      }
    }
    // end of rendering for the first layer of the map

    // being rendering of the objects, walls, chest, doors
    for (int i = board.length - 1; i >= 0; --i) {
      for (int j = board[0].length - 1; j >= 0; j--) {
        Texture t = new Texture();
        // check to render the wall
        if (board[i][j].getObject().getType().equals("Wall")) {
          this.image = t.onLoad("wall64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j) + image.getHeight(null) * 4 - image.getHeight(null) / 2, null);
        // check to render the chest (closed)
        } else if (board[i][j].getObject().getType().equals("YellowChest")) {
          this.image = t.onLoad("closedChest64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j) + image.getHeight(null) * 4 - image.getHeight(null) / 2, null);
        // check to render the chest (opened)
        } else if (board[i][j].getObject().getType().equals("RedChest")) {
          this.image = t.onLoad("openedChest64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j) + image.getHeight(null) * 4 - image.getHeight(null) / 2, null);
        // check to render the door
        } else if (board[i][j].getObject().getType().equals("Door Locked")) {
          this.image = t.onLoad("door64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j) + image.getHeight(null) * 4 - image.getHeight(null) / 2, null);
        // check to render the player
        } else if (board[i][j].hasPlayer()) { // else if check for the player
          this.image = t.onLoad("player64");
          g.drawImage(image, (windowWidth / 2) - isoX(i, j),
              (windowHeight / 2) - isoY(i, j) + image.getHeight(null) * 4 - image.getHeight(null) / 2, null);
        }
        // end of rendering the second layer
      }
    }
  }

  /**
   * Method to convert cartisian to isometric, helps render out the images in a diamond shape.
   * turning regular 2d array into isometric.
   * @param x coord from loop.
   * @param y coord from loop.
   * @return new isometric X coord.
   */
  private int isoX(int x, int y) {
    int segWidth = image.getWidth(null);
    int isoX;
    isoX = (int) ((x * segWidth / 2) - (y * segWidth / 2));
    return isoX;
  }

  /**
   * Method to convert cartisian to isometric, helps render out the images in a diamond shape.
   * turning regular 2d array into isometric.
   * @param x coord from loop.
   * @param y coord from loop.
   * @return new isometric Y coord.
   */
  private int isoY(int x, int y) {
    // convert y into isometric y
    // int segHeight = image.getHeight(null)-32;
    int segHeight = image.getHeight(null) / 2;
    int isoY;
    isoY = (int) ((x * segHeight / 2) + (y * segHeight / 2));
    return isoY;
  }

  /*each square in a 2d grid is an segment object
   * get each segment and use coords to use in polygons to draw the new isometric square
   *
   * */


}
