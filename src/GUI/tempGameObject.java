package GUI;

public abstract class tempGameObject {
  private String name;
  private String description;

  public tempGameObject(String name, String description) {
    this.name = name;
    this.description = description;
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

}
