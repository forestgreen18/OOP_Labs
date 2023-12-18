package edu.labs.configurableregistrationpanels.panels;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public abstract class Panel extends Parent {
  protected VBox panel;
  public Button nextButton;
  protected Button backButton;
  public Button cancelButton;
  protected TextField[] textFields;
  protected Label[] labels;

  public Panel(String[] fieldNames) {
    panel = new VBox();
    int numFields = fieldNames.length;
    textFields = new TextField[numFields];
    labels = new Label[numFields];

    for (int i = 0; i < numFields; i++) {
      labels[i] = new Label(fieldNames[i]);
      textFields[i] = new TextField();
      panel.getChildren().addAll(labels[i], textFields[i]);
    }

    nextButton = new Button("Next >>");
    cancelButton = new Button("Cancel");

    panel.getChildren().addAll(nextButton, cancelButton);
  }

  public VBox getPanel() {
    return panel;
  }

  public abstract void handleNextButton();
  public abstract void handleBackButton();

  @Override
  public Node getStyleableNode() {
    return super.getStyleableNode();
  }
}
