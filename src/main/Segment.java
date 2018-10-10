package main;

import java.awt.Image;

import renderer.Texture;

public class Segment {
	//fields
	private GameObject object;
	private int x,y;
	private Image image;
	private Texture texture = new Texture();
	private boolean hasPlayer;

	public Segment(GameObject object, int x,int y) {
		this.object=object;
		this.x=x;
		this.y=y;
	}

	public boolean hasPlayer() {
		return this.hasPlayer;
	}

	public void setHasPlayer(boolean b) {
		 this.hasPlayer = b;
	}

	public GameObject getObject(){
		return this.object;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean equals(Segment s) {
		if(this.getX()==s.getX() && this.getY()==s.getY()) {
			return true;
		} else {
			return false;
		}
	}

	public String opensChest() {
		if(this.object instanceof Chest) {
			Chest c = (Chest) this.object;
			return c.openAndClose();
		}
		return "";
	}

	public Key takeFromChest() {
		if(this.object instanceof Chest) {
			Chest c = (Chest) this.object;
			return c.takeKey();
		}
		return null;
	}
}

