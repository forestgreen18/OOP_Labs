package edu.labs.lab2;

import edu.labs.lab2.shape_editor.shapes.EllipseShape;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Menu Example");

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu("Файл");
        Menu editMenu = new Menu("Об'єкти");
        Menu helpMenu = new Menu("Довідка");

        // Create MenuItems
        MenuItem newItem = new MenuItem("Створити новий файл");
        MenuItem openFileItem = new MenuItem("Відкрити файл");
        MenuItem exitItem = new MenuItem("Вихід");

        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newItem, openFileItem, exitItem);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        VBox vBox = new VBox(menuBar);
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();


        Canvas canvas = new Canvas(400, 200);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Create an instance of EllipseShape
        EllipseShape ellipse = new EllipseShape(50, 50, 150, 100, gc);

        // Draw the ellipse
        ellipse.draw(gc);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
