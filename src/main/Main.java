package main;

import java.io.File;
import java.io.FileNotFoundException;

import GUI.GUI;
import parser.Parser;
import renderer.Renderer;
import GUI.GUI.moveDirection;

import javax.xml.stream.XMLStreamException;

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
		p.loadMap(new File(Parser.testMapFileName));
		this.segmentsBoard = this.makeTestSegment();
		this.player = this.p.loadPlayer(new File(Parser.playerLocationName));
		// this.segmentsBpoard=p.getSegments(); will be created
		// this.player=p.getPlayer(); will be created, lets do this
		this.renderer = new Renderer(this);
		// this.makeTestSegment();
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
			this.player.getSegment().setHasPlayer(false);
			for(int i=0;i<this.segmentsBoard.length;i++) {
        for(int j=0;j<this.segmentsBoard.length;j++) {
			if (segmentsBoard[i][j] != null) {
				if (this.player.getSegment().equals(this.segmentsBoard[i][j])) {
					this.segmentsBoard[i][j].setHasPlayer(false);
				}
			}
        }
      }
			this.player.move(getNextSegment(direction));
			this.player.getSegment().setHasPlayer(true);
			//this.renderer.drawMove(direction);

		}
		reDraw();
		if(checkWin()) {
			this.g.getFrame().getEventOutputPanel().setTextOutput("Player has Won!");
		}
	}

	public boolean checkWin() {
		for(int i=0;i<this.segmentsBoard.length;i++) {
	        for(int j=0;j<this.segmentsBoard.length;j++) {
				if (segmentsBoard[i][j] != null) {
					if (this.player.getSegment().equals(this.segmentsBoard[i][j])) {
						if (this.segmentsBoard[i][j].getObject().getType().equals("WinTile")) {
							return true;
						}
					}
				}
	        }
	      }
		return false;
	}

	public boolean canMove(moveDirection direction) {
		if (this.player == null || this.player.getSegment() == null) {
			return false;
		}
		Segment next = getNextSegment(direction);
		if (next != null) {
			if (next.getObject().getType().equals("FreeTile") || next.getObject().getType().equals("Door Unlocked") || next.getObject().getType().equals("WinTile")) {
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
				return this.segmentsBoard[xs-1][ys];
			}
		} else if (direction == moveDirection.downRight) {
			if ((ys - 1) >= 0) {
				return this.segmentsBoard[xs][ys-1];
			}
		}
		return null;
	}

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
		}
		else{
			this.g.getFrame().getEventOutputPanel().setTextOutput("There is no door adjacent to the Player.");
		}
	}

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
			this.g.getFrame().getEventOutputPanel().setTextOutput(this.player.giveKey(k,this));
			//System.out.println("Player has recieved Key with ID of: " + k.getID());
		} else if (k==null) {
			this.g.getFrame().getEventOutputPanel().setTextOutput("You have got nothing from the Chest.");
		}
		reDraw();
	}

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
			//System.out.println("There is no Chest nearby.");
		}
		reDraw();
	}

	public void clickedScreen(int x, int y) {
		reDraw();
	}

	public void saveGame() {
		try {
			p.saveMap(segmentsBoard);
			p.savePlayer(player);
		}
		catch (FileNotFoundException e) {}
		catch (XMLStreamException e) {}

	}

	public void loadGame() {
		segmentsBoard = this.p.loadMap(new File(Parser.dungeonSaveName));
		player = this.p.loadPlayer(new File(Parser.playerSaveLocationName));
		reDraw();
	}

	public void quitGame() {
		//test1();
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
		segmentsTest[2][2] = new Segment(new WinTile(), 2, 2);
		//return segmentsTest;
		return p.loadMap(new File(Parser.testMapFileName));
	}

	public Player makeTestPlayer() {
		Player p = new Player(this.segmentsBoard[0][0]);
		return p;
	}

	public void test1() {
		System.out.println("Test begining");
		System.out.println("Player Starts at: " + this.player.getSegment().getX() + " " + this.player.getSegment().getY());
		movePlayer(moveDirection.upLeft);
		System.out.println("Player moved to: " + this.player.getSegment().getX() + " " + this.player.getSegment().getY());
    System.out.println(this.segmentsBoard[0][2].getObject().getType());
		openChest();
		takeFromChest();
		System.out.println(this.segmentsBoard[0][2].getObject().getType());
		takeFromChest();
		playerAttempUnlock();
		movePlayer(moveDirection.upRight);
		System.out.println("Player moved to: " + this.player.getSegment().getX() + " " + this.player.getSegment().getY());
		movePlayer(moveDirection.upRight);
		System.out.println("Player moved to: " + this.player.getSegment().getX() + " " + this.player.getSegment().getY());
		movePlayer(moveDirection.upLeft);
		System.out.println("Player moved to: " + this.player.getSegment().getX() + " " + this.player.getSegment().getY());
	}

}
