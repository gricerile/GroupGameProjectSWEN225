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
  public enum Item{EMPTY, DOOR, CHEST};
  private int sz = 20;

  private int SEG_WIDTH = 20;
  private int SEG_HEIGHT = 20;

  private Image image;
  public int isox;
  public int isoy;

//  public Polygon isoTile;


  public Renderer(Main main) {
      this.m = main;
	  this.board = m.getSegments();	// initialise the board with the 2D array of segs stored in the main class
	  //this.board = new Segment[10][10];

	  Texture t = new Texture();
	  this.image = t.onLoad("/tx/grass.png");
  }




  public void draw(Graphics g, int windowWidth, int windowHeight) {
//	  isoTile = new Polygon();
//	  isoTile.getPoints().addAll(new Double[] {
//		0.0, 0.0,
//		20.0, 20.0,
//		0.0, 20.0,
//		0.0, 0.0
//	  });
	  //toIso();





//	  AffineTransform at = AffineTransform.getShearInstance(0.5, -0.5);
//	  Graphics2D g3d = (Graphics2D) g;
//	  g3d.transform(at);

	  for(int i = board.length; i > 0 ; --i) {
		  for(int j = board[0].length-1; j >= 0; j--) {


			  //g.drawRect(SEG_WIDTH/2*i+SEG_HEIGHT/2*j, SEG_WIDTH/2*i+SEG_HEIGHT/2*j, sz, sz);
			  g.drawImage(image, (windowWidth/2) - isoX(i, j),  (windowHeight/2) - isoY(i, j), null);
			  // g.drawRect((windowWidth/2) -i*SEG_WIDTH,(windowHeight/2) -  j*SEG_HEIGHT, sz, sz);
			  //g.drawImage(img, (windowWidth/2) - ((i-j) *segWidth/2), (windowHeight/2) - ((i + j)*segHeight/2), null);

		  }
	  }



      	// there is a wall fill the rectangle to indicate that it is a wall
//      	if(checkWall()) {
//      		g.fillRect(10, 10, 10, 10);
//      	}


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

  /*each square in a 2d grid is an segment object
   * get each segment and use coords to use in polygons to draw the new isometric square
   *
   * */


}
