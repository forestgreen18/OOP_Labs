package edu.labs.lab6.mainapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class FormWindow extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Форма вводу даних");
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(5);
    grid.setHgap(5);
    grid.setAlignment(Pos.CENTER);


    TextField nPointField = createTextField("Enter nPoint");
    GridPane.setConstraints(nPointField, 0, 0);
    grid.getChildren().add(nPointField);


    TextField xMinField = createTextField("Enter xMin");
    GridPane.setConstraints(xMinField, 0, 1);
    grid.getChildren().add(xMinField);

    TextField xMaxField = createTextField("Enter xMax");
    GridPane.setConstraints(xMaxField, 0, 2);
    grid.getChildren().add(xMaxField);

    TextField yMinField = createTextField("Enter yMin");
    GridPane.setConstraints(yMinField, 0, 3);
    grid.getChildren().add(yMinField);

    TextField yMaxField = createTextField("Enter yMax");
    GridPane.setConstraints(yMaxField, 0, 4);
    grid.getChildren().add(yMaxField);


    Button submitButton = createButton("Передати дані");
    GridPane.setConstraints(submitButton, 1, 0);
    grid.getChildren().add(submitButton);

    submitButton.setOnAction(event -> {
      // Get the values from the text fields
      String nPoint = nPointField.getText();
      String xMin = xMinField.getText();
      String xMax = xMaxField.getText();
      String yMin = yMinField.getText();
      String yMax = yMaxField.getText();

      // Convert the values to a single string in the desired format
      String dataString = "nPoint: " + nPoint + ";\n" +
          "xMin: " + xMin + ";\n" +
          "xMax: " + xMax + ";\n" +
          "yMin: " + yMin + ";\n" +
          "yMax: " + yMax + ";";

      // Create a StringSelection object
      java.awt.datatransfer.StringSelection stringSelection = new java.awt.datatransfer.StringSelection(dataString);

      // Get the system clipboard
      java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();

      // Write the string to the clipboard
      clipboard.setContents(stringSelection, null);
    });


    Scene scene = new Scene(grid, 700, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private TextField createTextField(String promptText) {
    TextField textField = new TextField();
    textField.setPromptText(promptText);
    textField.setPrefWidth(200); // Increase the width
    textField.setPrefHeight(30); // Increase the height
    textField.setFont(new Font(18)); // Increase the font size
    return textField;
  }

  private Button createButton(String text) {
    Button button = new Button(text);
    button.setPrefWidth(150); // Increase the width
    button.setPrefHeight(30); // Increase the height
    button.setFont(new Font(18)); // Increase the font size
    return button;
  }

  public static void main(String[] args) {
    launch(args);
  }
}