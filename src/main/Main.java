package main;

import java.io.File;
import java.io.FileNotFoundException;

import GUI.GUI;
import parser.Parser;
import renderer.Renderer;
import GUI.GUI.moveDirection;
import javax.xml.stream.XMLStreamException;

/**
 * The main is in charge of combining the game logic and renderer, GUI and
 * parser when needed.
 * @author gricerile
 */
public class Main {

	private Segment[][] segmentsBoard = new Segment[3][3]; // the size can change but for testing
	// the size is 2 segments
	private Player player;
	private GUI g;
	private Parser p;
	private Segment winTile;
	private Renderer renderer;

	/**
	 * The beginning of the program starts here.
	 *
	 * @param The
	 *            argument to start the program is here.
	 */
	public static void main(String[] args) {
		Main m = new Main();
	}


	public void setSegment(Segment[][] segments) {
		this.segmentsBoard=segments;
	}

	/**
	 * constructor of the main, does not need any parameters and creates if own
	 * fields it needs.
	 */
	public Main() {
		this.p = new Parser(this);
		this.g = new GUI(this);
		p.loadMap(new File(Parser.smallMapName));
		this.segmentsBoard = this.makeTestSegment();
		// this.segmentsBoard = this.makeBoard();
		// this.player = new Player(segmentsBoard[0][0]);
		this.player = this.p.loadPlayer(new File(Parser.playerLocationName),new File(Parser.inventoryStartDataName));
		// this.segmentsBpoard=p.getSegments(); will be created
		// this.player=p.getPlayer(); will be created, lets do this
		this.renderer = new Renderer(this);
		this.winTile = findWinTile();
		// this.makeTestSegment();
		// test1();
	}

	/**
	 * Getter for the segment array field.
	 *
	 * @return the array of segments.
	 */
	public Segment[][] getSegments() {
		return this.segmentsBoard;
	}

	/**
	 * Getter for the player field in main.
	 *
	 * @return The player field.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Getter for the GUI field in main.
	 *
	 * @return the GUI field.
	 */
	public GUI getGUI() {
		return this.g;
	}

	/**
	 * Getter for the GUI field in main.
	 *
	 * @return the renderer field.
	 */
	public Renderer getRenderer() {
		return this.renderer;
	}

	/**
	 * Method to move player, assisted by helped methods to move the to a valid
	 * tile.
	 *
	 * @param The
	 *            direction from the button pressed.
	 */
	public void movePlayer(moveDirection direction) {
		if (canMove(direction)) {
			this.player.getSegment().setHasPlayer(false);
			this.player.move(getNextSegment(direction));
			this.player.getSegment().setHasPlayer(true);
			// this.renderer.drawMove(direction);

		}
		reDraw();
		if (checkWin()) {
			//this.g.getFrame().getEventOutputPanel().setTextOutput("Player has Won!");
			this.g.getFrame().getGraphicsWindow().setHasWon(true);
		}
	}

