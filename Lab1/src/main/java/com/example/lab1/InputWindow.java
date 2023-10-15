package com.example.lab1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InputWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Input window");

        TextField textField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            System.out.println("Submit button clicked");
            // Add your submit logic here
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            System.out.println("Cancel button clicked");
            // Add your cancel logic here
        });

        HBox buttonBox = new HBox(10, submitButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, textField, buttonBox);
        vbox.setAlignment(Pos.CENTER);

        // Add margins to the TextField
        VBox.setMargin(textField, new Insets(0, 50, 0, 50));


        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
