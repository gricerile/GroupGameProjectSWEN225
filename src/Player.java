import java.util.ArrayList;

public class Player {
	//fields
	private ArrayList<Key> inventory = new ArrayList<Key>();
	private int locx;
	private int locy;

	public Player (int x, int y) {
		this.locx=x;
		this.locy=y;
	}
}
