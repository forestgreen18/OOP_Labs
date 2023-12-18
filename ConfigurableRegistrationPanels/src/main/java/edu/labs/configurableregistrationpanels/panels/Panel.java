package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public abstract class Panel extends Parent {
  protected VBox panel;
  public Button nextButton;
  public Button backButton;
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

  public VBox getPanelLayout() {
    return panel;
  }

  public abstract void handleNextButton();
  public abstract void handleBackButton();

  @Override
  public Node getStyleableNode() {
    return super.getStyleableNode();
  }

  public void hideNextButton() {
    nextButton.setVisible(false);
  }

  public void saveInput(DataSaver dataSaver) {
    for (int i = 0; i < textFields.length; i++) {
      TextField textField = textFields[i];
      String fieldName = labels[i].getText();
      textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue) {  // If the text field has lost focus
          dataSaver.saveInput(getPanelType(), fieldName, textField.getText());
          System.out.println(dataSaver.getData());
        }
      });
    }
  }

  public abstract String getPanelType();

}
