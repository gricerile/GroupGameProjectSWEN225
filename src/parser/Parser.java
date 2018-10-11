package parser;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.*;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;

import main.*;

public class Parser {
  public static final String testMapFileName = "Testing Map.xml";
  public static final String dungeonMapName = "Dungeon Map.xml";

  public static final String TRUECHECKER = "true";
  public static final String FALSECHECKER = "false";

  public static final String NORTHWALLCHECKER = "hasNorthWall";
  public static final String SOUTHWALLCHECKER = "hasSouthWall";
  public static final String EASTWALLCHECKER = "hasEastWall";
  public static final String WESTWALLCHECKER = "hasWestWall";
  public static final String DOORCHECKER = "door";
  public static final String CONTAINSOBJECTSCHECKER = "hasObjects";

  private ArrayList<TestingSegment> listOfSegmentRooms = new ArrayList<>();
  private Segment[][] segments = new Segment[3][3];

  /*
  public void loadMap(File mapFile) {
    listOfSegmentRooms = new ArrayList<>();
    try {
      BufferedReader buffer = new BufferedReader(new FileReader(mapFile));
      String header = buffer.readLine();
      String line = buffer.readLine();

      while (line != null) {
        // If we are loading the line signaling the start or end of a room description,
        // do not read anything and move on.
        if (!line.contains("/Room")) {
          // If we are reading a room.
          if (line.equals("<Room>")) {
            TestingSegment newSegment = new TestingSegment();
            line = buffer.readLine();

            while (line != null) {
              String sections[] = line.split(" ");
              // Read if walls in four cardinal directions are present or not.
              if (sections[0].contains(NORTHWALLCHECKER)) { // North Wall
                if (sections[1].equals(TRUECHECKER)) {
                  newSegment.setHasNorthWall(true);
                } else if (sections[1].equals(FALSECHECKER)) {
                  newSegment.setHasNorthWall(false);
                } else {
                  System.out.println("Invalid specification of wall being present.");
                }
              } else if (sections[0].contains(SOUTHWALLCHECKER)) { // South Wall
                if (sections[1].equals(TRUECHECKER)) {
                  newSegment.setHasSouthWall(true);
                } else if (sections[1].equals(FALSECHECKER)) {
                  newSegment.setHasSouthWall(false);
                } else {
                  System.out.println("Invalid specification of wall being present.");
                }
              } else if (sections[0].contains(EASTWALLCHECKER)) { // East Wall
                if (sections[1].equals(TRUECHECKER)) {
                  newSegment.setHasEastWall(true);
                } else if (sections[1].equals(FALSECHECKER)) {
                  newSegment.setHasEastWall(false);
                } else {
                  System.out.println("Invalid specification of wall being present.");
                }
              } else if (sections[0].contains(WESTWALLCHECKER)) { // West Wall
                if (sections[1].equals(TRUECHECKER)) {
                  newSegment.setHasWestWall(true);
                } else if (sections[1].equals(FALSECHECKER)) {
                  newSegment.setHasWestWall(false);
                } else {
                  System.out.println("Invalid specification of wall being present.");
                }
              }

              // Read if a door is present, and if so, where is it located.
              else if (sections[0].contains(DOORCHECKER)) {
                if (sections[1].equals(TRUECHECKER)) {
                  newSegment.setHasDoor(true);
                  newSegment.setDoorLocation(sections[2]);
                  if (sections[3].equals("locked")) {
                    newSegment.setDoorLocked(true);
                  } else if (sections[3].equals("unlocked")) {
                    newSegment.setDoorLocked(false);
                  } else {
                    System.out.println("Invalid specification of door being locked.");
                  }
                } else if (sections[1].equals(FALSECHECKER)) {
                  newSegment.setHasDoor(false);
                } else {
                  System.out.println("Invalid specification of door being present.");
                }
              }

              // Read if there are objects present or not.
              else if (sections[0].contains(CONTAINSOBJECTSCHECKER)) {
                if (sections[1].equals(TRUECHECKER)) {
                  newSegment.setHasObjects(true);
                  line = buffer.readLine();
                  sections = line.split(" ");
                  if (sections[0].equals("<objects>")) {
                    int i = 1;
                    // Until the end of the line of objects, read the objects into the list of
                    // objects
                    while (!sections[i].equals("</objects>")) {
                      newSegment.addObject(sections[i++]);
                    }
                  }
                } else if (sections[1].equals(FALSECHECKER)) {
                  newSegment.setHasObjects(false);
                } else {
                  System.out.println("Invalid specification of objects being present.");
                }
              }

              line = buffer.readLine();
            }
            listOfSegmentRooms.add(newSegment);
          }
        }
        line = buffer.readLine();
      }
    } catch (FileNotFoundException e) {
      System.out.println("Error file not found.");
    } catch (IOException e) {
      System.out.println("IO Exception.");
    }
    for (TestingSegment s : listOfSegmentRooms) {

      System.out.println("North Wall: " + s.hasNorthWall);
      System.out.println("South Wall: " + s.hasSouthWall);
      System.out.println("East Wall: " + s.hasEastWall);
      System.out.println("West Wall: " + s.hasWestWall);
      System.out.println("Door: " + s.hasDoor + ", Location: " + s.doorLocation + ", Locked: " + s.isDoorLocked);
      System.out.println();
      System.out.println("Objects:");
      for (String obj : s.getObjects()) {
        System.out.println(obj);
      }
    }
  }*/

