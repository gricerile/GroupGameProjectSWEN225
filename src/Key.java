
public class Key implements GameObject {
	// fields
	private Door door;
	private int direction;
	private boolean canBeSeen;

	public Key(Door d, int direction) {
		this.door = d;
		this.direction=direction;
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

	@Override
	public int getDirection() {
		return this.direction;
	}
}
