import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Texture {
  public ArrayList<Texture> textures;   // let other classes access the texture files
  public int[] pixels;           // hold the data for all pixels in the image of texture
  private String fileLocation;   // image file location
  public int size;               // texture image size

  /**
   * Tecture constructor.
   * @param location of the file.
   * @param size of the file eg 64x64.
   */
  public Texture(String location, int size) {
    this.fileLocation = location;
    this.size = size;
    pixels = new int [size * size];
    load();
  }

  /**
   * loading in texture files.
   */
  private void load() {
    try {
      BufferedImage image = ImageIO.read(new File(fileLocation));
      int w = image.getWidth();
      int h = image.getHeight();
      image.getRGB(0, 0, w, h, pixels, 0, w);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // get some texture files to load in.

  public static Texture floor = new Texture();
  public static Texture wall = new Texture();
  public static Texture ceilling = new Texture();


}
