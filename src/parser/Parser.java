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

public class Parser {
  public static final String testMapFileName = "ParsingTester.xml";

  public static final String TRUECHECKER = "true";
  public static final String FALSECHECKER = "false";

  public static final String NORTHWALLCHECKER = "hasNorthWall";
  public static final String SOUTHWALLCHECKER = "hasSouthWall";
  public static final String EASTWALLCHECKER = "hasEastWall";
  public static final String WESTWALLCHECKER = "hasWestWall";
  public static final String DOORCHECKER = "door";
  public static final String CONTAINSOBJECTSCHECKER = "hasObjects";

  private ArrayList<TestingSegment> listOfSegmentRooms = new ArrayList<>();

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

  public void loadMap(File mapFile) {
    try {
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

      InputStream in = new FileInputStream(mapFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      TestingSegment room = null;
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();


        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          //If we have found a room, we start the room parsing.
          if (startElement.getName().getLocalPart().equals("Room")) {
            room = new TestingSegment();

          }

            if (event.asStartElement().getName().getLocalPart().equals(NORTHWALLCHECKER)) {
              event = eventReader.nextEvent();

              if (event.asCharacters().getData().equals(TRUECHECKER)) {
                room.setHasNorthWall(true);
                //System.out.println(":)");
              }
              else {
                room.setHasNorthWall(false);
              }
              continue;
            }

            if (event.asStartElement().getName().getLocalPart().equals(SOUTHWALLCHECKER)) {
              event = eventReader.nextEvent();

              if (event.asCharacters().getData().equals(TRUECHECKER)) {
                room.setHasSouthWall(true);
              }
              else {
                room.setHasSouthWall(false);
              }
              continue;
            }

            if (event.asStartElement().getName().getLocalPart().equals(EASTWALLCHECKER)) {
              event = eventReader.nextEvent();
              if (event.asCharacters().getData().equals(TRUECHECKER)) {
                room.setHasEastWall(true);
              }
              else {
                room.setHasEastWall(false);
              }
              continue;
            }

            if (event.asStartElement().getName().getLocalPart().equals(WESTWALLCHECKER)) {
              event = eventReader.nextEvent();
              if (event.asCharacters().getData().equals(TRUECHECKER)) {
                room.setHasWestWall(true);
              }
              else {
                room.setHasWestWall(false);
              }
              continue;
            }

            if (event.asStartElement().getName().getLocalPart().equals(CONTAINSOBJECTSCHECKER)) {
              event = eventReader.nextEvent();
              if (event.asCharacters().getData().equals(TRUECHECKER)) {
                room.setHasObjects(true);
              }
              else {
                room.setHasObjects(false);
              }
              continue;
            }
          }
        if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart().equals("Room")) {
            listOfSegmentRooms.add(room);
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
    for (TestingSegment s : listOfSegmentRooms) {

      System.out.println("North Wall: " + s.hasNorthWall);
      System.out.println("South Wall: " + s.hasSouthWall);
      System.out.println("East Wall: " + s.hasEastWall);
      System.out.println("West Wall: " + s.hasWestWall);
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Parser p = new Parser();
    //p.loadMap(new File(testMapFileName));
    p.loadMap(new File(testMapFileName));
  }
}
