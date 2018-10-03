
public class Door implements GameObject {
	// fields
	private Segment seg1;
	private Segment seg2;
	private Key key;
	private String doorName; //code name for door and key association, unique for each door and key
	private boolean open;
	private boolean unlocked;
	private int direction;

	public Door(Segment s1, Segment s2, Key k, String s, int direction) {
		this.seg1 = s1;
		this.seg2 = s2;
		this.key = k;
		this.doorName = s;
		this.open = false;
		this.unlocked = false;
		this.direction=direction;
	}

	public String getDoorName() {
		return this.doorName;
	}

	public String openAndClose() {
		if (this.unlocked == true && this.open == false) {
			this.open = true;
			return "The door is now open.";
		} else if (this.open == true) {
			this.open = false;
			return "The door is now closed.";
		} else {
			return "The door is locked and closed, you must unlock it first.";
		}
	}

	public String unlockAndLock(Key k) {
		if (this.key.equals(k)) {
			if (this.unlocked == true && this.open == true) {
				return "The door must be closed first to lock it.";
			} else if (this.unlocked == true && this.open == false) {
				this.unlocked = false;
				return "The door is now locked.";
			} else if (this.unlocked == false && this.open == false) {
				this.unlocked = true;
				return "The door is now unlocked.";
			}
		}
		return "You must use the correct key on the door.";
	}

	public Segment getSeg1() {
		return this.seg1;
	}

	public Segment getSeg2() {
		return this.seg2;
	}

	@Override
	public String getStatus() {
		if(this.unlocked==false) {
			return "The door is closed and locked.";
		} else if(this.unlocked==true && this.open==false) {
			return "The door is closed and unlocked.";
		}	 else {
			return "The door is open.";
		}
	}

	@Override
	public int getDirection() {
		return this.direction;
	}

}
