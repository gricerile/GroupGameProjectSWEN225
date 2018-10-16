package main;

import java.awt.Graphics;

/**
 * Chest object on Segments that hold keys.
 * @author gricerile
 *
 */
public class Chest implements GameObject {
  // fields
  private boolean open;
  private Key key;

  /**
   * constructor for chest.
   *
   * @param key
   *          assigned to chest.
   */
  public Chest(Key k) {
    this.key = k;
    this.open = false;
  }

  /**
   * gets for key.
   *
   * @return key field.
   */
  public Key getKey() {
    return this.key;
  }

  /**
   * open and closes chest by altering open boolean.
   *
   * @return String of the event that occurs if opened of closed.
   */
  public String openAndClose() {
    if (this.open == true) {
      this.open = false;
      return "The chest is now closed.";
    } else {
      this.open = true;
      return "The chest is now open.";
    }
  }

  /**
   * Like poll on a queue, returns and removes key from chest.
   *
   * @return key from chest.
   */
  public Key takeKey() {
    if (this.open == true && this.key != null) {
      Key k = this.key;
      this.key = null;
      return k;
    } else if (this.open == false) {
      // System.out.println("The Chest is not open.");
      return null;
    } else {
      // System.out.println("Chest is empty");
      return null;
    }
  }

  /**
   * gets state of chest.
   *
   * @return A string of the current state.
   */
  @Override
  public String getStatus() {
    if (this.open == true && this.key != null) {
      return "The chest is open and there is something inside.";
    } else if (this.open == true && this.key == null) {
      return "The chest is open and it is empty.";
    } else {
      return "The chest is closed.";
    }
  }

  /**
   * gets game object type.
   *
   * @return string form of gameobject type.
   */
  @Override
  public String getType() {
    // TODO Auto-generated method stub
    if (this.open == true) {
      return "RedChest";
    }
    return "YellowChest";
  }

}
