package main;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import renderer.Texture;

public class Segment {
	//fields
	private GameObject object;
	private int x,y;
	private double imageXCoord;
	private double imageYCoord;
	public Image image;
	public Texture texture = new Texture();


	public Segment(GameObject object, double imageX, double imageY, Image image) {
		this.object=object;
		this.imageXCoord = imageX;
		this.imageYCoord = imageY;
		Texture t = new Texture();
		image = t.onLoad("/tx/grass.png");
		this.image = image;
	}

	public double getImageXCoord() {
		return imageXCoord;
	}

	public void setImageXCoord(double imageXCoord) {
		this.imageXCoord = imageXCoord;
	}

	public double getImageYCoord() {
		return imageYCoord;
	}

	public void setImageYCoord(double imageYCoord) {
		this.imageYCoord = imageYCoord;
	}

	public Image getImage() {
		if(this.image == null) {
			System.out.println("There is nothing here");
		}
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	// find the center of the image tile
	public void findImageX() {
		this.imageXCoord = image.getWidth(null)/2;
	}

	public void findImageY() {
		this.imageYCoord = image.getHeight(null)/2;
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
}

