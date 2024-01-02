package edu.labs.configurableregistrationpanels.datastructures;

public class FormFieldDataStructure {
  private String title;
  private String type;
  private String value;

  public FormFieldDataStructure(String title, String type, String value) {
    this.title = title;
    this.type = type;
    this.value = value;
  }

  // getters
  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  // setters
  public void setTitle(String title) {
    this.title = title;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
