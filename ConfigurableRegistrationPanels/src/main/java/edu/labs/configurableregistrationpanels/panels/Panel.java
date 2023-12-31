package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Panel extends Parent {
  protected VBox panel;
  protected HBox buttonBox;
  public Button nextButton;
  public Button backButton;
  public Button cancelButton;


  protected Control[] controls;
  protected Label[] labels;
  protected DataSaver dataSaver;

  public Panel(String[] fieldTitles, String[] fieldTypes, DataSaver dataSaver) {
    this.dataSaver = dataSaver;
    panel = new VBox();
    buttonBox = new HBox();  // Initialize the HBox here

    int numFields = fieldTitles.length;
    controls = new Control[numFields];

    labels = new Label[numFields];

    for (int i = 0; i < numFields; i++) {
      labels[i] = new Label(fieldTitles[i]);

      switch (fieldTypes[i]) {
        case "text":
          controls[i] = new TextField();
          break;
        case "password":
          controls[i] = new PasswordField();
          break;
        case "date":
          controls[i] = new DatePicker();
          break;
        // Add more cases as needed
        default:
          throw new IllegalArgumentException("Invalid field type: " + fieldTypes[i]);
      }

      panel.getChildren().addAll(labels[i], controls[i]);
    }
    nextButton = new Button("Next >>");  // Initialize nextButton
    cancelButton = new Button("Cancel");  // Initialize cancelButton
    backButton = new Button("<< Back");

    buttonBox.getChildren().addAll( backButton,nextButton, cancelButton);  // Add the buttons to the HBox

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
  buttonBox.getChildren().remove(nextButton);
  }

  public void saveInput(DataSaver dataSaver) {
    for (int i = 0; i < controls.length; i++) {
      Control control = controls[i];
      String fieldName = labels[i].getText();

      if (control instanceof TextField) {
        TextField textField = (TextField) control;
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
          dataSaver.saveInput(getPanelType(), fieldName, newValue);
          System.out.println(dataSaver.getData());
        });
      } else if (control instanceof DatePicker) {
        DatePicker datePicker = (DatePicker) control;
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
          dataSaver.saveInput(getPanelType(), fieldName, newValue.toString());
          System.out.println(dataSaver.getData());
        });
      }
      // Add more cases as needed
    }
  }



  public abstract String getPanelType();

  public void clearInputFields() {
    for (Control control : controls) {
      if (control instanceof TextField) {
        ((TextField) control).clear();
      } else if (control instanceof DatePicker) {
        ((DatePicker) control).setValue(null);
      }
      // Add more cases as needed
    }
  }
}
