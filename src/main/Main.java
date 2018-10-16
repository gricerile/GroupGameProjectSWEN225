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
		//this.segmentsBoard = this.makeBoard();
		//this.player = new Player(segmentsBoard[0][0]);
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

	/*
	public Segment[][] makeBoard(){
    Segment[][] segmentBoard = new Segment[20][20];
    segmentBoard[0][0] = new Segment(new FreeSpaceTile(), 0, 0);
//    segmentBoard[0][0].setHasPlayer(true);
    segmentBoard[1][0] = new Segment(new FreeSpaceTile(), 1, 0);
    segmentBoard[2][0] = new Segment(new FreeSpaceTile(), 2, 0);
    segmentBoard[3][0] = new Segment(new FreeSpaceTile(), 3, 0);
    segmentBoard[4][0] = new Segment(new FreeSpaceTile(), 4, 0);
    segmentBoard[5][0] = new Segment(new FreeSpaceTile(), 5, 0);
    segmentBoard[6][0] = new Segment(new FreeSpaceTile(), 6, 0);
    segmentBoard[7][0] = new Segment(new FreeSpaceTile(), 7, 0);
    segmentBoard[8][0] = new Segment(new FreeSpaceTile(), 8, 0);
    segmentBoard[9][0] = new Segment(new FreeSpaceTile(), 9, 0);
    segmentBoard[10][0] = new Segment(new FreeSpaceTile(), 10, 0);
    segmentBoard[11][0] = new Segment(new FreeSpaceTile(), 11, 0);
    segmentBoard[12][0] = new Segment(new FreeSpaceTile(), 12, 0);
    segmentBoard[13][0] = new Segment(new FreeSpaceTile(), 13, 0);
    segmentBoard[14][0] = new Segment(new FreeSpaceTile(), 14, 0);
    segmentBoard[15][0] = new Segment(new Wall(), 15, 0);
    segmentBoard[16][0] = new Segment(new Wall(), 16, 0);
    segmentBoard[17][0] = new Segment(new Wall(), 17, 0);
    segmentBoard[18][0] = new Segment(new Wall(), 18, 0);
    segmentBoard[19][0] = new Segment(new Wall(), 19, 0);

    segmentBoard[0][1] = new Segment(new FreeSpaceTile(), 0, 1);
    segmentBoard[1][1] = new Segment(new Wall(), 1, 1);
    segmentBoard[2][1] = new Segment(new Wall(), 2, 1);
    segmentBoard[3][1] = new Segment(new Wall(), 3, 1);
    segmentBoard[4][1] = new Segment(new Wall(), 4, 1);
    segmentBoard[5][1] = new Segment(new Wall(), 5, 1);
    segmentBoard[6][1] = new Segment(new Wall(), 6, 1);
    segmentBoard[7][1] = new Segment(new Wall(), 7, 1);
    segmentBoard[8][1] = new Segment(new FreeSpaceTile(), 8, 1);
    segmentBoard[9][1] = new Segment(new Wall(), 9, 1);
    segmentBoard[10][1] = new Segment(new FreeSpaceTile(), 10, 1);
    segmentBoard[11][1] = new Segment(new Wall(), 11, 1);
    segmentBoard[12][1] = new Segment(new Wall(), 12, 1);
    segmentBoard[13][1] = new Segment(new FreeSpaceTile(), 13, 1);
    segmentBoard[14][1] = new Segment(new FreeSpaceTile(), 14, 1);
    segmentBoard[15][1] = new Segment(new Wall(), 15, 1);
    segmentBoard[16][1] = new Segment(new FreeSpaceTile(), 16, 1);
    segmentBoard[17][1] = new Segment(new FreeSpaceTile(), 17, 1);
    segmentBoard[18][1] = new Segment(new FreeSpaceTile(), 18, 1);
    segmentBoard[19][1] = new Segment(new FreeSpaceTile(), 19, 1);

    segmentBoard[0][2] = new Segment(new FreeSpaceTile(), 0, 2);
    segmentBoard[1][2] = new Segment(new FreeSpaceTile(), 1, 2);
    segmentBoard[2][2] = new Segment(new Wall(), 2, 2);
    segmentBoard[3][2] = new Segment(new FreeSpaceTile(), 3, 2);
    segmentBoard[4][2] = new Segment(new FreeSpaceTile(), 4, 2);
    segmentBoard[5][2] = new Segment(new FreeSpaceTile(), 5, 2);
    segmentBoard[6][2] = new Segment(new FreeSpaceTile(), 6, 2);
    segmentBoard[7][2] = new Segment(new FreeSpaceTile(), 7, 2);
    segmentBoard[8][2] = new Segment(new FreeSpaceTile(), 8, 2);
    segmentBoard[9][2] = new Segment(new Wall(), 9, 2);
    segmentBoard[10][2] = new Segment(new FreeSpaceTile(), 10, 2);
    segmentBoard[11][2] = new Segment(new Wall(), 11, 2);
    segmentBoard[12][2] = new Segment(new FreeSpaceTile(), 12, 2);
    segmentBoard[13][2] = new Segment(new FreeSpaceTile(), 13, 2);
    segmentBoard[14][2] = new Segment(new FreeSpaceTile(), 14, 2);
    segmentBoard[15][2] = new Segment(new FreeSpaceTile(), 15, 2);
    segmentBoard[16][2] = new Segment(new FreeSpaceTile(), 16, 2);
    segmentBoard[17][2] = new Segment(new FreeSpaceTile(), 17, 2);
    segmentBoard[18][2] = new Segment(new FreeSpaceTile(), 18, 2);
    segmentBoard[19][2] = new Segment(new FreeSpaceTile(), 19, 2);

    segmentBoard[0][3] = new Segment(new Wall(), 0, 3);
    segmentBoard[1][3] = new Segment(new Wall(), 1, 3);
    segmentBoard[2][3] = new Segment(new Wall(), 2, 3);
    segmentBoard[3][3] = new Segment(new FreeSpaceTile(), 3, 3);
    segmentBoard[4][3] = new Segment(new Wall(), 4, 3);
    segmentBoard[5][3] = new Segment(new Wall(), 5, 3);
    segmentBoard[6][3] = new Segment(new Wall(), 6, 3);
    segmentBoard[7][3] = new Segment(new Wall(), 7, 3);
    segmentBoard[8][3] = new Segment(new FreeSpaceTile(), 8, 3);
    segmentBoard[9][3] = new Segment(new Wall(), 9, 3);
    segmentBoard[10][3] = new Segment(new FreeSpaceTile(), 10, 3);
    segmentBoard[10][3] = new Segment(new Wall(), 10, 3);
    segmentBoard[11][3] = new Segment(new FreeSpaceTile(), 11, 3);
    segmentBoard[12][3] = new Segment(new FreeSpaceTile(), 12, 3);
    segmentBoard[13][3] = new Segment(new Wall(), 13, 3);
    segmentBoard[14][3] = new Segment(new Wall(), 14, 3);
    segmentBoard[15][3] = new Segment(new Wall(), 15, 3);
    segmentBoard[16][3] = new Segment(new Wall(), 16, 3);
    segmentBoard[17][3] = new Segment(new Wall(), 17, 3);
    segmentBoard[18][3] = new Segment(new Wall(), 18, 3);
    segmentBoard[19][3] = new Segment(new FreeSpaceTile(), 19, 3);

    segmentBoard[0][4] = new Segment(new FreeSpaceTile(), 0, 4);
    segmentBoard[1][4] = new Segment(new FreeSpaceTile(), 1, 4);
    segmentBoard[2][4] = new Segment(new FreeSpaceTile(), 2, 4);
    segmentBoard[3][4] = new Segment(new FreeSpaceTile(), 3, 4);
    segmentBoard[4][4] = new Segment(new FreeSpaceTile(), 4, 4);
    segmentBoard[5][4] = new Segment(new Wall(), 5, 4);
    segmentBoard[6][4] = new Segment(new FreeSpaceTile(), 6, 4);
    segmentBoard[7][4] = new Segment(new FreeSpaceTile(), 7, 4);
    segmentBoard[8][4] = new Segment(new FreeSpaceTile(), 8, 4);
    segmentBoard[9][4] = new Segment(new Wall(), 9, 4);
    segmentBoard[10][4] = new Segment(new FreeSpaceTile(), 10, 4);
    segmentBoard[11][4] = new Segment(new Wall(), 11, 4);
    segmentBoard[12][4] = new Segment(new FreeSpaceTile(), 12, 4);
    segmentBoard[13][4] = new Segment(new FreeSpaceTile(), 13, 4);
    segmentBoard[14][4] = new Segment(new FreeSpaceTile(), 14, 4);
    segmentBoard[15][4] = new Segment(new Wall(), 15, 4);
    segmentBoard[16][4] = new Segment(new FreeSpaceTile(), 16, 4);
    segmentBoard[17][4] = new Segment(new FreeSpaceTile(), 17, 4);
    segmentBoard[18][4] = new Segment(new Wall(), 18, 4);
    segmentBoard[19][4] = new Segment(new FreeSpaceTile(), 19, 4);

    segmentBoard[0][5] = new Segment(new FreeSpaceTile(), 0, 5);
    segmentBoard[1][5] = new Segment(new Wall(), 1, 5);
    segmentBoard[2][5] = new Segment(new FreeSpaceTile(), 2, 5);
    segmentBoard[3][5] = new Segment(new Wall(), 3, 5);
    segmentBoard[4][5] = new Segment(new FreeSpaceTile(), 4, 5);
    segmentBoard[5][5] = new Segment(new Wall(), 5, 5);
    segmentBoard[6][5] = new Segment(new Wall(), 6, 5);
    segmentBoard[7][5] = new Segment(new Wall(), 7, 5);
    segmentBoard[8][5] = new Segment(new Wall(), 8, 5);
    segmentBoard[9][5] = new Segment(new Wall(), 9, 5);
    segmentBoard[10][5] = new Segment(new FreeSpaceTile(), 10, 5);
    segmentBoard[11][5] = new Segment(new Wall(), 11, 5);
    segmentBoard[12][5] = new Segment(new Wall(), 12, 5);
    segmentBoard[13][5] = new Segment(new Wall(), 13, 5);
    segmentBoard[14][5] = new Segment(new Wall(), 14, 5);
    segmentBoard[15][5] = new Segment(new Wall(), 15, 5);
    segmentBoard[16][5] = new Segment(new FreeSpaceTile(), 16, 5);
    segmentBoard[17][5] = new Segment(new FreeSpaceTile(), 17, 5);
    segmentBoard[18][5] = new Segment(new Wall(), 18, 5);
    segmentBoard[19][5] = new Segment(new FreeSpaceTile(), 19, 5);

    segmentBoard[0][6] = new Segment(new FreeSpaceTile(), 0, 6);
    segmentBoard[1][6] = new Segment(new Wall(), 1, 6);
    segmentBoard[2][6] = new Segment(new FreeSpaceTile(), 2, 6);
    segmentBoard[3][6] = new Segment(new Wall(), 3, 6);
    segmentBoard[4][6] = new Segment(new FreeSpaceTile(), 4, 6);
    segmentBoard[5][6] = new Segment(new FreeSpaceTile(), 5, 6);
    segmentBoard[6][6] = new Segment(new FreeSpaceTile(), 6, 6);
    segmentBoard[7][6] = new Segment(new FreeSpaceTile(), 7, 6);
    segmentBoard[8][6] = new Segment(new FreeSpaceTile(), 8, 6);
    segmentBoard[9][6] = new Segment(new FreeSpaceTile(), 9, 6);
    segmentBoard[10][6] = new Segment(new FreeSpaceTile(), 10, 6);
    segmentBoard[11][6] = new Segment(new FreeSpaceTile(), 11, 6);
    segmentBoard[12][6] = new Segment(new FreeSpaceTile(), 12, 6);
    segmentBoard[13][6] = new Segment(new FreeSpaceTile(), 13, 6);
    segmentBoard[14][6] = new Segment(new FreeSpaceTile(), 14, 6);
    segmentBoard[15][6] = new Segment(new FreeSpaceTile(), 15, 6);
    segmentBoard[16][6] = new Segment(new FreeSpaceTile(), 16, 6);
    segmentBoard[17][6] = new Segment(new FreeSpaceTile(), 17, 6);
    segmentBoard[18][6] = new Segment(new Wall(), 18, 6);
    segmentBoard[19][6] = new Segment(new FreeSpaceTile(), 19, 6);

    segmentBoard[0][7] = new Segment(new FreeSpaceTile(), 0, 7);
    segmentBoard[1][7] = new Segment(new Wall(), 1, 7);
    segmentBoard[2][7] = new Segment(new FreeSpaceTile(), 2, 7);
    segmentBoard[3][7] = new Segment(new Wall(), 3, 7);
    segmentBoard[4][7] = new Segment(new Wall(), 4, 7);
    segmentBoard[5][7] = new Segment(new FreeSpaceTile(), 5, 7);
    segmentBoard[6][7] = new Segment(new FreeSpaceTile(), 6, 7);
    segmentBoard[7][7] = new Segment(new Wall(), 7, 7);
    segmentBoard[8][7] = new Segment(new FreeSpaceTile(), 8, 7);
    segmentBoard[9][7] = new Segment(new Wall(), 9, 7);
    segmentBoard[10][7] = new Segment(new Wall(), 10, 7);
    segmentBoard[11][7] = new Segment(new Wall(), 11, 7);
    segmentBoard[12][7] = new Segment(new FreeSpaceTile(), 12, 7);
    segmentBoard[13][7] = new Segment(new FreeSpaceTile(), 13, 7);
    segmentBoard[14][7] = new Segment(new FreeSpaceTile(), 14, 7);
    segmentBoard[15][7] = new Segment(new FreeSpaceTile(), 15, 7);
    segmentBoard[16][7] = new Segment(new Wall(), 16, 7);
    segmentBoard[17][7] = new Segment(new Wall(), 17, 7);
    segmentBoard[18][7] = new Segment(new Wall(), 18, 7);
    segmentBoard[19][7] = new Segment(new FreeSpaceTile(), 19, 7);

    segmentBoard[0][8] = new Segment(new FreeSpaceTile(), 0, 8);
    segmentBoard[1][8] = new Segment(new Wall(), 1, 8);
    segmentBoard[2][8] = new Segment(new FreeSpaceTile(), 2, 8);
    segmentBoard[3][8] = new Segment(new FreeSpaceTile(), 3, 8);
    segmentBoard[4][8] = new Segment(new Wall(), 4, 8);
    segmentBoard[5][8] = new Segment(new FreeSpaceTile(), 5, 8);
    segmentBoard[6][8] = new Segment(new Wall(), 6, 8);
    segmentBoard[7][8] = new Segment(new Wall(), 7, 8);
    segmentBoard[8][8] = new Segment(new FreeSpaceTile(), 8, 8);
    segmentBoard[9][8] = new Segment(new Wall(), 9, 8);
    segmentBoard[10][8] = new Segment(new FreeSpaceTile(), 10, 8);
    segmentBoard[11][8] = new Segment(new Wall(), 11, 8);
    segmentBoard[12][8] = new Segment(new FreeSpaceTile(), 12, 8);
    segmentBoard[13][8] = new Segment(new Wall(), 13, 8);
    segmentBoard[14][8] = new Segment(new Wall(), 14, 8);
    segmentBoard[15][8] = new Segment(new Wall(), 15, 8);
    segmentBoard[16][8] = new Segment(new Wall(), 16, 8);
    segmentBoard[17][8] = new Segment(new FreeSpaceTile(), 17, 8);
    segmentBoard[18][8] = new Segment(new FreeSpaceTile(), 18, 8);
    segmentBoard[19][8] = new Segment(new FreeSpaceTile(), 19, 8);

    segmentBoard[0][9] = new Segment(new FreeSpaceTile(), 0, 9);
    segmentBoard[1][9] = new Segment(new Wall(), 1, 9);
    segmentBoard[2][9] = new Segment(new Wall(), 2, 9);
    segmentBoard[3][9] = new Segment(new FreeSpaceTile(), 3, 9);
    segmentBoard[4][9] = new Segment(new Wall(), 4, 9);
    segmentBoard[5][9] = new Segment(new FreeSpaceTile(), 5, 9);
    segmentBoard[6][9] = new Segment(new FreeSpaceTile(), 6, 9);
    segmentBoard[7][9] = new Segment(new FreeSpaceTile(), 7, 9);
    segmentBoard[8][9] = new Segment(new FreeSpaceTile(), 8, 9);
    segmentBoard[9][9] = new Segment(new Wall(), 9, 9);
    segmentBoard[10][9] = new Segment(new FreeSpaceTile(), 10, 9);
    segmentBoard[11][9] = new Segment(new Wall(), 11, 9);
    segmentBoard[12][9] = new Segment(new FreeSpaceTile(), 12, 9);
    segmentBoard[13][9] = new Segment(new FreeSpaceTile(), 13, 9);
    segmentBoard[14][9] = new Segment(new FreeSpaceTile(), 14, 9);
    segmentBoard[15][9] = new Segment(new FreeSpaceTile(), 15, 9);
    segmentBoard[16][9] = new Segment(new FreeSpaceTile(), 16, 9);
    segmentBoard[17][9] = new Segment(new FreeSpaceTile(), 17, 9);
    segmentBoard[18][9] = new Segment(new FreeSpaceTile(), 18, 9);
    segmentBoard[19][9] = new Segment(new FreeSpaceTile(), 19, 9);

    segmentBoard[0][10] = new Segment(new FreeSpaceTile(), 0, 10);
    segmentBoard[1][10] = new Segment(new FreeSpaceTile(), 1, 10);
    segmentBoard[2][10] = new Segment(new Wall(), 2, 10);
    segmentBoard[3][10] = new Segment(new FreeSpaceTile(), 3, 10);
    segmentBoard[4][10] = new Segment(new Wall(), 4, 10);
    segmentBoard[5][10] = new Segment(new Wall(), 5, 10);
    segmentBoard[6][10] = new Segment(new Wall(), 6, 10);
    segmentBoard[7][10] = new Segment(new Wall(), 7, 10);
    segmentBoard[8][10] = new Segment(new FreeSpaceTile(), 8, 10);
    segmentBoard[9][10] = new Segment(new Wall(), 9, 10);
    segmentBoard[10][10] = new Segment(new FreeSpaceTile(), 10, 10);
    segmentBoard[11][10] = new Segment(new Wall(), 11, 10);
    segmentBoard[12][10] = new Segment(new FreeSpaceTile(), 12, 10);
    segmentBoard[13][10] = new Segment(new Wall(), 13, 10);
    segmentBoard[14][10] = new Segment(new FreeSpaceTile(), 14, 10);
    segmentBoard[15][10] = new Segment(new FreeSpaceTile(), 15, 10);
    segmentBoard[16][10] = new Segment(new Wall(), 16, 10);
    segmentBoard[17][10] = new Segment(new Wall(), 17, 10);
    segmentBoard[18][10] = new Segment(new Wall(), 18, 10);
    segmentBoard[19][10] = new Segment(new Wall(), 19, 10);

    segmentBoard[0][11] = new Segment(new Wall(), 0, 11);
    segmentBoard[1][11] = new Segment(new FreeSpaceTile(), 1, 11);
    segmentBoard[2][11] = new Segment(new Wall(), 2, 11);
    segmentBoard[3][11] = new Segment(new FreeSpaceTile(), 3, 11);
    segmentBoard[4][11] = new Segment(new FreeSpaceTile(), 4, 11);
    segmentBoard[5][11] = new Segment(new FreeSpaceTile(), 5, 11);
    segmentBoard[6][11] = new Segment(new FreeSpaceTile(), 6, 11);
    segmentBoard[7][11] = new Segment(new Wall(), 7, 11);
    segmentBoard[8][11] = new Segment(new FreeSpaceTile(), 8, 11);
    segmentBoard[9][11] = new Segment(new Wall(), 9, 11);
    segmentBoard[10][11] = new Segment(new FreeSpaceTile(), 10, 11);
    segmentBoard[11][11] = new Segment(new Wall(), 11, 11);
    segmentBoard[12][11] = new Segment(new FreeSpaceTile(), 12, 11);
    segmentBoard[13][11] = new Segment(new Wall(), 13, 11);
    segmentBoard[14][11] = new Segment(new Wall(), 14, 11);
    segmentBoard[15][11] = new Segment(new FreeSpaceTile(), 15, 11);
    segmentBoard[16][11] = new Segment(new FreeSpaceTile(), 16, 11);
    segmentBoard[17][11] = new Segment(new FreeSpaceTile(), 17, 11);
    segmentBoard[18][11] = new Segment(new FreeSpaceTile(), 18, 11);
    segmentBoard[19][11] = new Segment(new FreeSpaceTile(), 19, 11);

    segmentBoard[0][12] = new Segment(new Wall(), 0, 12);
    segmentBoard[1][12] = new Segment(new FreeSpaceTile(), 1, 12);
    segmentBoard[2][12] = new Segment(new Wall(), 2, 12);
    segmentBoard[3][12] = new Segment(new FreeSpaceTile(), 3, 12);
    segmentBoard[4][12] = new Segment(new Wall(), 4, 12);
    segmentBoard[5][12] = new Segment(new Wall(), 5, 12);
    segmentBoard[6][12] = new Segment(new Wall(), 6, 12);
    segmentBoard[7][12] = new Segment(new Wall(), 7, 12);
    segmentBoard[8][12] = new Segment(new FreeSpaceTile(), 8, 12);
    segmentBoard[9][12] = new Segment(new Wall(), 9, 12);
    segmentBoard[10][12] = new Segment(new FreeSpaceTile(), 10, 12);
    segmentBoard[11][12] = new Segment(new Wall(), 11, 12);
    segmentBoard[12][12] = new Segment(new FreeSpaceTile(), 12, 12);
    segmentBoard[13][12] = new Segment(new FreeSpaceTile(), 13, 12);
    segmentBoard[14][12] = new Segment(new Wall(), 14, 12);
    segmentBoard[15][12] = new Segment(new FreeSpaceTile(), 15, 12);
    segmentBoard[16][12] = new Segment(new Wall(), 16, 12);
    segmentBoard[17][12] = new Segment(new FreeSpaceTile(), 17, 12);
    segmentBoard[18][12] = new Segment(new Wall(), 18, 12);
    segmentBoard[19][12] = new Segment(new FreeSpaceTile(), 19, 12);

    segmentBoard[0][13] = new Segment(new FreeSpaceTile(), 0, 13);
    segmentBoard[1][13] = new Segment(new FreeSpaceTile(), 1, 13);
    segmentBoard[2][13] = new Segment(new Wall(), 2, 13);
    segmentBoard[3][13] = new Segment(new FreeSpaceTile(), 3, 13);
    segmentBoard[4][13] = new Segment(new Wall(), 4, 13);
    segmentBoard[5][13] = new Segment(new FreeSpaceTile(), 5, 13);
    segmentBoard[6][13] = new Segment(new FreeSpaceTile(), 6, 13);
    segmentBoard[7][13] = new Segment(new FreeSpaceTile(), 7, 13);
    segmentBoard[8][13] = new Segment(new FreeSpaceTile(), 8, 13);
    segmentBoard[9][13] = new Segment(new Wall(), 9, 13);
    segmentBoard[10][13] = new Segment(new FreeSpaceTile(), 10, 13);
    segmentBoard[11][13] = new Segment(new Wall(), 11, 13);
    segmentBoard[12][13] = new Segment(new FreeSpaceTile(), 12, 13);
    segmentBoard[13][13] = new Segment(new FreeSpaceTile(), 13, 13);
    segmentBoard[14][13] = new Segment(new Wall(), 14, 13);
    segmentBoard[15][13] = new Segment(new Wall(), 15, 13);
    segmentBoard[16][13] = new Segment(new Wall(), 16, 13);
    segmentBoard[17][13] = new Segment(new FreeSpaceTile(), 17, 13);
    segmentBoard[18][13] = new Segment(new Wall(), 18, 13);
    segmentBoard[19][13] = new Segment(new FreeSpaceTile(), 19, 13);

    segmentBoard[0][14] = new Segment(new FreeSpaceTile(), 0, 14);
    segmentBoard[1][14] = new Segment(new FreeSpaceTile(), 1, 14);
    segmentBoard[2][14] = new Segment(new Wall(), 2, 14);
    segmentBoard[3][14] = new Segment(new FreeSpaceTile(), 3, 14);
    segmentBoard[4][14] = new Segment(new FreeSpaceTile(), 4, 14);
    segmentBoard[5][14] = new Segment(new FreeSpaceTile(), 5, 14);
    segmentBoard[6][14] = new Segment(new FreeSpaceTile(), 6, 14);
    segmentBoard[7][14] = new Segment(new Wall(), 7, 14);
    segmentBoard[8][14] = new Segment(new Wall(), 8, 14);
    segmentBoard[9][14] = new Segment(new Wall(), 9, 14);
    segmentBoard[10][14] = new Segment(new FreeSpaceTile(), 10, 14);
    segmentBoard[11][14] = new Segment(new Wall(), 11, 14);
    segmentBoard[12][14] = new Segment(new FreeSpaceTile(), 12, 14);
    segmentBoard[13][14] = new Segment(new FreeSpaceTile(), 13, 14);
    segmentBoard[14][14] = new Segment(new FreeSpaceTile(), 14, 14);
    segmentBoard[15][14] = new Segment(new FreeSpaceTile(), 15, 14);
    segmentBoard[16][14] = new Segment(new FreeSpaceTile(), 16, 14);
    segmentBoard[17][14] = new Segment(new FreeSpaceTile(), 17, 14);
    segmentBoard[18][14] = new Segment(new Wall(), 18, 14);
    segmentBoard[19][14] = new Segment(new FreeSpaceTile(), 19, 14);

    segmentBoard[0][15] = new Segment(new Wall(), 0, 15);
    segmentBoard[1][15] = new Segment(new Wall(), 1, 15);
    segmentBoard[2][15] = new Segment(new Wall(), 2, 15);
    segmentBoard[3][15] = new Segment(new FreeSpaceTile(), 3, 15);
    segmentBoard[4][15] = new Segment(new Wall(), 4, 15);
    segmentBoard[5][15] = new Segment(new Wall(), 5, 15);
    segmentBoard[6][15] = new Segment(new Wall(), 6, 15);
    segmentBoard[7][15] = new Segment(new Wall(), 7, 15);
    segmentBoard[8][15] = new Segment(new FreeSpaceTile(), 8, 15);
    segmentBoard[9][15] = new Segment(new Wall(), 9, 15);
    segmentBoard[10][15] = new Segment(new FreeSpaceTile(), 10, 15);
    segmentBoard[11][15] = new Segment(new Wall(), 11, 15);
    segmentBoard[12][15] = new Segment(new FreeSpaceTile(), 12, 15);
    segmentBoard[13][15] = new Segment(new FreeSpaceTile(), 13, 15);
    segmentBoard[14][15] = new Segment(new Wall(), 14, 15);
    segmentBoard[15][15] = new Segment(new Wall(), 15, 15);
    segmentBoard[16][15] = new Segment(new Wall(), 16, 15);
    segmentBoard[17][15] = new Segment(new FreeSpaceTile(), 17, 15);
    segmentBoard[18][15] = new Segment(new Wall(), 18, 15);
    segmentBoard[19][15] = new Segment(new Wall(), 19, 15);

    segmentBoard[0][16] = new Segment(new FreeSpaceTile(), 0, 16);
    segmentBoard[1][16] = new Segment(new FreeSpaceTile(), 1, 16);
    segmentBoard[2][16] = new Segment(new FreeSpaceTile(), 2, 16);
    segmentBoard[3][16] = new Segment(new FreeSpaceTile(), 3, 16);
    segmentBoard[4][16] = new Segment(new FreeSpaceTile(), 4, 16);
    segmentBoard[5][16] = new Segment(new FreeSpaceTile(), 5, 16);
    segmentBoard[6][16] = new Segment(new FreeSpaceTile(), 6, 16);
    segmentBoard[7][16] = new Segment(new FreeSpaceTile(), 7, 16);
    segmentBoard[8][16] = new Segment(new FreeSpaceTile(), 8, 16);
    segmentBoard[9][16] = new Segment(new Wall(), 9, 16);
    segmentBoard[10][16] = new Segment(new FreeSpaceTile(), 10, 16);
    segmentBoard[11][16] = new Segment(new Wall(), 11, 16);
    segmentBoard[12][16] = new Segment(new FreeSpaceTile(), 12, 16);
    segmentBoard[13][16] = new Segment(new FreeSpaceTile(), 13, 16);
    segmentBoard[14][16] = new Segment(new Wall(), 14, 16);
    segmentBoard[15][16] = new Segment(new FreeSpaceTile(), 15, 16);
    segmentBoard[16][16] = new Segment(new Wall(), 16, 16);
    segmentBoard[17][16] = new Segment(new FreeSpaceTile(), 17, 16);
    segmentBoard[18][16] = new Segment(new FreeSpaceTile(), 18, 16);
    segmentBoard[19][16] = new Segment(new Wall(), 19, 16);

    segmentBoard[0][17] = new Segment(new FreeSpaceTile(), 0, 17);
    segmentBoard[1][17] = new Segment(new Wall(), 1, 17);
    segmentBoard[2][17] = new Segment(new Wall(), 2, 17);
    segmentBoard[3][17] = new Segment(new Wall(), 3, 17);
    segmentBoard[4][17] = new Segment(new Wall(), 4, 17);
    segmentBoard[5][17] = new Segment(new Wall(), 5, 17);
    segmentBoard[6][17] = new Segment(new Wall(), 6, 17);
    segmentBoard[7][17] = new Segment(new Wall(), 7, 17);
    segmentBoard[8][17] = new Segment(new FreeSpaceTile(), 8, 17);
    segmentBoard[9][17] = new Segment(new Wall(), 9, 17);
    segmentBoard[10][17] = new Segment(new FreeSpaceTile(), 10, 17);
    segmentBoard[11][17] = new Segment(new FreeSpaceTile(), 11, 17);
    segmentBoard[12][17] = new Segment(new FreeSpaceTile(), 12, 17);
    segmentBoard[13][17] = new Segment(new FreeSpaceTile(), 13, 17);
    segmentBoard[14][17] = new Segment(new Wall(), 14, 17);
    segmentBoard[15][17] = new Segment(new FreeSpaceTile(), 15, 17);
    segmentBoard[16][17] = new Segment(new Wall(), 16, 17);
    segmentBoard[17][17] = new Segment(new FreeSpaceTile(), 17, 17);
    segmentBoard[18][17] = new Segment(new Wall(), 18, 17);
    segmentBoard[19][17] = new Segment(new Wall(), 19, 17);

    segmentBoard[0][18] = new Segment(new FreeSpaceTile(), 0, 18);
    segmentBoard[1][18] = new Segment(new FreeSpaceTile(), 1, 18);
    segmentBoard[2][18] = new Segment(new FreeSpaceTile(), 2, 18);
    segmentBoard[3][18] = new Segment(new FreeSpaceTile(), 3, 18);
    segmentBoard[4][18] = new Segment(new FreeSpaceTile(), 4, 18);
    segmentBoard[5][18] = new Segment(new FreeSpaceTile(), 5, 18);
    segmentBoard[6][18] = new Segment(new FreeSpaceTile(), 6, 18);
    segmentBoard[7][18] = new Segment(new Wall(), 7, 18);
    segmentBoard[8][18] = new Segment(new Wall(), 8, 18);
    segmentBoard[9][18] = new Segment(new Wall(), 9, 18);
    segmentBoard[10][18] = new Segment(new Wall(), 10, 18);
    segmentBoard[11][18] = new Segment(new Wall(), 11, 18);
    segmentBoard[12][18] = new Segment(new Wall(), 12, 18);
    segmentBoard[13][18] = new Segment(new FreeSpaceTile(), 13, 18);
    segmentBoard[14][18] = new Segment(new Wall(), 14, 18);
    segmentBoard[15][18] = new Segment(new FreeSpaceTile(), 15, 18);
    segmentBoard[16][18] = new Segment(new Wall(), 16, 18);
    segmentBoard[17][18] = new Segment(new Wall(), 17, 18);
    segmentBoard[18][18] = new Segment(new Wall(), 18, 18);
    segmentBoard[19][18] = new Segment(new FreeSpaceTile(), 19, 18);

    segmentBoard[0][19] = new Segment(new Wall(), 0, 19);
    segmentBoard[1][19] = new Segment(new Wall(), 1, 19);
    segmentBoard[2][19] = new Segment(new FreeSpaceTile(), 2, 19);
    segmentBoard[3][19] = new Segment(new FreeSpaceTile(), 3, 19);
    segmentBoard[4][19] = new Segment(new Wall(), 4, 19);
    segmentBoard[5][19] = new Segment(new FreeSpaceTile(), 5, 19);
    segmentBoard[6][19] = new Segment(new FreeSpaceTile(), 6, 19);
    segmentBoard[7][19] = new Segment(new FreeSpaceTile(), 7, 19);
    segmentBoard[8][19] = new Segment(new FreeSpaceTile(), 8, 19);
    segmentBoard[9][19] = new Segment(new FreeSpaceTile(), 9, 19);
    segmentBoard[10][19] = new Segment(new FreeSpaceTile(), 10, 19);
    segmentBoard[11][19] = new Segment(new FreeSpaceTile(), 11, 19);
    segmentBoard[12][19] = new Segment(new Wall(), 12, 19);
    segmentBoard[13][19] = new Segment(new Wall(), 13, 19);
    segmentBoard[14][19] = new Segment(new Wall(), 14, 19);
    segmentBoard[15][19] = new Segment(new FreeSpaceTile(), 15, 19);
    segmentBoard[16][19] = new Segment(new FreeSpaceTile(), 16, 19);
    segmentBoard[17][19] = new Segment(new FreeSpaceTile(), 17, 19);
    segmentBoard[18][19] = new Segment(new FreeSpaceTile(), 18, 19);
    segmentBoard[19][19] = new Segment(new FreeSpaceTile(), 19, 19);


    return segmentBoard;

	}*/

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
