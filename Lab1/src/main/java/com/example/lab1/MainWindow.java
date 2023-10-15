package com.example.lab1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Laboratory work #1");

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu worksMenu = new Menu("Works");

        // Create MenuItems
        MenuItem newItem = new MenuItem("Work #1");
        MenuItem openItem = new MenuItem("Work #2");

        // Add menu items to the Works menu
        worksMenu.getItems().addAll(newItem, openItem);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(worksMenu);

        Label label = new Label("Welcome to My JavaFX Project!");
        label.setAlignment(Pos.CENTER);


        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(label);

        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
