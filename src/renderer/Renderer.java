package renderer;

import java.awt.Graphics;

public class Renderer {

  public Renderer() {
  }

  public void draw(Graphics g) {
    g.drawRect(10, 10, 100, 100);
    g.drawOval(10, 10, 100, 100);
  }
}