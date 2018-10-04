package main;

public class Key extends GameItem {
	// fields
	private Door door;

	public Key(Door d, String description, String name) {
		super(description, name);
		this.door = d;
	}

	public Door getDoor() {
		return this.door;
	}

	public boolean equals(Key k) {
		if (this.door.getDoorName().equals(k.getDoor().getDoorName())) {
			return true;
		} else {
			return false;
		}
	}

	public String getKeysDoorName() {
		return this.door.getDoorName();
	}

	public String getStatus() {
		return ("The Key unlocks/locks the door " + getKeysDoorName());
	}

	public String getType() {
		// TODO Auto-generated method stub
		return "Key";
	}
}
