package main;

import java.awt.Graphics;

import main.Key;

/**
 * Door object on Segments that needs a key to unlock.
 * @author gricerile
 *
 */
public class Door implements GameObject {
  // fields
  private int ID; // code name for door and key association, unique for each door and key
  private boolean unlocked;

  /**
   * constructor of door.
   *
   *@param id of door that is matched with key.
   *
   *@param boolean to determine if door locked or unlocked.
   */
  public Door(int s, boolean unlocked) {
    this.ID = s;
    this.unlocked = unlocked;
  }

  /**
   * gets door id.
   *
   *@return id field.
   */
  public int ID() {
    return this.ID;
  }

  /**
   * gets unlocked boolean.
   *
   *@return unlocked field.
   */
  public boolean getUnlocked() {
    return this.unlocked;
  }

  /**
   * attempts to unlocked door with key.
   * And changes unlocked boolean if a match.
   *
   *@param key which player has.
   *
   *@return String from the events that occur from unlock door attemp.
   */
  public String unlock(Key k) {
    if (k.equals(this.ID)) {
      if (this.unlocked == false) {
        this.unlocked = true;
        return "The door is now unlocked.";
      }
    }
    return "You must use the correct key on the door.";
  }

  /**
   * gets state.
   *
   *@return String form of state.
   */
  @Override
  public String getStatus() {
    if (this.unlocked == false) {
      return "The door is closed and locked.";
    } else {
      return "The door is open.";
    }
  }

  /**
   * gets game object type.
   *
   *@return game object type in string form.
   */
  @Override
  public String getType() {
    if (unlocked) {
      return "Door Unlocked";
    }
    return "Door Locked";
  }

}
