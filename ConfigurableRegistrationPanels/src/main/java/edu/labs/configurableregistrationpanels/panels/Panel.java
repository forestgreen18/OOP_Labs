package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Panel extends Parent {
  protected VBox panel;
  protected HBox buttonBox;
  public Button nextButton;
  public Button backButton;
  public Button cancelButton;


  protected TextField[] textFields;
  protected Label[] labels;
  protected DataSaver dataSaver;

  public Panel(String[] fieldNames , DataSaver dataSaver) {
    this.dataSaver = dataSaver;
    panel = new VBox();
    buttonBox = new HBox();  // Initialize the HBox here

    int numFields = fieldNames.length;
    textFields = new TextField[numFields];
    labels = new Label[numFields];

    for (int i = 0; i < numFields; i++) {
      labels[i] = new Label(fieldNames[i]);
      textFields[i] = new TextField();
      panel.getChildren().addAll(labels[i], textFields[i]);
    }

    nextButton = new Button("Next >>");  // Initialize nextButton
    cancelButton = new Button("Cancel");  // Initialize cancelButton

    buttonBox.getChildren().addAll(nextButton, cancelButton);  // Add the buttons to the HBox

    buttonBox.setSpacing(10);  // Add some space between the buttons

    VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));  // Add some margin to the top of the buttonBox
    panel.getChildren().add(buttonBox);  // Add the HBox to the panel


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
    buttonBox.getChildren().remove(0);
  }

  public void saveInput(DataSaver dataSaver) {
    for (int i = 0; i < textFields.length; i++) {
      TextField textField = textFields[i];
      String fieldName = labels[i].getText();
      textField.textProperty().addListener((observable, oldValue, newValue) -> {
        dataSaver.saveInput(getPanelType(), fieldName, newValue);
        System.out.println(dataSaver.getData());
      });
    }
  }


  public abstract String getPanelType();

  public void clearInputFields() {
    for (TextField textField : textFields) {
      textField.clear();
    }
  }


}
