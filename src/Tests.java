
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.awt.Image;
import java.io.File;

import org.junit.Test;

import gui.GUI.MoveDirection;
import main.Chest;
import main.Door;
import main.FreeSpaceTile;
import main.Key;
import main.Main;
import main.Segment;
import main.Wall;
import parser.Parser;
import renderer.Texture;

/**
 * test class for main Package.
 *
 * @author gricerile
 *
 */
public class Tests {


  /**
   * check all move directions work.
   */
  @Test
  public void movePlayerAllDirections() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upLeft);
    m.movePlayer(MoveDirection.upRight);
    m.movePlayer(MoveDirection.downLeft);
    m.movePlayer(MoveDirection.downRight);
    assertTrue("player is in the incorrect position",
        m.getPlayer().getSegment().getX() == 0 && m.getPlayer().getSegment().getY() == 0);
  }


  /**
   * fails moves in all directions.
   */
  @Test
  public void falseMove() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new Wall(), 0, 1);
    board[1][0] = new Segment(new Wall(), 1, 0);
    board[1][1] = new Segment(new Wall(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upLeft);
    m.movePlayer(MoveDirection.upRight);
    m.movePlayer(MoveDirection.downLeft);
    m.movePlayer(MoveDirection.downRight);
    assertTrue("player is in the incorrect position",
        m.getPlayer().getSegment().getX() == 0 && m.getPlayer().getSegment().getY() == 0);
  }


  /**
   * fail chest open.
   */
  @Test
  public void falseOpenChest() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.openChest();
    Chest c = (Chest) m.getSegments()[1][1].getObject();
    assertTrue("chest should not be open.", c.getStatus().equals("The chest is closed."));
  }


  /**
   * chest open.
   */
  @Test
  public void openChestUpRight() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upLeft);
    m.openChest();
    Chest c = (Chest) m.getSegments()[1][1].getObject();
    assertTrue("chest should be open.",
        c.getStatus().equals("The chest is open and there is something inside."));
  }


  /**
   * chest open.
   */
  @Test
  public void openChestUpLeft() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upRight);
    m.openChest();
    Chest c = (Chest) m.getSegments()[1][1].getObject();
    assertTrue("chest should be open.",
        c.getStatus().equals("The chest is open and there is something inside."));
  }


  /**
   * chest open.
   */
  @Test
  public void openChestdownRight() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new Chest(new Key(1, null, null)), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upLeft);
    m.movePlayer(MoveDirection.upRight);
    m.openChest();
    Chest c = (Chest) m.getSegments()[1][0].getObject();
    assertTrue("chest should be open.",
        c.getStatus().equals("The chest is open and there is something inside."));
  }


  /**
   * chest open.
   */
  @Test
  public void openChestdownLeft() {

    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new Chest(new Key(1, null, null)), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upRight);
    m.movePlayer(MoveDirection.upLeft);
    m.openChest();
    Chest c = (Chest) m.getSegments()[0][1].getObject();
    assertTrue("chest should be open.",
        c.getStatus().equals("The chest is open and there is something inside."));
  }


  /**
   * check if the loaded image is not empty.
   */
  @Test
  public void textureLoad1() {
    Texture texture = new Texture();
    Image image = texture.onLoad("grass64");

    assertTrue("image is not null", image != null);
  }


  /**
   * check if the image path is correct.
   */
  @Test
  public void textureLoad2() {
    Texture texture = new Texture();
    Image image = texture.onLoad("grass64");
    assertTrue("that is not the path", texture.toString().equals("/tx/grass64.png"));
  }


  /**
   * take from chest from certain position.
   */
  @Test
  public void takeFromChestdownLeft() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new Chest(new Key(1, null, null)), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upRight);
    m.movePlayer(MoveDirection.upLeft);
    m.openChest();
    m.takeFromChest();
    Chest c = (Chest) m.getSegments()[0][1].getObject();
    assertTrue("chest should have nothing in it.",
        c.getStatus().equals("The chest is open and it is empty."));
  }


  /**
   * take from chest from certain position.
   */
  @Test
  public void takeFromChestUpRight() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upLeft);
    m.openChest();
    m.takeFromChest();
    Chest c = (Chest) m.getSegments()[1][1].getObject();
    assertTrue("chest should have nothing in it.",
        c.getStatus().equals("The chest is open and it is empty."));
  }


  /**
   * take from chest from certain position.
   */
  @Test
  public void takeFromChestUpLeft() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upRight);
    m.openChest();
    m.takeFromChest();
    Chest c = (Chest) m.getSegments()[1][1].getObject();
    assertTrue("chest should have nothing in it.",
        c.getStatus().equals("The chest is open and it is empty."));
  }


  /**
   * take from chest from certain position.
   */
  @Test
  public void takeFromChestdownRight() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new Chest(new Key(1, null, null)), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upLeft);
    m.movePlayer(MoveDirection.upRight);
    m.openChest();
    m.takeFromChest();
    Chest c = (Chest) m.getSegments()[1][0].getObject();
    assertTrue("chest should have nothing in it.",
        c.getStatus().equals("The chest is open and it is empty."));
  }


  /**
   * take from chest twice.
   */
  @Test
  public void takeFromChestdownRightTwice() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new Chest(new Key(1, null, null)), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upLeft);
    m.movePlayer(MoveDirection.upRight);
    m.openChest();
    m.takeFromChest();
    m.takeFromChest();
    Chest c = (Chest) m.getSegments()[1][0].getObject();
    assertTrue("chest should have nothing in it.",
        c.getStatus().equals("The chest is open and it is empty."));
  }


  /**
   * open door from certain position.
   */
  @Test
  public void openDoorUpRight() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new Chest(new Key(1, null, null)), 1, 0);
    board[1][1] = new Segment(new Door(1, false), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.openChest();
    m.takeFromChest();
    m.movePlayer(MoveDirection.upLeft);
    m.playerAttempUnlock();
    Door c = (Door) m.getSegments()[1][1].getObject();
    assertTrue("Door should be open.", c.getStatus().equals("The door is open."));
  }


  /**
   * open door from certain position.
   */
  @Test
  public void openDoorUpLeft() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new Chest(new Key(1, null, null)), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new Door(1, false), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.openChest();
    m.takeFromChest();
    m.movePlayer(MoveDirection.upRight);
    m.playerAttempUnlock();
    Door c = (Door) m.getSegments()[1][1].getObject();
    assertTrue("Door should be open.", c.getStatus().equals("The door is open."));
  }


  /**
   * open door from certain position.
   */
  @Test
  public void openDoorDownRight() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    board[1][0] = new Segment(new Door(1, false), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upLeft);
    m.movePlayer(MoveDirection.upRight);
    m.getPlayer().giveKey(new Key(1, null, null), m);
    m.playerAttempUnlock();
    Door c = (Door) m.getSegments()[1][0].getObject();
    assertTrue("Door should be open.", c.getStatus().equals("The door is open."));
  }


  /**
   * open door from certain position.
   */
  @Test
  public void openDoorDownLeft() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new Door(1, false), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upRight);
    m.movePlayer(MoveDirection.upLeft);
    m.getPlayer().giveKey(new Key(1, null, null), m);
    m.playerAttempUnlock();
    Door c = (Door) m.getSegments()[0][1].getObject();
    assertTrue("Door should be open.", c.getStatus().equals("The door is open."));
  }


  /**
   * fail opening door because of no key.
   */
  @Test
  public void noKeyOpenDoor() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new Door(1, false), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.movePlayer(MoveDirection.upRight);
    m.movePlayer(MoveDirection.upLeft);
    m.playerAttempUnlock();
    Door c = (Door) m.getSegments()[0][1].getObject();
    assertTrue("Door should be open.", c.getStatus().equals("The door is closed and locked."));
  }


  /**
   * fail opening door because player is not next to door.
   */
  @Test
  public void notNextToDoorWhenTryinToOpen() {
    Segment[][] board = new Segment[2][2];
    board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
    board[0][1] = new Segment(new Door(1, false), 0, 1);
    board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
    Main m = new Main();
    m.setSegment(board);
    m.makeTestPlayer();
    m.playerAttempUnlock();
    Door c = (Door) m.getSegments()[0][1].getObject();
    assertTrue("Door should be open.", c.getStatus().equals("The door is closed and locked."));
  }


  /**
   * fail opening door because player is not next to door.
   */
  @Test
  public void saveAndLoad() {
    Main m = new Main();
    m.loadSpecificMap(new File(Parser.smallMapName));
    m.movePlayer(MoveDirection.upLeft);
    int x1 = m.getPlayer().getSegment().getX();
    int y1 = m.getPlayer().getSegment().getY();
    m.saveGame();
    m.loadSpecificMap(new File(Parser.smallMapName));
    assertFalse("They should be different values",
        x1 == m.getPlayer().getSegment().getX() && y1 == m.getPlayer().getSegment().getY());
    int x2 = m.getPlayer().getSegment().getX();
    int y2 = m.getPlayer().getSegment().getY();
    m.loadGame();
    assertFalse("They should be different values",
        x2 == m.getPlayer().getSegment().getX() && y2 == m.getPlayer().getSegment().getY());
    assertFalse("They should be different values", x2 == x1 && y2 == y1);
  }
}
