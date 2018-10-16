package main;

/**
 * Object that player must be on to win.
 *
 * @author gricerile
 *
 */
public class WinTile implements GameObject {

  /**
   * gets game object type.
   *
   * @return String form of game object type.
   */
  @Override
  public String getStatus() {
    // TODO Auto-generated method stub
    return "Win by being on top of this tile.";
  }

  /**
   * gets game object type.
   *
   * @return String form of game object type.
   */
  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return "WinTile";
  }

}
