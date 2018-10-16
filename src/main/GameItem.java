package main;

public abstract class GameItem {
  private String description;
  private String name;
  /**
   *contructor of a game item.
   *
   *@param string of description.
   *
   *@param string of name(type).
   */
  public GameItem(String d, String n) {
    this.description = d;
    this.name = n;
  }

  /**
   * gets game item name.
   *
   *@return game item name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * gets game item description.
   *
   *@return game item description.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * gets game item name.
   *
   *@return game item name.
   */
  @Override
  public String toString() {
    return this.name;
  }
}
