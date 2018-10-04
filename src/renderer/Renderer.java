package renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;

import main.Main;
import main.Segment;

public class Renderer {

  private Main m;
  private Segment[][] board;
  public enum Item{EMPTY, DOOR, CHEST};
  private int sz = 10;
  
  private int colCount = 10;
  private int rowCount = 10;
  
  
  
  public Renderer() {
//	  this.board = m.getSegments();	// initialise the board with the 2D array of segs stored in the main class
	  
  }

  
  public void draw(Graphics g, int windowWidth, int windowHeight) {
	  this.board = new Segment[20][20];
	  for (int i = 0; i < board.length; i++)
			for(int j = 0; j<board[i].length; j++)
				g.drawRect(i*sz, j*sz, sz, sz);
				//g.drawRect(sz/2*i+sz/2*j, sz/2*i+sz/2*j, sz, sz);

//    g.drawRect((windowWidth/2)-50, (windowHeight/2)-50, 100, 100);
//    g.drawOval((windowWidth/2)-50, (windowHeight/2)-50, 100, 100);
  }
  
  
  // turning regular 2d array into isometric
  // isoX = cartX - cartY;
  // isoY = (cartX + cartY) / 2
  public void toIso() {
	  
  }
}