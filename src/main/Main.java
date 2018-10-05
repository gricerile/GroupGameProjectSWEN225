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

  private Renderer renderer;

  public static void main(String[] args) {
    Main m = new Main();
  }

  public Main() {
    Parser p = new Parser();
    this.g = new GUI(this);
    p.loadMap(new File("ParsingTester.xml"));
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
    Segment next = getNextSegment(direction);
    if (next != null) {
      if (next.getObject().getType().equals("FreeTile") || next.getObject().getType().equals("Door Unlocked")) {
        return true;
      }
    }
    return false;
  }

  public Segment getNextSegment(moveDirection direction) {// this only works for 3 by 3 double array
    int xs = 0;
    int ys = 0;
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        if (this.segmentsBoard[x][y].equals(this.player.getSegment())) {
          xs = x;
          ys = y;
          break;
        }
      }
    }
    if (direction == moveDirection.up) {
      if ((ys - 1) >= 0) {
        return this.segmentsBoard[xs][ys - 1];
      }
    } else if (direction == moveDirection.right) {
      if ((xs + 1) < 3) {
        return this.segmentsBoard[xs + 1][ys];
      }
    } else if (direction == moveDirection.down) {
      if ((ys + 1) < 3) {
        return this.segmentsBoard[xs][ys + 1];
      }
    } else if (direction == moveDirection.left) {
      if ((xs - 1) >= 0) {
        return this.segmentsBoard[xs - 1][ys];
      }
    }
    return null;
  }

  public void clickedScreen(int x, int y) {
    reDraw();
  }

  public void saveGame() {

  }

  public void loadGame() {

  }

  public void quitGame() {
    System.exit(0);
  }

  public void reDraw() {
    this.g.getFrame().getGraphicsWindow().redraw();
  }

}
