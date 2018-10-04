package main;

import java.io.File;

import GUI.GUI;
import parser.Parser;
import renderer.Renderer;
import GUI.GUI.moveDirection;

public class Main {
	private main.Segment[][] segmentsBoard = new main.Segment[3][3]; // the size can change but for testing the size is 2 segments
	private main.Player player;
	private GUI g;

	private Renderer renderer;

	public static void main(String[] args) {
		Main m = new Main();
	}

	public Main() {
		Parser p = new Parser();
		p.loadMap(new File("ParsingTester.xml"));
		// this.segmentsBpoard=p.getSegments(); will be created
		// this.player=p.getPlayer(); will be created, lets do this
		this.g = new GUI(this);
		this.renderer = new Renderer(this);
	}

	public main.Segment[][] getSegments() {
		return this.segmentsBoard;
	}

	public main.Player getPlayer() {
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
			if (!player.getSegment().getDoor(direction).getSeg1().equals(player.getSegment())) {
				player.move(player.getSegment().getDoor(direction).getSeg1());
			} else {
				player.move(player.getSegment().getDoor(direction).getSeg2());
			}
		}
		reDraw();
	}

	public boolean canMove(moveDirection direction) {
		if (player.getSegment().hasDoor(direction)) {
			if (player.getSegment().getDoor(direction) != null
					&& player.getSegment().getDoor(direction).getOpen() == true) {
				return true;
			}
		}
		return false;
	}

	public void clickedScreen(int x, int y) {
		reDraw();
	}

	public void saveGame() {

	}

	public void loadGame() {

	}

	public void quitGame() {

	}

	public void reDraw() {
		this.g.getFrame().getGraphicsWindow().redraw();
	}

}
