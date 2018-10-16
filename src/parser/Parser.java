package parser;


import java.io.*;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

import main.*;

public class Parser {
  public static final String smallMapName = "smallmap.xml";
  public static final String mediumMapName = "mediummap.xml";
  public static final String largeMapName = "largemap.xml";

  public static final String dungeonSaveName = "Dungeon Map Save.xml";

  public static final String playerLocationName = "PlayerStartData.xml";
  public static final String playerSaveLocationName = "PlayerSavingData.xml";
  public static final String inventoryStartDataName = "InventoryStartData.xml";
  public static final String inventorySaveDataName = "InventorySaveData.xml";

  public static final String TRUECHECKER = "true";

  private Main m = null;

  //Arbitrary size that serves as a maximum size.
  private Segment[][] segments = new Segment[30][30];
  private Player player = null;

  /**
   * Simple constructor that stores the main class for use.
   *
   * @param m
   */
  public Parser(Main m) {
    this.m = m;
  }

  /**
   * Method used to parse the player data
   * into the main class
   *
   * @param playerFile
   *          The source file storing the player information.
   * @return
   *          The player object.
   */
  public Player loadPlayer(File playerFile, File inventoryFile) {
    player = null;
    try {
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

      InputStream in = new FileInputStream(playerFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      int x = 0;
      int y = 0;

      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        if (event.isStartElement()) {
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
        }
      }
      player = new Player(segments[x][y]);
      parseInventory(player, inventoryFile);
    }
    catch (IOException e) {
      System.out.println(e);
    }
    catch (XMLStreamException e) {
      System.out.println(e);
    }
    return player;
  }

