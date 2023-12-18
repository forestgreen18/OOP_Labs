package edu.labs.lab6.mainapp;

import edu.labs.lab6.mainapp.sockets.Client;
import edu.labs.lab6.mainapp.utils.AppLauncher;
import edu.labs.lab6.mainapp.utils.ClipboardManager;
import edu.labs.lab6.mainapp.utils.DataFormatter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FormWindow extends Application {

  public static void main(String[] args) {
    launch(args);
  }

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
      DataFormatter dataFormatter = new DataFormatter();
      String dataString = dataFormatter.getFormattedData(
          nPointField.getText(),
          xMinField.getText(),
          xMaxField.getText(),
          yMinField.getText(),
          yMaxField.getText()
      );

      ClipboardManager clipboardManager = new ClipboardManager();
      clipboardManager.copyToClipboard(dataString);

      new Thread(() -> {
        Client client = new Client();
        boolean isConnected = client.sendMessage("START");
        System.out.println(isConnected);
        if (!isConnected) {
          // Run launchApp in a new thread
          new Thread(() -> {
            AppLauncher.launchApp(
                "\"F:\\Labs\\OOP\\Lab6\\CoordinateGeneratordemo\\out\\artifacts\\CoordinateGenerator_jar\\coordinategenerator.bat\"");
          }).start();
        } else {
          client.sendMessage("UPDATE");
        }
      }).start();


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

    // Add a change listener that only allows numeric input
    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        textField.setText(newValue.replaceAll("[^\\d]", ""));
      }
    });

    return textField;
  }

  private Button createButton(String text) {
    Button button = new Button(text);
    button.setPrefWidth(150); // Increase the width
    button.setPrefHeight(30); // Increase the height
    button.setFont(new Font(18)); // Increase the font size
    return button;
  }
}