
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.awt.Image;
import java.io.File;

import org.junit.Test;

import GUI.GUI.moveDirection;
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
public class TestMain {

	@Test
	/**
	 * check all move directions work
	 */
	public void movePlayerAllDirections() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upLeft);
		m.movePlayer(moveDirection.upRight);
		m.movePlayer(moveDirection.downLeft);
		m.movePlayer(moveDirection.downRight);
		assertTrue("player is in the incorrect position",
				m.getPlayer().getSegment().getX() == 0 && m.getPlayer().getSegment().getY() == 0);
	}

	@Test
	/**
	 * fails moves in all directions
	 */
	public void falseMove() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new Wall(), 0, 1);
		board[1][0] = new Segment(new Wall(), 1, 0);
		board[1][1] = new Segment(new Wall(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upLeft);
		m.movePlayer(moveDirection.upRight);
		m.movePlayer(moveDirection.downLeft);
		m.movePlayer(moveDirection.downRight);
		assertTrue("player is in the incorrect position",
				m.getPlayer().getSegment().getX() == 0 && m.getPlayer().getSegment().getY() == 0);
	}

	@Test
	/**
	 * fail chest open
	 */
	public void falseOpenChest() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.openChest();
		Chest c = (Chest) m.getSegments()[1][1].getObject();
		assertTrue("chest should not be open.", c.getStatus().equals("The chest is closed."));
	}

	@Test
	/**
	 * chest open
	 */
	public void openChestUpRight() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upLeft);
		m.openChest();
		Chest c = (Chest) m.getSegments()[1][1].getObject();
		assertTrue("chest should be open.", c.getStatus().equals("The chest is open and there is something inside."));
	}

	@Test
	/**
	 * chest open
	 */
	public void openChestUpLeft() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upRight);
		m.openChest();
		Chest c = (Chest) m.getSegments()[1][1].getObject();
		assertTrue("chest should be open.", c.getStatus().equals("The chest is open and there is something inside."));
	}

	@Test
	/**
	 * chest open
	 */
	public void openChestdownRight() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new Chest(new Key(1, null, null)), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upLeft);
		m.movePlayer(moveDirection.upRight);
		m.openChest();
		Chest c = (Chest) m.getSegments()[1][0].getObject();
		assertTrue("chest should be open.", c.getStatus().equals("The chest is open and there is something inside."));
	}

	@Test
	/**
	 * chest open
	 */
	public void openChestdownLeft() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new Chest(new Key(1, null, null)), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upRight);
		m.movePlayer(moveDirection.upLeft);
		m.openChest();
		Chest c = (Chest) m.getSegments()[0][1].getObject();
		assertTrue("chest should be open.", c.getStatus().equals("The chest is open and there is something inside."));
	}

	@Test
	/**
	 * check if the loaded image is not empty
	 */
	public void textureLoad1() {
		Texture texture = new Texture();
		Image image = texture.onLoad("grass64");

		assertTrue("image is not null", image != null);
	}

	@Testm.getPlayer().getSegment().getY())
	/**
	 * check if the image path is correct.
	 */
	public void textureLoad2() {
		Texture texture = new Texture();
		Image image = texture.onLoad("grass64");
		assertTrue("that is not the path", texture.toString().equals("/tx/grass64.png"));
	}

	@Test
	/**
	 * take from chest from certain position
	 */
	public void takeFromChestdownLeft() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new Chest(new Key(1, null, null)), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upRight);
		m.movePlayer(moveDirection.upLeft);
		m.openChest();
		m.takeFromChest();
		Chest c = (Chest) m.getSegments()[0][1].getObject();
		assertTrue("chest should have nothing in it.", c.getStatus().equals("The chest is open and it is empty."));
	}

	@Test
	/**
	 * take from chest from certain position
	 */
	public void takeFromChestUpRight() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upLeft);
		m.openChest();
		m.takeFromChest();
		Chest c = (Chest) m.getSegments()[1][1].getObject();
		assertTrue("chest should have nothing in it.", c.getStatus().equals("The chest is open and it is empty."));
	}

	@Test
	/**
	 * take from chest from certain position
	 */
	public void takeFromChestUpLeft() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new Chest(new Key(1, null, null)), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upRight);
		m.openChest();
		m.takeFromChest();
		Chest c = (Chest) m.getSegments()[1][1].getObject();
		assertTrue("chest should have nothing in it.", c.getStatus().equals("The chest is open and it is empty."));
	}

	@Test
	/**
	 * take from chest from certain position
	 */
	public void takeFromChestdownRight() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new Chest(new Key(1, null, null)), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upLeft);
		m.movePlayer(moveDirection.upRight);
		m.openChest();
		m.takeFromChest();
		Chest c = (Chest) m.getSegments()[1][0].getObject();
		assertTrue("chest should have nothing in it.", c.getStatus().equals("The chest is open and it is empty."));
	}

	@Test
	/**
	 * take from chest twice
	 */
	public void takeFromChestdownRightTwice() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new Chest(new Key(1, null, null)), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upLeft);
		m.movePlayer(moveDirection.upRight);
		m.openChest();
		m.takeFromChest();
		m.takeFromChest();
		Chest c = (Chest) m.getSegments()[1][0].getObject();
		assertTrue("chest should have nothing in it.", c.getStatus().equals("The chest is open and it is empty."));
	}

	@Test
	/**
	 * open door from certain position
	 */
	public void openDoorUpRight() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new Chest(new Key(1, null, null)), 1, 0);
		board[1][1] = new Segment(new Door(1, false), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.openChest();
		m.takeFromChest();
		m.movePlayer(moveDirection.upLeft);
		m.playerAttempUnlock();
		Door c = (Door) m.getSegments()[1][1].getObject();
		assertTrue("Door should be open.", c.getStatus().equals("The door is open."));
	}

	@Test
	/**
	 * open door from certain position
	 */
	public void openDoorUpLeft() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new Chest(new Key(1, null, null)), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new Door(1, false), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.openChest();
		m.takeFromChest();
		m.movePlayer(moveDirection.upRight);
		m.playerAttempUnlock();
		Door c = (Door) m.getSegments()[1][1].getObject();
		assertTrue("Door should be open.", c.getStatus().equals("The door is open."));
	}

	@Test
	/**
	 * open door from certain position
	 */
	public void openDoorDownRight() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		board[1][0] = new Segment(new Door(1, false), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upLeft);
		m.movePlayer(moveDirection.upRight);
		m.getPlayer().giveKey(new Key(1, null, null), m);
		m.playerAttempUnlock();
		Door c = (Door) m.getSegments()[1][0].getObject();
		assertTrue("Door should be open.", c.getStatus().equals("The door is open."));
	}

	@Test
	/**
	 * open door from certain position
	 */
	public void openDoorDownLeft() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new Door(1, false), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upRight);
		m.movePlayer(moveDirection.upLeft);
		m.getPlayer().giveKey(new Key(1, null, null), m);
		m.playerAttempUnlock();
		Door c = (Door) m.getSegments()[0][1].getObject();
		assertTrue("Door should be open.", c.getStatus().equals("The door is open."));
	}

	@Test
	/**
	 * fail opening door because of no key
	 */
	public void noKeyOpenDoor() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new Door(1, false), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.movePlayer(moveDirection.upRight);
		m.movePlayer(moveDirection.upLeft);
		m.playerAttempUnlock();
		Door c = (Door) m.getSegments()[0][1].getObject();
		assertTrue("Door should be open.", c.getStatus().equals("The door is closed and locked."));
	}

	@Test
	/**
	 * fail opening door because player is not next to door
	 */
	public void notNextToDoorWhenTryinToOpen() {
		Main m = new Main();
		Segment[][] board = new Segment[2][2];
		board[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		board[0][1] = new Segment(new Door(1, false), 0, 1);
		board[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
		board[1][1] = new Segment(new FreeSpaceTile(), 1, 1);
		m.setSegment(board);
		m.makeTestPlayer();
		m.playerAttempUnlock();
		Door c = (Door) m.getSegments()[0][1].getObject();
		assertTrue("Door should be open.", c.getStatus().equals("The door is closed and locked."));
	}

	@Test
	/**
	 * fail opening door because player is not next to door
	 */
	public void SaveAndLoad() {
		Main m = new Main();
		m.loadSpecificMap(new File(Parser.smallMapName));
		m.movePlayer(moveDirection.upLeft);
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
		assertFalse("They should be different values",
				x2 == x1 && y2 == y1);
	}
}
