package main;
import java.util.ArrayList;

public class Segment {
	//fields
	ArrayList<Door> doors;
	ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public Segment(ArrayList<GameObject> objects,ArrayList<Door> doors) {
		this.objects=objects;
		this.doors=doors;
	}


}

