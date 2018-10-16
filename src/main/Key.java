package main;

public class Key extends GameItem {
  // fields
  private int ID;

  /**
   * Constructor of Key.
   *
   * @param id
   *          of key that matches with door.
   *
   * @param description
   *          of game item.
   *
   * @param name
   *          of game item.
   */
  public Key(int ID, String description, String name) {
    super(description, name);
    this.ID = ID;
  }

  /**
   * gets key id.
   *
   * @return key id field.
   */
  public int getID() {
    return this.ID;
  }

  /**
   * gets game item description.
   *
   * @return game item description.
   */
  public String getDescription() {
    return super.getDescription();
  }

  /**
   * gets game item name.
   *
   * @return game item name.
   */
  public String getName() {
    return super.getName();
  }

  /**
   * compares id of door to key to find match.
   *
   * @param door's
   *          id.
   *
   * @return boolean of comparison.
   */
  public boolean equals(int doorID) {
    if (this.ID == doorID) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * gets state of key.
   *
   * @return string form of state.
   */
  public String getStatus() {
    return ("The Key unlocks/locks the door " + this.ID);
  }

  /**
   * gets type of key.
   *
   * @return string form of key.
   */
  public String getType() {
    return "Key";
  }
}
