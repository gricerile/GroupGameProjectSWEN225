package renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture {
  public int[] pixels; // hold data of the image
  private String fileLocation; // path to image
  public final int textureSize; // size of the texture

  public Texture(String fileLocation, int textureSize) {
    this.fileLocation = fileLocation;
    this.textureSize = textureSize;
    this.pixels = new int[textureSize * textureSize];

    // call the onLoad method
    onLoad();
  }

  public void onLoad() {
    try {
      BufferedImage image = ImageIO.read(new File(fileLocation));
      int w = image.getWidth();
      int h = image.getHeight();
      image.getRGB(0, 0, w, h, pixels, 0, w);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // set up the different textures
  public static Texture grass = new Texture("tx/grass.jpg", 65);
  public static Texture wall = new Texture("tx", 65);

}
