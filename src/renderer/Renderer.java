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
import javafx.scene.shape.*;

//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.shape.Polygon;
//import javafx.stage.Stage;
import javax.swing.ImageIcon;

import GUI.GUI;
import GUI.GUI.moveDirection;
import GUI.GameFrame;
import main.Main;
import main.Segment;

public class Renderer{

  private Main m;
  private main.Segment[][] board;
  private int sz = 20;

  private int SEG_WIDTH = 20;
  private int SEG_HEIGHT = 20;

  private int xx;
  private int xy;
  private int yx;
  private int yy;

  private Image image;
  public int isox;
  public int isoy;

  public int tileHeight;

 /**
 * constructor that takes in the main class.
 * @param main from the main class that sets up the segment board.
 */
  public Renderer(Main main) {
	  this.m = main;
	  this.board = m.getSegments();	// initialise the board with the 2D array of segs stored in the main class
	  //this.board = new Segment[10][10];
	  Texture t = new Texture();
	  this.image = t.onLoad("/tx/grass.png");
//	  for(int i = board.length-1; i >= 0 ; --i)
//		  for(int j = board[0].length-1; j >= 0; j--)
//			  if(board[i][j] == null)
//				  board[i][j].setImage(this.image);
//	  			  System.out.println("the image is added");

  }

  public void drawMove(moveDirection direction) {
	  if(direction == moveDirection.downLeft) {
		  this.xx += 20;
		  this.xy += 20;
	  }
  }

  public void drawPlayer(Graphics g, int x, int y) {
	  for(int i = board.length-1; i >= 0 ; --i) {
		  for(int j = board[0].length-1; j >= 0; j--) {
			  g.drawOval(x, y, 20, 20);
		  }
	  }
  }

  public void draw(Graphics g, int windowWidth, int windowHeight) {


	  // board[0][0].setImage(image);

	  // draw the board
	  for(int i = board.length-1; i >= 0 ; --i) {
		  for(int j = board[0].length-1; j >= 0; j--) {
			  System.out.println(i + "and : " + j );
			  if(board[i][j] == null) {
				  System.out.println("its empty");
			  }

			  // g.drawImage(board[i][j].setImage(this.image), (windowWidth/2) - isoX(i, j),  (windowHeight/2) - isoY(i, j), null);

			  // Image floor = board[i][j].getImage();
			  // g.drawRect(SEG_WIDTH/2*i+SEG_HEIGHT/2*j, SEG_WIDTH/2*i+SEG_HEIGHT/2*j, sz, sz);
			  // g.drawRect((windowWidth/2) -i*SEG_WIDTH,(windowHeight/2) -  j*SEG_HEIGHT, sz, sz);
			  g.drawImage(image, (windowWidth/2) - isoX(i, j),  (windowHeight/2) - isoY(i, j), null);
		  }
	  }

	  for(int i = board.length; i > 0 ; --i) {
		  for(int j = board[0].length-1; j >= 0; j--) {
			  // draw walls
			  if(checkWall()) {
				  // render a wall tile
			  }
		  }
	  }

	  drawPlayer(g,m.getX(),m.getY());
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

  // turning regular 2d array into isometric
  // isoX = cartX - cartY;
  // isoY = (cartX + cartY) / 2
	private int isoX(int x, int y) {
		// convert x into isometric x
		int segWidth = image.getWidth(null);

		int isoX;
		isoX = (int) ((x * segWidth / 2) - (y * segWidth / 2));
		return isoX;
	}

	private int isoY(int x, int y) {
		// convert y into isometric y
		int segHeight = image.getHeight(null)-15;
		int isoY;
		isoY = (int) ((x * segHeight / 2) + (y * segHeight / 2));

		return isoY;
	}

	public void convert(Segment[][] board) {
	  for(int i = 0; i < board.length; i++) {
		  for(int j = 0; j < board[i].length; j++) {

		  }
	  }
	}

	public int findXCoord(Image image) {
		return image.getWidth(null)/2;
	}

	public int findYCoord(Image image) {
		return image.getHeight(null)/2;
	}

  /*each square in a 2d grid is an segment object
   * get each segment and use coords to use in polygons to draw the new isometric square
   *
   * */


}
