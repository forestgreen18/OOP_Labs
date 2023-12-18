package edu.labs.configurableregistrationpanels.panels;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public abstract class Panel {
  protected VBox panel;
  protected Button nextButton;
  protected Button backButton;
  protected Button cancelButton;
  protected TextField[] textFields;

  public Panel(int numFields) {
    panel = new VBox();
    textFields = new TextField[numFields];

    for (int i = 0; i < numFields; i++) {
      textFields[i] = new TextField();
      panel.getChildren().add(textFields[i]);
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
}
