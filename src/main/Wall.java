package main;

import java.awt.Graphics;

import renderer.Texture;

public class Wall implements GameObject {

  private Texture wall = new Texture();

  /**
   * gets current state.
   *
   * @return The current state of the Wall.
   */
  @Override
  public String getStatus() {
    // TODO Auto-generated method stub
    return "There is a wall in the way.";
  }

  /**
   * gets game object type in string form.
   *
   * @return the game object type.
   */
  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return "Wall";
  }

}
