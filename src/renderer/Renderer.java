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
  private Segment[][] board;
  public enum Item{EMPTY, DOOR, CHEST};
  private int sz = 20;
  
  private int SEG_WIDTH = 20;
  private int SEG_HEIGHT = 20;
  
  private Image image;
  
  private GameFrame gf;
  
  public Renderer() {
//	  this.board = m.getSegments();	// initialise the board with the 2D array of segs stored in the main class
	  this.board = new Segment[10][10];
  }

  private void loadImage() {
	  ImageIcon i = new ImageIcon("tx/grass.jpg");
	  image = i.getImage();
  }
  
  public void draw(Graphics g, int windowWidth, int windowHeight) {
	  
	  for (int i = 0; i < board.length; i++) {
		  for(int j = 0; j<board[i].length; j++) {
				g.drawRect((windowWidth/2) -i*SEG_WIDTH,(windowHeight/2) -  j*SEG_HEIGHT, sz, sz);
				// g.drawImage(board[i][j].getTexture(), (windowWidth/2) -i*SEG_WIDTH, (windowHeight/2) -  j*SEG_HEIGHT, m.getGUI());
		  }
		  
	  }
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
  

}