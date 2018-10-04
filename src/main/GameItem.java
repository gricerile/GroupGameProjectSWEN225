package main;

public abstract class GameItem {
	private String description;
	private String name;

	public GameItem(String d, String n) {
		this.description=d;
		this.name=n;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}
}
