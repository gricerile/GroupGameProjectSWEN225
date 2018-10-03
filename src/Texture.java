package d;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Texture {
  public ArrayList<Texture> textures;   // let other classes access the texture files
  // get some texture files to load in.

  public int[] pixels;           // hold the data for all pixels in the image of texture
  private String fileLocation;   // image file location
  public final int SZ;               // texture image size

  /**
   * Tecture constructor.
   * @param location of the file.
   * @param size of the file eg 64x64.
   */
  public Texture(String location, int size) {
    this.fileLocation = location;
    this.SZ = size;
    this.pixels = new int[SZ * SZ];
    load();
  }

  /**
   * loading in texture files.
   */
  private void load() {
    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(fileLocation));
      int w = image.getWidth();
      int h = image.getHeight();
      image.getRGB(0, 0, w, h, pixels, 0, w);
      //image.getRGB(w, h);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  // /am/phoenix/home1/chenkelv/eclipse-workspace/GroupProject/src/floor.png
  // /GroupProject/src/floor.png
  public static Texture floor = new Texture("/am/phoenix/home1/chenkelv/eclipse-workspace/GroupProject/res/floor.png", 65);
  public static Texture wall = new Texture("/am/phoenix/home1/chenkelv/eclipse-workspace/GroupProject/res/wall.png", 65);
  public static Texture sky = new Texture("/am/phoenix/home1/chenkelv/eclipse-workspace/GroupProject/res/sky.png", 65);
}
