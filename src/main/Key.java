package main;

public class Key extends GameItem {
  // fields
  private int ID;

  public Key(int ID, String description, String name) {
    super(description, name);
    this.ID = ID;
  }

  public int getID() {
    return this.ID;
  }

  public boolean equals(int doorID) {
    if (this.ID == doorID) {
      return true;
    } else {
      return false;
    }
  }

  public String getStatus() {
    return ("The Key unlocks/locks the door " + this.ID);
  }

  public String getType() {
    return "Key";
  }
}
