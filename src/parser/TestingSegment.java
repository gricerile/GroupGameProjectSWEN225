package parser;

import java.util.ArrayList;

public class TestingSegment {
  boolean hasNorthWall = false;
  boolean hasSouthWall = false;
  boolean hasEastWall = false;
  boolean hasWestWall = false;
  boolean hasDoor = false;
  String doorLocation = "";
  boolean isDoorLocked = false;
  boolean hasObjects = false;
  ArrayList<String> objects = new ArrayList<>();

  public TestingSegment() {

  }

  public void setHasNorthWall(boolean hasNorthWall) {
    this.hasNorthWall = hasNorthWall;
  }

  public void setHasSouthWall(boolean hasSouthWall) {
    this.hasSouthWall = hasSouthWall;
  }

  public void setHasEastWall(boolean hasEastWall) {
    this.hasEastWall = hasEastWall;
  }

  public void setHasWestWall(boolean hasWestWall) {
    this.hasWestWall = hasWestWall;
  }

  public void setHasDoor(boolean hasDoor) {
    this.hasDoor = hasDoor;
  }

  public void setDoorLocation(String doorLocation) {
    if (!doorLocation.equals("north") && !doorLocation.equals("south") && !doorLocation.equals("east")
        && !doorLocation.equals("west")) {
      System.out.println("Error, invalid direction specified.");
      return;
    }
    this.doorLocation = doorLocation;
  }

  public void setDoorLocked(boolean doorLocked) {
    isDoorLocked = doorLocked;
  }

  public void setHasObjects(boolean hasObjects) {
    this.hasObjects = hasObjects;
    objects = new ArrayList<>();
  }

  public void addObject(String object) {
    if (!hasObjects) {
      return;
    }
    objects.add(object);
  }

  public ArrayList<String> getObjects() {
    return objects;
  }
}
