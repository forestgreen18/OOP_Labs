package edu.labs.configurableregistrationpanels.inputs;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PasswordInput extends HBox {
  private PasswordField passwordField;
  private TextField textField;

  public PasswordInput() {
    passwordField = new PasswordField();
    textField = new TextField();
    textField.setManaged(false);
    textField.setVisible(false);

    CheckBox checkBox = new CheckBox("Show password");
    HBox.setMargin(checkBox, new Insets(0, 0, 0, 10));  // Add left margin to the checkbox

    checkBox.selectedProperty().addListener((observable, oldValue, isSelected) -> {
      if (isSelected) {
        textField.setText(passwordField.getText());
        textField.setManaged(true);
        textField.setVisible(true);
        passwordField.setManaged(false);
        passwordField.setVisible(false);
      } else {
        passwordField.setText(textField.getText());
        passwordField.setManaged(true);
        passwordField.setVisible(true);
        textField.setManaged(false);
        textField.setVisible(false);
      }
    });

    getChildren().addAll(passwordField, textField, checkBox);
  }

  public String getText() {
    if (textField.isVisible()) {
      return textField.getText();
    } else {
      return passwordField.getText();
    }
  }

  public void clear() {
    passwordField.clear();
    textField.clear();
  }

  public StringProperty textProperty() {
    if (textField.isVisible()) {
      return textField.textProperty();
    } else {
      return passwordField.textProperty();
    }
  }
}
