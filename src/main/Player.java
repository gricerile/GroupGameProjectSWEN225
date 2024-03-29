package main;

import java.util.ArrayList;

/**
 * Player is the users character, filled with moves and actions.
 *
 * @author gricerile
 *
 */
public class Player {
  // fields
  private ArrayList<GameItem> inventory = new ArrayList<GameItem>();
  private Segment segment;

  /**
   * Constructor of Player.
   *
   * @param s
   *          the player will spawn on.
   */
  public Player(Segment s) {
    this.segment = s;
  }

  /**
   * gets the segment the player is on.
   *
   * @return segment field.
   */
  public Segment getSegment() {
    return this.segment;
  }

  /**
   * sets segment field to new segment.
   *
   * @param s
   *          segment
   */
  public void move(Segment s) {
    this.segment = s;
  }

  /**
   * gets inventory of the player.
   *
   * @return the inventory field.
   */
  public ArrayList<GameItem> getInventory() {
    return this.inventory;
  }

  /**
   * Gives only new keys into the inventory.
   *
   * @param k
   *          key
   *
   * @param m
   *          to alter inventory event panel.
   *
   * @return String of events that happened when attempting to add key.
   */
  public String giveKey(Key k, Main m) {
    if (!this.inventory.contains(k)) {
      this.inventory.add(k);
      m.getGui().getFrame().getInventoryPanel().addItemToInventory(k);
      return "Key " + k.getID() + " is the Inventory.";
    }
    return "The Key is already in the Inventory.";
  }

}
