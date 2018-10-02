
public class Key extends GameObject {
	// fields
	private Door door;

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
}
