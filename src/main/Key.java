package main;

public class Key implements GameObject {
	// fields
	private Door door;
	private boolean canBeSeen;

	public Key(Door d) {
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

	@Override
	public String getStatus() {
		return ("The Key unlocks/locks the door " + getKeysDoorName());
	}
}
