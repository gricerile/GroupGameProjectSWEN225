package renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;


import GUI.GUI;
import GUI.GUI.moveDirection;
import GUI.GameFrame;
import main.Main;
import main.Segment;
import javafx.scene.shape.*;




public class Renderer {

  private Main m;
  private main.Segment[][] board;
  private int sz = 20;

  private int SEG_WIDTH = 40;
  private int SEG_HEIGHT = 40;

  //private ArrayList<Polygon> allPolygons = new ArrayList<>();

  private Image image;

  public Renderer(Main main) {
      this.m = main;
	  this.board = m.getSegments();	// initialise the board with the 2D array of segs stored in the main class
	  //this.board = new Segment[10][10];

  }

  private void loadImage() {
	  ImageIcon i = new ImageIcon("/GroupProject/src/tx/grass.jpg");
	  image = i.getImage();
  }

  public void draw(Graphics g, int windowWidth, int windowHeight) {


	 // drawPlayer(g);

//      Texture t = new Texture();
//	  Image img = t.onLoad("/tx/dirt.png");
//	  g.drawImage(img, 10, 10, null);
//
//	  Image character = t.onLoad("/tx/grass.png");
//	  g.drawImage(character, 100, 100, null);
//
//
//
//	  int startX = windowWidth/2;
//	  int startY = windowHeight/2;
//	  for (int i = 0; i < board.length; i++) {
//		  for(int j = 0; j<board[i].length; j++) {
//				g.drawLine(startX, startY, startX+(SEG_WIDTH/2), startY+(SEG_HEIGHT/3));
//				startX += SEG_WIDTH/2;
//				startY += SEG_HEIGHT/3;
//		  }
//          startX = windowWidth/2 - (SEG_WIDTH*(i+1))/2;
//		  startY = windowHeight/2 + (SEG_HEIGHT*(i+1))/3;
//	  }
//	  for (int i = 0; i < board.length; i++) {
//          g.drawLine(startX, startY, startX + SEG_WIDTH/2, startY + SEG_HEIGHT/3);
//          startX += SEG_WIDTH/2;
//          startY += SEG_HEIGHT/3;
//      }
//
//      startX = windowWidth/2;
//      startY = windowHeight/2;
//      for (int i = 0; i < board.length; i++) {
//          for(int j = 0; j<board[i].length; j++) {
//              g.drawLine(startX, startY, startX - (SEG_WIDTH/2), startY + (SEG_HEIGHT/3));
//              startX -= SEG_WIDTH/2;
//              startY += SEG_HEIGHT/3;
//          }
//          startX = windowWidth/2 + (SEG_WIDTH*(i+1))/2;
//          startY = windowHeight/2 + (SEG_HEIGHT*(i+1))/3;
//      }
//      for (int i = 0; i < board.length; i++) {
//
//	          g.drawLine(startX, startY, startX - SEG_WIDTH/2, startY + SEG_HEIGHT/3);
//	          startX -= SEG_WIDTH/2;
//	          startY += SEG_HEIGHT/3;
//      }

	  for(int i = 0; i < board.length; i++) {
		  for(int j = 0; j < board[i].length; j++) {
			  g.drawRect((windowWidth/2) - i*SEG_WIDTH, (windowHeight/2) - j*SEG_HEIGHT, SEG_WIDTH, SEG_HEIGHT);
			  // g.drawPolygon(new int[] {0, 20, 40, 20}, new int[] {10, 0, 10, 20}, 4);


		  }
	  }
      	// there is a wall fill the rectangle to indicate that it is a wall
      	if(checkWall()) {
      		g.fillRect(10, 10, 10, 10);
      	}

  }

//  public void drawPlayer() {
//	  m.getPlayer().loadImage();
//  }

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


  public void updateBoard() {}


  // turning regular 2d array into isometric
  // isoX = cartX - cartY;
  // isoY = (cartX + cartY) / 2
  public void toIso() {
	  for (int i = 0; i < board.length; i++) {
		  for(int j = 0; j<board[i].length; j++) {

			  // screen.x = map.x * SEG_HEIGHT
			  // screen.y = map.y * SEG_WIDTH
		  }
	  }
  }





  /*each square in a 2d grid is an segment object
   * get each segment and use coords to use in polygons to draw the new isometric square
   *
   * */


}
