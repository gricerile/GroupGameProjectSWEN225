package main;

import java.awt.Graphics;

public class FreeSpaceTile implements GameObject {

  @Override
  public String getStatus() {
    // TODO Auto-generated method stub
    return "There is nothing on this segment.";
  }

  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return "FreeTile";
  }

@Override
public void draw(Graphics g, int windowWidth, int windowHeight) {
	// TODO Auto-generated method stub

}

}
