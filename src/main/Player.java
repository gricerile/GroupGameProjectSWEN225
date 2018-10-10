package main;

import java.util.ArrayList;

public class Player {
	// fields
	private ArrayList<GameItem> inventory = new ArrayList<GameItem>();
	private Segment segment;
	private int direction; // 1-north 2-east 3-south 4-west

	public Player(Segment s, int i) {
		this.segment = s;
		this.direction = i;
	}

	public String rotate(int i) {
		this.direction = i;
		if (this.direction == 1) {
			return "Player is facing North.";
		} else if (this.direction == 2) {
			return "Player is facing East.";
		} else if (this.direction == 2) {
			return "Player is facing South.";
		} else {
			return "Player is facing West.";
		}
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

	public String giveKey(Key k) {
		if (!this.inventory.contains(k)) {
			this.inventory.add(k);
			return "Key is the Inventory.";
		}
		return "The Key is already in the Inventory.";
	}

}
