import java.util.ArrayList;

public class Segment {
	//fields
	Door door1;
	Door door2;
	ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public Segment(ArrayList<GameObject> objects,Door d1, Door d2) {
		this.objects=objects;
		this.door1=d1;
		this.door2=d2;
	}


}