  /**
   * Helper method to parse player inventory information.
   *
   * @param player
   *          The player which is having its inventory parsed.
   *
   * @param inventoryFile
   *          The inventory file that we are reading from.
   */
  private void parseInventory(Player player, File inventoryFile) {
    m.getGUI().getFrame().getInventoryPanel().clearInventory();
    try {
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

      InputStream in = new FileInputStream(inventoryFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

      Key key = null;
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        if (event.isStartElement()) {
          if (event.asStartElement().getName().getLocalPart().equals("Inventory")) {
            key = null;
          }
          key = parseKey(eventReader);
          if (key != null) {
            player.giveKey(key,m);
          }
        }
        if (event.isEndElement()) {
          if (event.asEndElement().getName().getLocalPart().equals("Inventory")) {
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
  }

  /**
   * Helper method to empty the segments in the parser.
   */
  private void resetSegments() {
    for (int i = 0; i < segments.length; i++) {
      for (int j = 0; j < segments[i].length; j++) {
        segments[i][j] = null;
      }
    }
  }

  /**
   * Method used to parse the segments into a
   * two dimensional array of Segments.
   *
   * @param mapFile
   *          The source file storing the segment information.
   * @return
   *          The 2d array of segments storing map information.
   */
  public Segment[][] loadMap(File mapFile) {
    resetSegments();
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
          if (event.asCharacters().getData().equals("FreeTile")) {
            gameObjectType = new FreeSpaceTile();
          }
          else if (event.asCharacters().getData().equals("Wall")) {
            gameObjectType = new Wall();
          }
          else if (event.asCharacters().getData().equals("WinTile")) {
            gameObjectType = new WinTile();
          }
          //Doors also require additional information to be parsed.
          else if (event.asCharacters().getData().equals("Door Locked")
                  || event.asCharacters().getData().equals("Door Unlocked")) {
            door = parseDoor(eventReader);
            gameObjectType = door;
          }
          //Chests also require a key object to be created and stored within the chest.
          else if (event.asCharacters().getData().equals("Chest")) {
            key = parseKey(eventReader);
            gameObjectType = new Chest(key);
          }
          else if (event.asCharacters().getData().equals("Open Chest")) {
            key = parseKey(eventReader);
            Chest chest = new Chest(key);
            chest.openAndClose();
            gameObjectType = chest;
          }
          else if (event.asCharacters().getData().equals("Empty Chest")) {
            Chest chest = new Chest(null);
            chest.openAndClose();
            gameObjectType = chest;
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
  private Key parseKey(XMLEventReader eventReader) {
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
  private Door parseDoor(XMLEventReader eventReader) {
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
    catch (XMLStreamException e) {}
    return null;
  }

  /**
   * Helper method to insert tab characters into the
   * XML documents for formatting purposes.
   * @param eventWriter
   *          The XMLEventWriter we are currently writing with.
   * @param num
   *          The number of tabs we are inserting.
   * @throws XMLStreamException
   */
  private void addTabs(XMLEventWriter eventWriter, int num) throws XMLStreamException{
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    for (int i = 0; i < num; i++) {
      eventWriter.add(eventFactory.createDTD("\t"));
    }
  }

  /**
   * Method used to save player information to a file:
   * PlayerSavingData.xml.
   * @param player
   *          The player we are taking data from.
   * @throws FileNotFoundException
   * @throws XMLStreamException
   */
  public void savePlayer(Player player) throws FileNotFoundException, XMLStreamException{
    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
    XMLEventWriter eventWriter =
            outputFactory.createXMLEventWriter(new FileOutputStream(playerSaveLocationName));
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createDTD("\n");

    StartDocument startDocument = eventFactory.createStartDocument();
    eventWriter.add(startDocument);
    eventWriter.add(end);

    StartElement segmentsStartElement = eventFactory.createStartElement("","","Player");
    eventWriter.add(segmentsStartElement);
    eventWriter.add(end);

    createNode(eventWriter, "CoordinateX", "" + player.getSegment().getX());
    createNode(eventWriter, "CoordinateY", "" + player.getSegment().getY());

    eventWriter.add(eventFactory.createEndElement("","","Player"));
    saveInventory(player);
  }

  /**
   * Helper method to save the player's inventory information
   * into a seperate InventorySaveData.xml file.
   * @param player
   *          The player we are taking data from.
   * @throws FileNotFoundException
   * @throws XMLStreamException
   */
  private void saveInventory(Player player) throws FileNotFoundException, XMLStreamException{
    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
    XMLEventWriter eventWriter =
            outputFactory.createXMLEventWriter(new FileOutputStream(inventorySaveDataName));
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createDTD("\n");

    StartDocument startDocument = eventFactory.createStartDocument();
    eventWriter.add(startDocument);
    eventWriter.add(end);

    StartElement segmentsStartElement = eventFactory.createStartElement("","","Inventory");
    eventWriter.add(segmentsStartElement);
    eventWriter.add(end);

    for (GameItem k : player.getInventory()) {
      if (k instanceof Key) {
        Key key = (Key) k;
        addTabs(eventWriter, 1);
        eventWriter.add(eventFactory.createStartElement("", "", "Key"));
        eventWriter.add(end);
        createNode(eventWriter, "ID", "" + key.getID());
        createNode(eventWriter, "Description",
                "" + key.getDescription());
        createNode(eventWriter, "Name",
                "" + key.getName());
        addTabs(eventWriter, 1);
        eventWriter.add(eventFactory.createEndElement("", "", "Key"));
        eventWriter.add(end);
        eventWriter.add(end);
      }
    }

    eventWriter.add(eventFactory.createEndElement("","","Inventory"));
  }

  /**
   * Method used to parse the segments into a
   * two dimensional array of Segments.
   *
   * @param segments
   *          The source array storing the segment information.
   *
   */
  public void saveMap(Segment[][] segments) throws FileNotFoundException, XMLStreamException{
    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
    XMLEventWriter eventWriter =
            outputFactory.createXMLEventWriter(new FileOutputStream(dungeonSaveName));
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createDTD("\n");
    XMLEvent tab = eventFactory.createDTD("\t");

    StartDocument startDocument = eventFactory.createStartDocument();
    eventWriter.add(startDocument);
    eventWriter.add(end);

    //Create the opening Segments tag
    StartElement segmentsStartElement = eventFactory.createStartElement("","","Segments");
    eventWriter.add(segmentsStartElement);
    eventWriter.add(end);

    StartElement newSegmentStartElement = eventFactory.createStartElement("","","NewSegment");

    //Start writing segments.
    for (int i = 0; i < segments.length; i++) {
      for (int j = 0; j < segments[i].length; j++) {
        if (segments[i][j] != null) {
          eventWriter.add(tab);
          eventWriter.add(newSegmentStartElement);
          eventWriter.add(end);
          if (segments[i][j].getObject().getType().equals("Door Locked")
                  || segments[i][j].getObject().getType().equals("Door Unlocked")) {
            createNode(eventWriter, "GameObject", segments[i][j].getObject().getType());

            //Write the extra information required of a door.
            addTabs(eventWriter, 2);
            eventWriter.add(eventFactory.createStartElement("", "", "newDoor"));
            eventWriter.add(end);
            addTabs(eventWriter, 1);
            createNode(eventWriter, "ID", "" + ((Door) segments[i][j].getObject()).ID());
            addTabs(eventWriter, 1);
            createNode(eventWriter, "Unlocked", "" + ((Door) segments[i][j].getObject()).getUnlocked());
            addTabs(eventWriter, 2);
            eventWriter.add(eventFactory.createEndElement("", "", "newDoor"));
            eventWriter.add(end);

            createNode(eventWriter, "CoordinateX", "" + segments[i][j].getX());
            createNode(eventWriter, "CoordinateY", "" + segments[i][j].getY());
            createNode(eventWriter, "hasPlayer", "" + segments[i][j].hasPlayer());
          }
          else if (segments[i][j].getObject().getStatus().equals("The chest is open and there is something inside.")) {
            createNode(eventWriter, "GameObject", "Open Chest");

            //Write the extra information required of a closed chest.
            addTabs(eventWriter, 2);
            eventWriter.add(eventFactory.createStartElement("", "", "Key"));
            eventWriter.add(end);
            addTabs(eventWriter, 1);
            createNode(eventWriter, "ID", "" + ((Chest) segments[i][j].getObject()).getKey().getID());
            addTabs(eventWriter, 1);
            createNode(eventWriter, "Description",
                    "" + ((Chest) segments[i][j].getObject()).getKey().getDescription());
            addTabs(eventWriter, 1);
            createNode(eventWriter, "Name",
                    "" + ((Chest) segments[i][j].getObject()).getKey().getName());
            addTabs(eventWriter, 2);
            eventWriter.add(eventFactory.createEndElement("", "", "Key"));
            eventWriter.add(end);
            createNode(eventWriter, "chestClosed", "false");

            createNode(eventWriter, "CoordinateX", "" + segments[i][j].getX());
            createNode(eventWriter, "CoordinateY", "" + segments[i][j].getY());
            createNode(eventWriter, "hasPlayer", "" + segments[i][j].hasPlayer());
          }
          else if (segments[i][j].getObject().getStatus().equals("The chest is open and it is empty.")) {
            createNode(eventWriter, "GameObject", "Empty Chest");

            createNode(eventWriter, "CoordinateX", "" + segments[i][j].getX());
            createNode(eventWriter, "CoordinateY", "" + segments[i][j].getY());
            createNode(eventWriter, "hasPlayer", "" + segments[i][j].hasPlayer());
          }
          else if (segments[i][j].getObject().getStatus().equals("The chest is closed.")) {
            createNode(eventWriter, "GameObject", "Chest");

            //Write the extra information required of a closed chest.
            addTabs(eventWriter, 2);
            eventWriter.add(eventFactory.createStartElement("", "", "Key"));
            eventWriter.add(end);
            addTabs(eventWriter, 1);
            createNode(eventWriter, "ID", "" + ((Chest) segments[i][j].getObject()).getKey().getID());
            addTabs(eventWriter, 1);
            createNode(eventWriter, "Description",
                    "" + ((Chest) segments[i][j].getObject()).getKey().getDescription());
            addTabs(eventWriter, 1);
            createNode(eventWriter, "Name",
                    "" + ((Chest) segments[i][j].getObject()).getKey().getName());
            addTabs(eventWriter, 2);
            eventWriter.add(eventFactory.createEndElement("", "", "Key"));
            eventWriter.add(end);
            createNode(eventWriter, "chestClosed", "true");

            createNode(eventWriter, "CoordinateX", "" + segments[i][j].getX());
            createNode(eventWriter, "CoordinateY", "" + segments[i][j].getY());
            createNode(eventWriter, "hasPlayer", "" + segments[i][j].hasPlayer());
          }
          else {
            createNode(eventWriter, "GameObject", segments[i][j].getObject().getType());
            createNode(eventWriter, "CoordinateX", "" + segments[i][j].getX());
            createNode(eventWriter, "CoordinateY", "" + segments[i][j].getY());
            createNode(eventWriter, "hasPlayer", "" + segments[i][j].hasPlayer());
          }

          eventWriter.add(tab);
          eventWriter.add(eventFactory.createEndElement("", "", "NewSegment"));
          eventWriter.add(end);
          eventWriter.add(end);
        }
      }
    }
    eventWriter.add(eventFactory.createEndElement("","","Segments"));
    eventWriter.add(eventFactory.createEndDocument());
    eventWriter.close();
  }

  /**
   * Helper method to help write lines of information to the eventWriter.
   *
   * @param eventWriter
   *          The XMLEventWriter we write to.
   *
   * @param name
   *          The tag name of the line.
   *
   * @param content
   *          The information to store in the line.
   *
   * @return
   */
  private void createNode(XMLEventWriter eventWriter, String name, String content)
          throws XMLStreamException{
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createDTD("\n");
    XMLEvent tab = eventFactory.createDTD("\t");

    StartElement startElement = eventFactory.createStartElement("","",name);
    eventWriter.add(tab);
    eventWriter.add(tab);
    eventWriter.add(startElement);

    Characters characters = eventFactory.createCharacters(content);
    eventWriter.add(characters);

    EndElement endElement = eventFactory.createEndElement("","",name);
    eventWriter.add(endElement);
    eventWriter.add(end);
  }

  public static void main(String[] args) {
    Parser p = new Parser(new Main());
    p.loadMap(new File(smallMapName));
  }
}