  public Segment[][] loadMap(File mapFile) {
    try {
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

      InputStream in = new FileInputStream(mapFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
        GameObject gameObjectType = null;
        int x = 0;
        int y = 0;
        boolean hasPlayer = false;
        int id = 0;
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();


        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          //If we have found a room, we start the room parsing.
          if (startElement.getName().getLocalPart().equals("NewSegment")) {
              gameObjectType = null;
              x = 0;
              y = 0;
              hasPlayer = false;
              id = 0;
          }
            if (event.asStartElement().getName().getLocalPart().equals("GameObject")) {
              event = eventReader.nextEvent();

              if (event.asCharacters().getData().equals("FreeSpaceTile")) {
                  gameObjectType = new FreeSpaceTile();
              }
              else if (event.asCharacters().getData().equals("Wall")) {
                  gameObjectType = new Wall();
              }
              else if (event.asCharacters().getData().equals("Door")) {
                  gameObjectType = new Door(101, false);
              }
              else if (event.asCharacters().getData().equals("Chest")) {
                  gameObjectType = new Chest(new Key(101, "Key opens door 101", "Key ID 101"));
              }
              continue;
            }

            if (event.asStartElement().getName().getLocalPart().equals("CoordinateX")) {
              event = eventReader.nextEvent();
              x = Integer.parseInt(event.asCharacters().getData());
              continue;
            }

            if (event.asStartElement().getName().getLocalPart().equals("CoordinateY")) {
              event = eventReader.nextEvent();
              y = Integer.parseInt(event.asCharacters().getData());
              continue;
            }

            if (event.asStartElement().getName().getLocalPart().equals("hasPlayer")) {
              event = eventReader.nextEvent();
              if (event.asCharacters().getData().equals(TRUECHECKER)) {
                hasPlayer = true;
              }
              else {
                hasPlayer = false;
              }
              continue;
            }
          }
        if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart().equals("NewSegment")) {
            Segment segment = new Segment(gameObjectType,x,y);
            segment.setHasPlayer(hasPlayer);
            segments[x][y] = segment;
          }
        }
        }
    }
    catch (IOException e) {
      System.out.println(e);
    }
    catch (XMLStreamException e) {
      System.out.println(e);
    }
    return segments;
  }

  public static void main(String[] args) {
    Parser p = new Parser();
    //p.loadMap(new File(testMapFileName));
    p.loadMap(new File(testMapFileName));
  }
}
