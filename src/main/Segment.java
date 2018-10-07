package main;

public class Segment {
	//fields
	private GameObject object;
	private int id;

	public Segment(GameObject object, int id) {
		this.object=object;
		this.id=id;
	}

	public GameObject getObject(){
		return this.object;
	}

	public int getID() {
		return this.id;
	}

	public boolean equals(Segment s) {
		if(this.id==s.getID()) {
			return true;
		} else {
			return false;
		}
	}
}

