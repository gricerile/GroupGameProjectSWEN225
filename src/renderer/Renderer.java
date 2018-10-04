package renderer;

import java.awt.Graphics;

public class Renderer {

  public Renderer() {
  }

  public void draw(Graphics g, int windowWidth, int windowHeight) {
    g.drawRect((windowWidth/2)-50, (windowHeight/2)-50, 100, 100);
    g.drawOval((windowWidth/2)-50, (windowHeight/2)-50, 100, 100);
  }
}