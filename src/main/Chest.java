package main;

import java.awt.Graphics;

public class Chest implements GameObject {
  // fields
  private boolean open;
  private Key key;

  public Chest(Key k) {
    this.key = k;
    this.open = false;
  }

  public Key getKey() {
    return this.key;
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
    } else if (this.open==false){
    	System.out.println("The Chest is not open.");
      return null;
    } else {
    	System.out.println("Chest is empty");
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
  public String getType() {
    // TODO Auto-generated method stub
    if(this.open==true) {
      return "RedChest";
    }
    return "YellowChest";
  }

@Override
public void draw(Graphics g, int windowWidth, int windowHeight) {
	// TODO Auto-generated method stub

}

}
