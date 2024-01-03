package edu.labs.configurableregistrationpanels.inputs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DateInput extends VBox {

  private final DatePicker datePicker;
  private final Label errorLabel;

  public DateInput(String initialValue) {
    datePicker = new DatePicker();
    errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);  // Set the text color to red

    if (!initialValue.isEmpty()) {
      DateTimeFormatter[] formatters = {
          DateTimeFormatter.ofPattern("dd.MM.yyyy"),
          DateTimeFormatter.ofPattern("yyyy-MM-dd")
          // Add more formatters as needed
      };

      LocalDate date = null;
      for (DateTimeFormatter formatter : formatters) {
        try {
          date = LocalDate.parse(initialValue, formatter);
          break;  // Exit the loop if the date was successfully parsed
        } catch (DateTimeParseException e) {
          // Ignore the exception and try the next formatter
        }
      }

      if (date != null) {
        datePicker.setValue(date);
      } else {
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
