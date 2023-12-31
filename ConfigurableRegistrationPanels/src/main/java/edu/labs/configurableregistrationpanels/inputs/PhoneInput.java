package edu.labs.configurableregistrationpanels.inputs;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PhoneInput extends VBox {
  private static final String PHONE_REGEX = "^\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$";
  private TextField phoneField;
  private Label errorLabel;

  public PhoneInput() {
    phoneField = new TextField();
    errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);  // Set the text color to red

    phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches(PHONE_REGEX)) {
        errorLabel.setText("Invalid phone number format");
      } else {
        errorLabel.setText("");  // Clear the error message when the phone number is valid
      }
    });

    getChildren().addAll(phoneField, errorLabel);
  }

  public String getText() {
    return phoneField.getText();
  }

  public StringProperty textProperty() {
    return phoneField.textProperty();
  }

  public void clear() {
    phoneField.clear();
  }
}
