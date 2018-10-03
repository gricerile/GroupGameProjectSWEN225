package main;
import java.util.ArrayList;

public class Segment {
	//fields
	ArrayList<Door> doors;
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	ArrayList<Boolean> walls = new ArrayList<Boolean>();//0-north, 1-east, 2-south, 3-west. boolean if it has them

	public Segment(ArrayList<GameObject> objects,ArrayList<Door> doors) {
		this.objects=objects;
		this.doors=doors;
	}

	public ArrayList<Door> getDoors(){
		return this.doors;
	}

	public ArrayList<GameObject> getObjects(){
		return this.objects;
	}

	public ArrayList<Boolean> getWalls(){
		return this.walls;
	}
}

