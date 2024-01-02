package edu.labs.configurableregistrationpanels.inputs;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EmailInput extends VBox {
  private TextField emailField;
  private Label errorLabel;
  private static final String EMAIL_REGEX = "^[\\w-]+@([\\w-]+\\.)+[\\w-]+$";


  public EmailInput(String initialValue) {
    emailField = new TextField();
    emailField.setText(initialValue);
    errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);  // Set the text color to red

    emailField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches(EMAIL_REGEX)) {
        errorLabel.setText("Invalid email format");
      } else {
        errorLabel.setText("");  // Clear the error message when the email is valid
      }
    });

    getChildren().addAll(emailField, errorLabel);
  }

  public String getText() {
    return emailField.getText();
  }

  public void clear() {
    emailField.clear();
  }

  public StringProperty textProperty() {
    return emailField.textProperty();
  }
}
