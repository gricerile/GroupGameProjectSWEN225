package Victor.GroupProject.src;

import javafx.stage.FileChooser;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Parser {
    public static final String testMapFileName = "src/Victor/GroupProject/ParsingTester.xml";

    public static final String TRUECHECKER = "true";
    public static final String FALSECHECKER = "false";

    public static final String NORTHWALLCHECKER = "hasNorthWall";
    public static final String SOUTHWALLCHECKER = "hasSouthWall";
    public static final String EASTWALLCHECKER = "hasEastWall";
    public static final String WESTWALLCHECKER = "hasWestWall";
    public static final String DOORCHECKER = "door";
    public static final String CONTAINSOBJECTSCHECKER = "hasObjects";

    private ArrayList<TestingSegment> listOfSegmentRooms = new ArrayList<>();

    public void loadMap(File mapFile) {
        listOfSegmentRooms = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(mapFile));
            String header = buffer.readLine();
            String line = buffer.readLine();

            while(line != null) {
                //If we are loading the line signaling the start or end of a room description, do not read anything and move on.
                if (!line.contains("/Room")) {
                    //If we are reading a room.
                    if (line.equals("<Room>")) {
                        TestingSegment newSegment = new TestingSegment();
                        line = buffer.readLine();

                        while (line != null) {
                            String sections[] = line.split(" ");
                            //Read if walls in four cardinal directions are present or not.
                            if (sections[0].contains(NORTHWALLCHECKER)) { // North Wall
                                if (sections[1].equals(TRUECHECKER)) {
                                    newSegment.setHasNorthWall(true);
                                }
                                else if (sections[1].equals(FALSECHECKER)) {
                                    newSegment.setHasNorthWall(false);
                                }
                                else {
                                    System.out.println("Invalid specification of wall being present.");
                                }
                            }
                            else if (sections[0].contains(SOUTHWALLCHECKER)) { // South Wall
                                if (sections[1].equals(TRUECHECKER)) {
                                    newSegment.setHasSouthWall(true);
                                }
                                else if (sections[1].equals(FALSECHECKER)) {
                                    newSegment.setHasSouthWall(false);
                                }
                                else {
                                    System.out.println("Invalid specification of wall being present.");
                                }
                            }
                            else if (sections[0].contains(EASTWALLCHECKER)) { // East Wall
                                if (sections[1].equals(TRUECHECKER)) {
                                    newSegment.setHasEastWall(true);
                                }
                                else if (sections[1].equals(FALSECHECKER)) {
                                    newSegment.setHasEastWall(false);
                                }
                                else {
                                    System.out.println("Invalid specification of wall being present.");
                                }
                            }
                            else if (sections[0].contains(WESTWALLCHECKER)) { // West Wall
                                if (sections[1].equals(TRUECHECKER)) {
                                    newSegment.setHasWestWall(true);
                                }
                                else if (sections[1].equals(FALSECHECKER)) {
                                    newSegment.setHasWestWall(false);
                                }
                                else {
                                    System.out.println("Invalid specification of wall being present.");
                                }
                            }

                            //Read if a door is present, and if so, where is it located.
                            else if (sections[0].contains(DOORCHECKER)) {
                                if (sections[1].equals(TRUECHECKER)) {
                                    newSegment.setHasDoor(true);
                                    newSegment.setDoorLocation(sections[2]);
                                    if (sections[3].equals("locked")) {
                                        newSegment.setDoorLocked(true);
                                    }
                                    else if (sections[3].equals("unlocked")) {
                                        newSegment.setDoorLocked(false);
                                    }
                                    else {
                                        System.out.println("Invalid specification of door being locked.");
                                    }
                                }
                                else if (sections[1].equals(FALSECHECKER)) {
                                    newSegment.setHasDoor(false);
                                }
                                else {
                                    System.out.println("Invalid specification of door being present.");
                                }
                            }

                            //Read if there are objects present or not.
                            else if (sections[0].contains(CONTAINSOBJECTSCHECKER)) {
                                if (sections[1].equals(TRUECHECKER)) {
                                    newSegment.setHasObjects(true);
                                    line = buffer.readLine();
                                    sections = line.split(" ");
                                    if (sections[0].equals("<objects>")) {
                                        int i = 1;
                                        //Until the end of the line of objects, read the objects into the list of objects
                                        while (!sections[i].equals("</objects>")) {
                                            newSegment.addObject(sections[i++]);
                                        }
                                    }
                                }
                                else if (sections[1].equals(FALSECHECKER)) {
                                    newSegment.setHasObjects(false);
                                }
                                else {
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
        }
        catch (FileNotFoundException e) {
            System.out.println("Error file not found.");
        }
        catch (IOException e) {
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

    }


    public static void main(String[] args) {
        Parser p = new Parser();
        p.loadMap(new File(testMapFileName));

    }
}
