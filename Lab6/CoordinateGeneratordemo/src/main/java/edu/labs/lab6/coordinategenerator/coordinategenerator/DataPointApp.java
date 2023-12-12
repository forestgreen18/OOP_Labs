package edu.labs.lab6.coordinategenerator.coordinategenerator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DataPointApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    TextArea outputArea = new TextArea();
    outputArea.setPrefHeight(200);
    outputArea.setPrefWidth(300);
    outputArea.setFont(new Font(18));

    Button generateButton = new Button("Generate Points");
    generateButton.setPrefWidth(150);
    generateButton.setPrefHeight(30);
    generateButton.setFont(new Font(18));
    generateButton.setOnAction(event -> {
      DataPointGenerator generator = new DataPointGenerator(10, 0.0, 100.0, 0.0, 100.0);
      double[][] points = generator.generatePoints();

      StringBuilder output = new StringBuilder();
      for (double[] point : points) {
        output.append("x: ").append(point[0]).append(", y: ").append(point[1]).append("\n");
      }

      outputArea.setText(output.toString());
    });

    VBox vbox = new VBox(10, outputArea, generateButton);
    vbox.setAlignment(Pos.CENTER);
    vbox.setPadding(new Insets(10));

    Scene scene = new Scene(vbox, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
