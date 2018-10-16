package renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture {

  private String path = "/tx/"; // path to image
  private String imageName = "";
  public Texture() {}

  /**
   * already considers the path from tx folder and file type .png,
   * image is loaded through typing the name of the image.
   * @param imgName is the name of the file.
   * @return null if there is no image.
   */
  public synchronized BufferedImage onLoad(String imgName) {
    try {
      BufferedImage img = null;
      this.imageName = imgName;
      img = ImageIO.read(getClass().getResource(path + imageName + ".png"));
      return img;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("image is not there");
      e.printStackTrace();
    }
    return null;
  }

  /**
   * returns the path to the image.
   */
  public String toString() {
    return this.path + this.imageName + ".png";
  }

}
