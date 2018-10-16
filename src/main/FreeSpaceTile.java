package main;

import java.awt.Graphics;

public class FreeSpaceTile implements GameObject {

  /**
   * gets state of game objects.
   *
   * @return String form of game objects.
   */
  @Override
  public String getStatus() {
    // TODO Auto-generated method stub
    return "There is nothing on this segment.";
  }

  /**
   * gets game object type.
   *
   * @return String form of game object type.
   */
  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return "FreeTile";
  }

}
