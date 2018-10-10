package renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture {
	
	private String path = "/tx/";	// path to image
	
	public Texture() {
	}
	
	public Texture(String newPath) {
		this.path = newPath;
	}
	
	public synchronized BufferedImage onLoad(String imgName) {
		
			
			try {
				BufferedImage img = null;
				img = ImageIO.read(getClass().getResource(path + imgName + ".png"));
				return img;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("not there");
				e.printStackTrace();
			}
			
			return null;

	}
	
	
	
	// set up the different textures
//	public static Texture grass = new Texture("tx/grass.jpg", 65);
//	public static Texture wall = new Texture("tx",65);
	
}
