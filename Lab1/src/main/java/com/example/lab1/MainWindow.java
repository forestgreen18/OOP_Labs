package com.example.lab1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class MainWindow extends Application {
    private final String labelText = "Received value: ";
    private boolean isWindowOpen = false;
    private Stage currentWindow = null;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Laboratory work #1");

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu worksMenu = new Menu("Works");
        worksMenu.getStyleClass().add("menu");

        // Create MenuItems
        MenuItem inputWindowButton = new MenuItem("Work #1");
        MenuItem scrollbarWindowButton = new MenuItem("Work #2");

        System.out.println(System.getProperty("javafx.runtime.version"));


        // Add menu items to the Works menu
        worksMenu.getItems().addAll(inputWindowButton, scrollbarWindowButton);


        // Add menus to the menu bar
        menuBar.getMenus().addAll(worksMenu);


        Label label = new Label(labelText);
        label.setAlignment(Pos.CENTER);

        inputWindowButton.setOnAction(e -> {
            if (!isWindowOpen) {
                // Create a new InputWindow and open it

                InputWindow inputWindow = new InputWindow(
                        value -> {
                            // Add your logic here to handle the value
                            System.out.println(labelText + " from input window: " + value);
                            label.setText("Received input: " + value);
                        },
                        () -> {
                            // Add your logic here to clear the input
                            System.out.println("Cancel button clicked");
                            label.setText(labelText);
                            isWindowOpen = false;
                        }
                );

                startNewWindow(inputWindow);
            }
        });


        scrollbarWindowButton.setOnAction(e -> {
            if (!isWindowOpen) {
                // Create a new ScrollbarWindow and open it
                ScrollbarWindow scrollbarWindow = new ScrollbarWindow(
                        value -> {
                            // Add your logic here to handle the value
                            System.out.println(labelText + " from scrollbar window: " + value);
                            label.setText("Received input: " + value);
                        },
                        () -> {
                            // Add your logic here to clear the input
                            System.out.println("Cancel button clicked");
                            label.setText(labelText);
                            isWindowOpen = false;
                        }
                );
                startNewWindow(scrollbarWindow);
            }
        });



        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(label);

        Scene scene = new Scene(borderPane, 400, 300);
        URL cssUrl = getClass().getResource("/com/example/lab1/mainWindow.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.out.println("Resource not found: /com/example/lab1/mainWindow.css");
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    private void startNewWindow(Application window) {
        if (currentWindow != null) {
            currentWindow.close();
        }
        Stage stage = new Stage();
        stage.setOnHidden(event -> {
            System.out.println("Window closed");
            isWindowOpen = false;
            currentWindow = null;
        });
        try {
            window.start(stage);
            isWindowOpen = true;
            currentWindow = stage;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
