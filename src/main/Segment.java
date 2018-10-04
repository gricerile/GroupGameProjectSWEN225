package main;
import java.util.ArrayList;

import GUI.GUI.moveDirection;

public class Segment {
	//fields
	private int segmentID;
	private ArrayList<Door> doors;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<Boolean> walls = new ArrayList<Boolean>();//0-north, 1-east, 2-south, 3-west. boolean if it has them

	public Segment(ArrayList<GameObject> objects,ArrayList<Door> doors, int id) {
		this.objects=objects;
		this.doors=doors;
		this.segmentID=id;
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

	public boolean equals(Segment s) {
		if(s.getID()==this.getID()) {
			return true;
		} else {
			return false;
		}
	}

	public int getID() {
		return this.segmentID;
	}

	public Door getDoor(moveDirection direction) {
		if (direction == moveDirection.up) {
			if (this.doors.get(0) != null) {
				return this.doors.get(0);
			}
		} else if (direction == moveDirection.right) {
			if (this.doors.get(1) != null) {
				return this.doors.get(1);
			}
		} else if (direction == moveDirection.down) {
			if (this.doors.get(2) != null) {
				return this.doors.get(2);
			}
		} else if (direction == moveDirection.left) {
			if (this.doors.get(3) != null) {
				return this.doors.get(3);
			}
		}
		return null;
	}

	public boolean hasDoor(moveDirection direction) {
		if (direction == moveDirection.up) {
			if (this.doors.get(0) != null) {
				return true;
			}
		} else if (direction == moveDirection.right) {
			if (this.doors.get(1) != null) {
				return true;
			}
		} else if (direction == moveDirection.down) {
			if (this.doors.get(2) != null) {
				return true;
			}
		} else if (direction == moveDirection.left) {
			if (this.doors.get(3) != null) {
				return true;
			}
		}
		return false;
	}
}

