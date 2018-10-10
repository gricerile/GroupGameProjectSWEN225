package main;

import java.io.File;

import GUI.GUI;
import parser.Parser;
import renderer.Renderer;
import GUI.GUI.moveDirection;

public class Main {
	private Segment[][] segmentsBoard = new Segment[3][3]; // the size can change but for testing the size is 2 segments
	private Player player;
	private GUI g;
	private Parser p;

	private Renderer renderer;

	public static void main(String[] args) {
		Main m = new Main();
	}

	public Main() {
		this.p = new Parser();
		this.g = new GUI(this);
		p.loadMap(new File("ParsingTester.xml"));
		this.segmentsBoard = this.makeTestSegment();
		this.player = this.makeTestPlayer();
		// this.segmentsBpoard=p.getSegments(); will be created
		// this.player=p.getPlayer(); will be created, lets do this
		// this.renderer = new Renderer(this);
		// test1();
	}

	public Segment[][] getSegments() {
		return this.segmentsBoard;
	}

	public Player getPlayer() {
		return this.player;
	}

	public GUI getGUI() {
		return this.g;
	}

	public Renderer getRenderer() {
		return this.renderer;
	}

	public void movePlayer(moveDirection direction) {
		if (canMove(direction)) {
			player.move(getNextSegment(direction));
		}
		reDraw();
	}

	public boolean canMove(moveDirection direction) {
		if (this.player == null || this.player.getSegment() == null) {
			return false;
		}
		Segment next = getNextSegment(direction);
		if (next != null) {
			if (next.getObject().getType().equals("FreeTile") || next.getObject().getType().equals("Door Unlocked")) {
				return true;
			}
		}
		return false;
	}

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
				return this.segmentsBoard[xs][ys - 1];
			}
		} else if (direction == moveDirection.downRight) {
			if ((ys - 1) >= 0) {
				return this.segmentsBoard[xs - 1][ys];
			}
		}
		return null;
	}

	public void playerAttempUnlock() {

	}

	public void takeFromChest() {
		Key k = null;
		if (getNextSegment(moveDirection.upLeft) != null
				&& getNextSegment(moveDirection.upLeft).getObject().getType().equals("Chest")) {
			k = getNextSegment(moveDirection.upLeft).takeFromChest();
		} else if (getNextSegment(moveDirection.upRight) != null
				&& getNextSegment(moveDirection.upRight).getObject().getType().equals("Chest")) {
			k = getNextSegment(moveDirection.upRight).takeFromChest();
		} else if (getNextSegment(moveDirection.downLeft) != null
				&& getNextSegment(moveDirection.downLeft).getObject().getType().equals("Chest")) {
			k = getNextSegment(moveDirection.downLeft).takeFromChest();
		} else if (getNextSegment(moveDirection.downRight) != null
				&& getNextSegment(moveDirection.downRight).getObject().getType().equals("Chest")) {
			k = getNextSegment(moveDirection.downRight).takeFromChest();
		}
		if (k != null) {
			this.player.giveKey(k);
			System.out.println("Player has recieved Key with ID of: "+k.getID());
		}
	}

	public void openChest() {
		if (getNextSegment(moveDirection.upLeft) != null
				&& getNextSegment(moveDirection.upLeft).getObject().getType().equals("Chest")) {
			System.out.println(getNextSegment(moveDirection.upLeft).opensChest());
		} else if (getNextSegment(moveDirection.upRight) != null
				&& getNextSegment(moveDirection.upRight).getObject().getType().equals("Chest")) {
			System.out.println(getNextSegment(moveDirection.upRight).opensChest());
		} else if (getNextSegment(moveDirection.downLeft) != null
				&& getNextSegment(moveDirection.downLeft).getObject().getType().equals("Chest")) {
			System.out.println(getNextSegment(moveDirection.downLeft).opensChest());
		} else if (getNextSegment(moveDirection.downRight) != null
				&& getNextSegment(moveDirection.downRight).getObject().getType().equals("Chest")) {
			System.out.println(getNextSegment(moveDirection.downRight).opensChest());
		}
	}

	public void clickedScreen(int x, int y) {
		reDraw();
	}

	public void saveGame() {
		// this.p.saveGame(this.segmentsBoard,this.player);
		test1();
	}

	public void loadGame() {
		// this.p.loadMap(FileChooser());//or victor can choose the file himself in the
		// parser
	}

	public void quitGame() {
		System.exit(0);
	}

	public void reDraw() {
		this.g.getFrame().getGraphicsWindow().redraw();
	}

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
		segmentsTest[2][2] = new Segment(new FreeSpaceTile(), 2, 2);
		return segmentsTest;
	}

	public Player makeTestPlayer() {
		Player p = new Player(this.segmentsBoard[0][0], 1);
		return p;
	}

	public void test1() {
		System.out.println(this.player.getSegment().getX() + " " + this.player.getSegment().getY());
		movePlayer(moveDirection.upLeft);
		System.out.println(this.player.getSegment().getX() + " " + this.player.getSegment().getY());
		openChest();
		takeFromChest();
		playerAttempUnlock();
	}

}
