package main;

import java.awt.Graphics;

import renderer.Texture;

public class Wall implements GameObject {

  private Texture wall = new Texture();
  @Override
  public String getStatus() {
    // TODO Auto-generated method stub
    return "There is a wall in the way.";
  }

  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return "Wall";
  }

}
