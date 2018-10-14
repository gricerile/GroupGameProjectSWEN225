package main;

import java.awt.Graphics;

import main.Key;

public class Door implements GameObject {
  // fields
  private int ID; // code name for door and key association, unique for each door and key
  private boolean unlocked;

  public Door( int s, boolean unlocked) {
    this.ID = s;
    this.unlocked = unlocked;
  }

public int ID() {
    return this.ID;
  }

  public boolean getUnlocked() {
    return this.unlocked;
  }

  public String unlock(Key k) {
    if (k.equals(this.ID)) {
      if (this.unlocked == false) {
        this.unlocked = true;
        return "The door is now unlocked.";
      }
    }
    return "You must use the correct key on the door.";
  }

  @Override
  public String getStatus() {
    if (this.unlocked == false) {
      return "The door is closed and locked.";
    } else {
      return "The door is open.";
    }
  }

  @Override
  public String getType() {
    if (unlocked) {
      return "Door Unlocked";
    }
    return "Door Locked";
  }

}
