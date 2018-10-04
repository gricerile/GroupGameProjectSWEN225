package GUI;

import javax.swing.ImageIcon;

public abstract class tempGameObject {
  private String name;
  private String description;
  private ImageIcon image;

  public tempGameObject(String name, String description, String image) {
    this.name = name;
    this.description = description;
    //if (image == null) {
      this.image = GUI.resizeImage("arrowImages/noImage.bmp", 50, 50);
    //} else {
     // this.image = GUI.resizeImage(image, 50, 50);
   // }
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return this.name;
  }

  public ImageIcon getImage() {
    return image;
  }
}
