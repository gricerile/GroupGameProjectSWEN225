package main;

/**
 * Abstract class for items in player's inventory.
 *
 * @author gricerile
 *
 */
public abstract class GameItem {
  private String description;
  private String name;

  /**
   * constructor of a game item.
   *
   * @param d
   *          of description.
   *
   * @param n
   *          of name(type).
   */
  public GameItem(String d, String n) {
    this.description = d;
    this.name = n;
  }

  /**
   * gets game item name.
   *
   * @return game item name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * gets game item description.
   *
   * @return game item description.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * gets game item name.
   *
   * @return game item name.
   */
  @Override
  public String toString() {
    return this.name;
  }
}
