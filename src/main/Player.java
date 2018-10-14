package main;

import java.util.ArrayList;

public class Player {
	// fields
	private ArrayList<GameItem> inventory = new ArrayList<GameItem>();
	private Segment segment;

	public Player(Segment s) {
		this.segment = s;
	}

	public Segment getSegment() {
		return this.segment;
	}

	public void move(Segment s) {
		this.segment = s;
	}

	public ArrayList<GameItem> getInventory() {
		return this.inventory;
	}

	public String giveKey(Key k, Main m) {
		if (!this.inventory.contains(k)) {
			this.inventory.add(k);
			m.getGUI().getFrame().getInventoryPanel().addItemToInventory(k);
			return "Key "+ k.getID() +" is the Inventory.";
		}
		return "The Key is already in the Inventory.";
	}

}
