package renderer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import GUI.GUI;
import GUI.GameFrame;
import main.Main;
import main.Segment;

public class Renderer {

  private Main m;
  private main.Segment[][] board;
  public enum Item{EMPTY, DOOR, CHEST};
  private int sz = 20;

  private int SEG_WIDTH = 40;
  private int SEG_HEIGHT = 40;

  private Image image;

  private GameFrame gf;

  public Renderer(Main main) {
      this.m = main;
	  this.board = m.getSegments();	// initialise the board with the 2D array of segs stored in the main class
	  //this.board = new Segment[10][10];

  }

  private void loadImage() {
	  ImageIcon i = new ImageIcon("tx/grass.jpg");
	  image = i.getImage();
  }

  public void draw(Graphics g, int windowWidth, int windowHeight) {


	  int startX = windowWidth/2;
	  int startY = windowHeight/2;
	  for (int i = 0; i < board.length; i++) {
		  for(int j = 0; j<board[i].length; j++) {
				g.drawLine(startX, startY, startX+(SEG_WIDTH/2), startY+(SEG_HEIGHT/3));
				startX += SEG_WIDTH/2;
				startY += SEG_HEIGHT/3;
		  }
          startX = windowWidth/2 - (SEG_WIDTH*(i+1))/2;
		  startY = windowHeight/2 + (SEG_HEIGHT*(i+1))/3;
	  }
	  for (int i = 0; i < board.length; i++) {
          g.drawLine(startX, startY, startX + SEG_WIDTH/2, startY + SEG_HEIGHT/3);
          startX += SEG_WIDTH/2;
          startY += SEG_HEIGHT/3;
      }

      startX = windowWidth/2;
      startY = windowHeight/2;
      for (int i = 0; i < board.length; i++) {
          for(int j = 0; j<board[i].length; j++) {
              g.drawLine(startX, startY, startX - (SEG_WIDTH/2), startY + (SEG_HEIGHT/3));
              startX -= SEG_WIDTH/2;
              startY += SEG_HEIGHT/3;
          }
          startX = windowWidth/2 + (SEG_WIDTH*(i+1))/2;
          startY = windowHeight/2 + (SEG_HEIGHT*(i+1))/3;
      }
      for (int i = 0; i < board.length; i++) {

	          g.drawLine(startX, startY, startX - SEG_WIDTH/2, startY + SEG_HEIGHT/3);
	          startX -= SEG_WIDTH/2;
	          startY += SEG_HEIGHT/3;
      }

      /*
      startX = windowWidth/2;
      startY = windowHeight/2;
      for (int i = 0; i < board[0].length; i++) {
          for(int j = 0; j<board.length; j++) {
              g.drawLine(startX, startY, startX + SEG_WIDTH/3, startY + SEG_HEIGHT/2);
              startY += SEG_HEIGHT/2;
              startX += SEG_WIDTH/3;
              // g.drawImage(board[i][j].getTexture(), (windowWidth/2) -i*SEG_WIDTH, (windowHeight/2) -  j*SEG_HEIGHT, m.getGUI());
          }
          startY = windowHeight/2 - (SEG_HEIGHT*(i+1))/2;
          startX = windowWidth/2 + (SEG_WIDTH*(i+1))/3;
      }
      for (int i = 0; i < 3; i++) {
          g.drawLine(startX, startY, startX + SEG_WIDTH/3, startY + SEG_HEIGHT/2);
          startY += SEG_HEIGHT/2;
          startX += SEG_WIDTH/3;
      }*/

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
   * use polygons to draw the new isometric square
   *
   * */


}
