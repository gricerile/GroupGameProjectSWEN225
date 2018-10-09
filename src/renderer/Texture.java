package renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture {

	private String fileLocation;	// path to image

	public Texture() {
		this.fileLocation = fileLocation;
		// call the onLoad method
	}

	public synchronized BufferedImage onLoad(String path) {


			try {
				BufferedImage img = null;
				img = ImageIO.read(getClass().getResource(path));
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
