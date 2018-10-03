package GUI;
import java.io.File;

import main.Player;
import main.Segment;
import parser.Parser;

public class Main {
	private Segment[][] segmentsBoard = new Segment[2][1]; // the size can change but for testing the size is 2 segments
	private Player player;
	private GUI g;

	public static void main(String[] args) {
		Main m = new Main();
	}

	public Main() {
		Parser p = new Parser();
		p.loadMap(new File("ParsingTester.xml"));
		//this.segmentsBpoard=p.getSegments(); will be created
		//this.player=p.getPlayer(); will be created
		this.g = new GUI(this);
	}

	public Segment[][] getSegments(){
		return this.segmentsBoard;
	}

	public Player getPlayer(){
		return this.player;
	}

	public GUI getGUI() {
		return this.g;
	}

}
