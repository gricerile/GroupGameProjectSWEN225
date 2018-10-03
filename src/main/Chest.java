package main;

public class Chest implements GameObject {
	// fields
	private boolean open;
	private Key key;
	private int direction;

	public Chest(Key k, int direction) {
		this.key = k;
		this.open = false;
		this.direction = direction;
	}

	public String openAndClose() {
		if (this.open == true) {
			this.open = false;
			return "The chest is now closed.";
		} else {
			this.open = true;
			return "The chest is now open.";
		}
	}

	public Key takeKey() {
		if (this.open == true && this.key != null) {
			Key k = this.key;
			this.key = null;
			return k;
		} else {
			return null;
		}
	}

	@Override
	public String getStatus() {
		if (this.open == true && this.key != null) {
			return "The chest is open and there is something inside.";
		} else if (this.open == true && this.key == null) {
			return "The chest is open and it is empty.";
		} else {
			return "The chest is closed.";
		}
	}

	@Override
	public int getDirection() {
		return this.direction;
	}
}