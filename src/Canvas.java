package renderer;
import d.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import d.Game;
import GUI.GUI;
import GUI.GraphicsWindow;
//in progress
public class Canvas {

	GraphicsWindow graphicsWindow;
	Graphics g;
	Game game;

	int width = 70;
	int height = 70;

	private GUI gui;

	private double sf = 1.2;
	public Canvas(GraphicsWindow graphicsWindow) {
		g = graphicsWindow.getGraphics();

	}


	public Graphics draw(Graphics g) {
		// test draw
    g.drawLine((int)(1+sf), (int)(1+sf), (int)(100+sf), (int)(100+sf));
    g.drawLine((int)(500+sf), (int)(1+sf), (int)(300+sf), (int)(100+sf));
    g.drawRect(100, 100, 200, 100);
    g.drawLine(1, 300, 100, 200);
    g.drawLine(500, 300, 300, 200);


    // draw and image of the item

    try {
      g = drawImage(g);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    return g;

  }

	public Graphics drawImage(Graphics g) throws IOException {


    // For storing image in RAM
    BufferedImage image = null;
    try
    {
        File input_file = new File("/am/phoenix/home1/chenkelv/eclipse-workspace/GroupProject/res/key.png"); //image file path

        image = new BufferedImage(50, 50,BufferedImage.TYPE_INT_ARGB);
        image = ImageIO.read(input_file);

        g.drawImage(image, 150, 220, (ImageObserver) gui);

        System.out.println("Reading complete.");
    }
    catch(IOException e)
    {
        System.out.println("Error: "+e);
    }
	  return g;

	}

}
