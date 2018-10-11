package main;

import java.awt.Graphics;

public interface GameObject {
  public String getStatus();

  public String getType();

  public void draw(Graphics g, int windowWidth, int windowHeight);
}