	/**
	 * Method to see if the player has won and is on the win tile.
	 *
	 * @return a boolean whether the player is on the win tile or not.
	 */
	public boolean checkWin() {
		if (this.winTile != null) {
			if (this.player.getSegment().equals(this.winTile)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds the win tile once so it can be accessed more efficiently.
	 *
	 * @return Win tile segment.
	 */
	public Segment findWinTile() {
		for (int i = 0; i < this.segmentsBoard.length; i++) {
			for (int j = 0; j < this.segmentsBoard.length; j++) {
				if (segmentsBoard[i][j] != null) {
					if (this.segmentsBoard[i][j].getObject().getType().equals("WinTile")) {
						return this.segmentsBoard[i][j];
					}
				}
			}
		}
		return null;
	}

	/**
	 * Make sure the player is going to a valid tile or not.
	 *
	 * @param The
	 *            direction of the button pressed.
	 *
	 * @return a boolean if the player can move to the desired direction.
	 */
	public boolean canMove(moveDirection direction) {
		if (this.player == null || this.player.getSegment() == null) {
			return false;
		}
		Segment next = getNextSegment(direction);
		if (next != null) {
			if (next.getObject().getType().equals("FreeTile") || next.getObject().getType().equals("Door Unlocked")
					|| next.getObject().getType().equals("WinTile")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Helper method to grab tile quickly which is next to the player.
	 *
	 * @param Direction
	 *            of button pressed by the user.
	 *
	 * @return the next segment from the direction chosen.
	 */
	public Segment getNextSegment(moveDirection direction) {// this only works for 3 by 3 double array
		int xs = this.player.getSegment().getX();
		int ys = this.player.getSegment().getY();

		if (direction == moveDirection.upLeft) {
			if ((ys + 1) < this.segmentsBoard.length) {
				return this.segmentsBoard[xs][ys + 1];
			}
		} else if (direction == moveDirection.upRight) {
			if ((xs + 1) < this.segmentsBoard.length) {
				return this.segmentsBoard[xs + 1][ys];
			}
		} else if (direction == moveDirection.downLeft) {
			if ((xs - 1) >= 0) {
				return this.segmentsBoard[xs - 1][ys];
			}
		} else if (direction == moveDirection.downRight) {
			if ((ys - 1) >= 0) {
				return this.segmentsBoard[xs][ys - 1];
			}
		}
		return null;
	}

	/**
	 * Method which unlocks a door and alerts the player of the result.
	 *
	 */
	public void playerAttempUnlock() {
		if (getNextSegment(moveDirection.upLeft) != null
				&& getNextSegment(moveDirection.upLeft).getObject().getType().equals("Door Locked")) {
			this.g.getFrame().getEventOutputPanel().setTextOutput(unlock(getNextSegment(moveDirection.upLeft)));
		} else if (getNextSegment(moveDirection.upRight) != null
				&& getNextSegment(moveDirection.upRight).getObject().getType().equals("Door Locked")) {
			this.g.getFrame().getEventOutputPanel().setTextOutput(unlock(getNextSegment(moveDirection.upRight)));
		} else if (getNextSegment(moveDirection.downLeft) != null
				&& getNextSegment(moveDirection.downLeft).getObject().getType().equals("Door Locked")) {
			this.g.getFrame().getEventOutputPanel().setTextOutput(unlock(getNextSegment(moveDirection.downLeft)));
		} else if (getNextSegment(moveDirection.downRight) != null
				&& getNextSegment(moveDirection.downRight).getObject().getType().equals("Door Locked")) {
			this.g.getFrame().getEventOutputPanel().setTextOutput(unlock(getNextSegment(moveDirection.downRight)));
		} else {
			this.g.getFrame().getEventOutputPanel().setTextOutput("There is no door adjacent to the Player.");
		}
	}

	/**
	 * Helper method to unlock door and get event String from the result.
	 *
	 * @param segment
	 *            with door, desired to be unlocked.
	 *
	 * @return event occurrence of what happened to the door.
	 */
	public String unlock(Segment s) {
		Door d = (Door) s.getObject();
		for (GameItem g : player.getInventory()) {
			if (g instanceof Key) {
				Key k = (Key) g;
				d.unlock(k);
				if (d.getUnlocked()) {
					reDraw();
					return ("Door (ID: " + d.ID() + ") is unlocked");
				}
			}
		}
		return "Player does not have the right Key.";
	}

	/**
	 * Method to take a key from a opened chest and give to the player while also
	 * sending event string to be displayed to the user.
	 *
	 */
	public void takeFromChest() {
		Key k = null;
		if (getNextSegment(moveDirection.upLeft) != null
				&& getNextSegment(moveDirection.upLeft).getObject().getType().equals("RedChest")) {
			k = getNextSegment(moveDirection.upLeft).takeFromChest();
		} else if (getNextSegment(moveDirection.upRight) != null
				&& getNextSegment(moveDirection.upRight).getObject().getType().equals("RedChest")) {
			k = getNextSegment(moveDirection.upRight).takeFromChest();
		} else if (getNextSegment(moveDirection.downLeft) != null
				&& getNextSegment(moveDirection.downLeft).getObject().getType().equals("RedChest")) {
			k = getNextSegment(moveDirection.downLeft).takeFromChest();
		} else if (getNextSegment(moveDirection.downRight) != null
				&& getNextSegment(moveDirection.downRight).getObject().getType().equals("RedChest")) {
			k = getNextSegment(moveDirection.downRight).takeFromChest();
		}
		if (k != null) {
			this.g.getFrame().getEventOutputPanel().setTextOutput(this.player.giveKey(k, this));
			// System.out.println("Player has recieved Key with ID of: " + k.getID());
		} else if (k == null) {
			this.g.getFrame().getEventOutputPanel().setTextOutput("You have got nothing from the Chest.");
		}
		reDraw();
	}

	/**
	 * Method to open an unopened chest and give event result to user.
	 */
	public void openChest() {
		if (getNextSegment(moveDirection.upLeft) != null
				&& getNextSegment(moveDirection.upLeft).getObject().getType().equals("YellowChest")) {
			this.g.getFrame().getEventOutputPanel().setTextOutput(getNextSegment(moveDirection.upLeft).opensChest());
		} else if (getNextSegment(moveDirection.upRight) != null
				&& getNextSegment(moveDirection.upRight).getObject().getType().equals("YellowChest")) {
			this.g.getFrame().getEventOutputPanel().setTextOutput(getNextSegment(moveDirection.upRight).opensChest());
		} else if (getNextSegment(moveDirection.downLeft) != null
				&& getNextSegment(moveDirection.downLeft).getObject().getType().equals("YellowChest")) {
			this.g.getFrame().getEventOutputPanel().setTextOutput(getNextSegment(moveDirection.downLeft).opensChest());
		} else if (getNextSegment(moveDirection.downRight) != null
				&& getNextSegment(moveDirection.downRight).getObject().getType().equals("YellowChest")) {
			this.g.getFrame().getEventOutputPanel().setTextOutput(getNextSegment(moveDirection.downRight).opensChest());
		} else {
			this.g.getFrame().getEventOutputPanel().setTextOutput("There is no yellow chest to open nearby.");
			// System.out.println("There is no Chest nearby.");
		}
		reDraw();
	}

	/**
	 * Gets the int coords from the click on the screen. Is not being used in most
	 * current version.
	 *
	 * @param x
	 *            coord.
	 *
	 * @param y
	 *            coord.
	 */
	public void clickedScreen(int x, int y) {
		reDraw();
	}

	/**
	 * Saves game in file.
	 *
	 */
	public void saveGame() {
		try {
			p.saveMap(segmentsBoard);
			p.savePlayer(player);
		} catch (FileNotFoundException e) {
		} catch (XMLStreamException e) {
		}

	}

	/**
	 * Loads game from a chosen File.
	 *
	 * @param chosen
	 *            file.
	 */
	public void loadSpecificMap(File file) {
	  this.g.getFrame().getGraphicsWindow().setHasWon(false);
	  winTile = findWinTile();
		if (!file.getName().equals(Parser.smallMapName) && !file.getName().equals(Parser.largeMapName)
				&& !file.getName().equals(Parser.mediumMapName)) {
			System.out.println("Invalid file specified.");
			this.g.getFrame().getEventOutputPanel().setTextOutput("Invalid Starting File Specified");
		} else {
			segmentsBoard = this.p.loadMap(file);
			player = this.p.loadPlayer(new File(Parser.playerLocationName), new File(Parser.inventoryStartDataName));
			this.g.getFrame().getEventOutputPanel().setTextOutput("");
			reDraw();
		}
	}

	/**
	 * Loads game from file.
	 *
	 */
	public void loadGame() {
	   this.g.getFrame().getGraphicsWindow().setHasWon(false);
	   winTile = findWinTile();
		segmentsBoard = this.p.loadMap(new File(Parser.dungeonSaveName));
		player = this.p.loadPlayer(new File(Parser.playerSaveLocationName), new File(Parser.inventorySaveDataName));
		reDraw();
	}

	/**
	 * Quits game.
	 */
	public void quitGame() {
		// test1();
		System.exit(0);
	}

	/**
	 * Calls gui to redraw the game.
	 */
	public void reDraw() {
		this.g.getFrame().getGraphicsWindow().redraw();
	}

	/**
	 * Makes test case for game.
	 *
	 * @return an Array of segments.
	 */
	public Segment[][] makeTestSegment() {
		Segment[][] segmentsTest = new Segment[3][3];
		segmentsTest[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
		segmentsTest[1][0] = new Segment(new Wall(), 1, 0);
		segmentsTest[2][0] = new Segment(new Wall(), 2, 0);
		segmentsTest[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
		segmentsTest[1][1] = new Segment(new Door(101, false), 1, 1);
		segmentsTest[2][1] = new Segment(new FreeSpaceTile(), 2, 1);
		segmentsTest[0][2] = new Segment(new Chest(new Key(101, "Key opens door 101", "Key ID 101")), 0, 2);
		segmentsTest[1][2] = new Segment(new Wall(), 1, 2);
		segmentsTest[2][2] = new Segment(new WinTile(), 2, 2);
		// return segmentsTest;
		return p.loadMap(new File(Parser.smallMapName));
	}

	/**
	 * Makes player for test along side segment maker tests.
	 *
	 * @return Player on set 0,0 segment.
	 */
	public Player makeTestPlayer() {
		Player p = new Player(this.segmentsBoard[0][0]);
		return p;
	}
}
