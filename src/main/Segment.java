package main;

import java.awt.Image;

import renderer.Texture;

public class Segment {
  // fields
  private GameObject object;
  private int x, y;
  private Image image;
  private Texture texture = new Texture();
  private boolean hasPlayer;

  /**
   * Constructor of Segment
   *
   * @param the game object type on the segment.
   *
   * @param x position.
   *
   * @param y position.
   */
  public Segment(GameObject object, int x, int y) {
    this.object = object;
    this.x = x;
    this.y = y;
    this.hasPlayer = false;
  }

  /**
   * Gets the boolean of the has player field.
   *
   * @return boolean of has player field.
   *
   */
  public boolean hasPlayer() {
    return this.hasPlayer;
  }

  /**
   * sets has player field.
   *
   *@param boolean which determines is has player field is true or false.
   */
  public void setHasPlayer(boolean b) {
    this.hasPlayer = b;
  }

  /**
   * gets the game objects.
   *
   *@return the game object on the segment.
   */
  public GameObject getObject() {
    return this.object;
  }

  /**
   * gets x coord.
   *
   *@return x coord of segment.
   */
  public int getX() {
    return this.x;
  }

  /**
   * gets y coord.
   *
   *@return y coord of segment.
   */
  public int getY() {
    return this.y;
  }

  /**
   * Method to compare two segments to check if they're the same.
   *
   * @param segment that will be compared.
   *
   *@return boolean of comparison.
   */
  public boolean equals(Segment s) {
    if (this.getX() == s.getX() && this.getY() == s.getY()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   *Opens chest on segment if there is a chest.
   *
   *@return String from the event of the opening or not of the chest.
   */
  public String opensChest() {
    if (this.object instanceof Chest) {
      Chest c = (Chest) this.object;
      return c.openAndClose();
    }
    return "";
  }

  /**
   * gets the key from a chest and returns it.
   *
   *@return key from chest.
   */
  public Key takeFromChest() {
    if (this.object instanceof Chest) {
      Chest c = (Chest) this.object;
      return c.takeKey();
    }
    return null;
  }
}
