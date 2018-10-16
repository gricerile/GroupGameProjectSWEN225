package main;

import java.awt.Graphics;

/**
 * Game object interface for objects on segments.
 * @author gricerile
 *
 */
public interface GameObject {
  /**
   * gets state of game objects.
   *
   *@return String form of game objects.
   */
  public String getStatus();

  /**
   * gets game object type.
   *
   *@return String form of game object type.
   */
  public String getType();

}
