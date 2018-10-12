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

  private ArrayList<TestingSegment> listOfSegmentRooms = new ArrayList<>();
  private Segment[][] segments = new Segment[3][3];

  /**
   * Method to used to parse the segments into a
   * two dimensional array of Segments.
   *
   * @param mapFile
   *          The source file storing the segment information.
   * @return
   *          The 2d array of segments storing map information.
   */
  public Segment[][] loadMap(File mapFile) {
    try {
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

      InputStream in = new FileInputStream(mapFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      //Create variables to store the information of the segments before creating them.
      GameObject gameObjectType = null;
      int x = 0;
      int y = 0;
      boolean hasPlayer = false;
      Key key = null;
      Door door = null;
      while (eventReader.hasNext()) {
      XMLEvent event = eventReader.nextEvent();
      if (event.isStartElement()) {
        StartElement startElement = event.asStartElement();
        //If we have found a segment, we start the segment parsing.
        if (startElement.getName().getLocalPart().equals("NewSegment")) {
          gameObjectType = null;
          x = 0;
          y = 0;
          hasPlayer = false;
          key = null;
          door = null;
        }
        if (event.asStartElement().getName().getLocalPart().equals("GameObject")) {
          event = eventReader.nextEvent();
          //Parse the appropriate GameObject based on the present tile
          if (event.asCharacters().getData().equals("FreeSpaceTile")) {
            gameObjectType = new FreeSpaceTile();
          }
          else if (event.asCharacters().getData().equals("Wall")) {
            gameObjectType = new Wall();
          }
          //Doors also require additional information to be parsed.
          else if (event.asCharacters().getData().equals("Door")) {
            door = parseDoor(eventReader);
            gameObjectType = door;
          }
          //Chests also require a key object to be created and stored within the chest.
          else if (event.asCharacters().getData().equals("Chest")) {
            key = parseKey(eventReader);
            gameObjectType = new Chest(key);
          }
          continue;
          }
          //After parsing the Tile type, parse the coordinates.
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
          //Parse the information declaring if the tile contains the player or not.
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
        //Finally, after collecting all the appropriate information, create and store the segment.
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

  /**
   * Helper method to parse key object information.
   *
   * @param eventReader
   *          The XMLEventReader used for parsing.
   * @return
   */
  public Key parseKey(XMLEventReader eventReader) {
    int id = 0;
    String description = "";
    String name = "";
    try {
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();

          if (startElement.getName().getLocalPart().equals("ID")) {
            event = eventReader.nextEvent();
            id = Integer.parseInt(event.asCharacters().getData());
            continue;
          }

          if (startElement.getName().getLocalPart().equals("Description")) {
            event = eventReader.nextEvent();
            description = event.asCharacters().getData();
            continue;
          }

          if (startElement.getName().getLocalPart().equals("Name")) {
            event = eventReader.nextEvent();
            name = event.asCharacters().getData();
            continue;
          }
        }
        if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart().equals("Key")) {
            Key key = new Key(id,description,name);
            return key;
          }
        }
      }
    }
    catch (XMLStreamException e) {

    }
    return null;
  }

  /**
   * Helper method to parse Door object information.
   *
   * @param eventReader
   *          The XMLEventReader used for parsing.
   * @return
   */
  public Door parseDoor(XMLEventReader eventReader) {
    int id = 0;
    boolean unlocked  = false;
    try {
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();

          if (startElement.getName().getLocalPart().equals("ID")) {
            event = eventReader.nextEvent();
            id = Integer.parseInt(event.asCharacters().getData());
            continue;
          }

          if (startElement.getName().getLocalPart().equals("Unlocked")) {
            event = eventReader.nextEvent();
            if (event.asCharacters().getData().equals(TRUECHECKER)) {
              unlocked = true;
            }
            else {
              unlocked = false;
            }
            continue;
          }
        }
        if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart().equals("newDoor")) {
            return new Door(id,unlocked);
          }
        }
      }
    }
    catch (XMLStreamException e) {

    }
    return null;
  }

  public static void main(String[] args) {
    Parser p = new Parser();
    //p.loadMap(new File(testMapFileName));
    p.loadMap(new File(testMapFileName));
  }
}
