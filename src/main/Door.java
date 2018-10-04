package main;

import main.Key;

public class Door implements GameObject {
	// fields
	private Key key;
	private String doorName; // code name for door and key association, unique for each door and key
	private boolean unlocked;

	public Door( Key k, String s, boolean unlocked) {
		this.key = k;
		this.doorName = s;
		this.unlocked = unlocked;
	}

	public String getDoorName() {
		return this.doorName;
	}

	public boolean getUnlocked() {
		return this.unlocked;
	}

	public String unlock(Key k) {
		if (this.key.equals(k)) {
			if (this.unlocked == false) {
				this.unlocked = true;
				return "The door is now unlocked.";
			}
		}
		return "You must use the correct key on the door.";
	}

	@Override
	public String getStatus() {
		if (this.unlocked == false) {
			return "The door is closed and locked.";
		} else {
			return "The door is open.";
		}
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		if(unlocked) {
			return "Door Unlocked";
		}
		return "Door Locked";
	}

}
