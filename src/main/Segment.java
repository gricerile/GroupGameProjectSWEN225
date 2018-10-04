package main;

public class Segment {
	//fields
	private GameObject object;

	public Segment(GameObject object, int id) {
		this.object=object;
	}

	public GameObject getObject(){
		return this.object;
	}
}

