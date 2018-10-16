package renderer;

import static org.junit.Assert.assertTrue;

import java.awt.Image;
import java.io.File;

import org.junit.Test;

import main.Chest;
import main.Door;
import main.FreeSpaceTile;
import main.Key;
import main.Main;
import main.Segment;
import main.Wall;
import main.WinTile;
import parser.Parser;

/**
 * test class for renderer package.
 * 
 * @author chenkelv
 *
 */
public class renderpackageTests {

  @Test
  /**
   * check if the loaded image is not empty
   */
  public void textureLoad1() {
    Texture texture = new Texture();
    Image image = texture.onLoad("grass64");

    assertTrue("image is not null", image != null);
  }

  @Test
  /**
   * check if the image path is correct.
   */
  public void textureLoad2() {
    Texture texture = new Texture();
    Image image = texture.onLoad("grass64");
    assertTrue("that is not the path", texture.toString().equals("/tx/grass64.png"));
  }

  @Test
  /**
   * check that the conversion between the coordinates are correct.
   */
  public void renderIsoY() {

  }

  @Test
  /**
   * check that the conversion between the coordinates are correct.
   */
  public void renderIsoX() {

  }

}
