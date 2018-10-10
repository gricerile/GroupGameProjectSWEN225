package main;


import java.awt.Image;
import java.io.File;

import GUI.GUI;
import parser.Parser;
import renderer.Renderer;
import renderer.Texture;
import GUI.GUI.moveDirection;

public class Main {
	private Segment[][] segmentsBoard = new Segment[5][5]; // the size can change but for testing the size is 2 segments
	private Player player;
	private GUI g;
	private Parser p;
	private Texture texture = new Texture();
	private Renderer renderer;
	private Image image;

	public int x;
	public int y;

	public static void main(String[] args) {
		Main m = new Main();
	}

	public Main() {
		this.p = new Parser();
		this.g = new GUI(this);
		p.loadMap(new File("ParsingTester.xml"));
		// this.image = texture.onLoad("/tx/grass.png");
		// this.segmentsBpoard=p.getSegments(); will be created
		// this.player=p.getPlayer(); will be created, lets do this
		this.renderer = new Renderer(this);
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
		if(this.player==null||this.player.getSegment()==null) {
			return false;
		}
		Segment next = getNextSegment(direction);
		if (next != null) {
			if (next.getObject().getType().equals("FreeTile")||next.getObject().getType().equals("Door Unlocked")) {
				return true;
			}
		}
		return false;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Segment getNextSegment(moveDirection direction) {//this only works for 3 by 3 double array
		int xs=0;
		int ys=0;
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				if(this.segmentsBoard[x][y].equals(this.player.getSegment())){
					xs=x;
					ys=y;
					break;
				}
			}
		}
		if(direction==moveDirection.downLeft) {
			if((ys-1)>=0) {
				this.x += 20;
				this.y += 20;
				return this.segmentsBoard[xs][ys-1];
			}
		} else if (direction==moveDirection.upRight) {
			if((xs+1)<3) {
				return this.segmentsBoard[xs+1][ys];
			}
		} else if (direction==moveDirection.downLeft) {
			if((ys+1)<3) {
				return this.segmentsBoard[xs][ys+1];
			}
		} else if (direction==moveDirection.downLeft) {
			if((xs-1)>=0) {
				return this.segmentsBoard[xs-1][ys];
			}
		}
		return null;
	}

	public void clickedScreen(int x, int y) {
		reDraw();
	}

	public void saveGame() {
		//this.p.saveGame(this.segmentsBoard,this.player);
	}

	public void loadGame() {
		//this.p.loadMap(FileChooser());//or victor can choose the file himself in the parser
	}

	public void quitGame() {
		System.exit(0);
	}

	public void reDraw() {
		this.g.getFrame().getGraphicsWindow().redraw();
	}

}
