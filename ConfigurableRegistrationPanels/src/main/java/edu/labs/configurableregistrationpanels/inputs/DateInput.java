package edu.labs.configurableregistrationpanels.inputs;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateInput extends VBox {
  private DatePicker datePicker;
  private Label errorLabel;

  public DateInput(String initialValue) {
    datePicker = new DatePicker();
    errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);  // Set the text color to red

    if (!initialValue.isEmpty()) {
      try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        datePicker.setValue(LocalDate.parse(initialValue, formatter));
      } catch (DateTimeParseException e) {
        errorLabel.setText("Invalid date format");
      }
    }

    datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        errorLabel.setText("");  // Clear the error message when the date is valid
      }
    });

    getChildren().addAll(datePicker, errorLabel);
  }

  public LocalDate getDate() {
    return datePicker.getValue();
  }

  public void clear() {
    datePicker.setValue(null);
  }

  public ObjectProperty<LocalDate> dateProperty() {
    return datePicker.valueProperty();
  }
}
