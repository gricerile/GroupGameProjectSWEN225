import java.util.ArrayList;

public class Player {
	// fields
	private ArrayList<Key> inventory = new ArrayList<Key>();
	private int locx;
	private int locy;

	public Player(int x, int y) {
		this.locx = x;
		this.locy = y;
	}

	public int getX() {
		return this.locx;
	}

	public int getY() {
		return this.locy;
	}

	public void move(int x, int y) {
		this.locx = x;
		this.locy = y;
	}

	public String getInventory() {
		if (this.inventory.isEmpty() || this.inventory == null) {
			return "The inventory is empty.";
		}
		String s = "";
		for (Key k : this.inventory) {
			s += (" " + k.getKeysDoorName());
		}
		return s;
	}
}
