package edu.labs.lab2;

import edu.labs.lab2.shape_editor.ShapeObjectsEditor;
import edu.labs.lab2.shape_editor.editor.EllipseShapeEditor;
import edu.labs.lab2.shape_editor.editor.ShapeEditor;
import edu.labs.lab2.shape_editor.shapes.EllipseShape;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab #2");

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu("Файл");
        Menu shapesMenu = new Menu("Об'єкти");
        Menu helpMenu = new Menu("Довідка");

        // Create MenuItems
        MenuItem newItem = new MenuItem("Створити новий файл");
        MenuItem openFileItem = new MenuItem("Відкрити файл");
        MenuItem exitItem = new MenuItem("Вихід");

        // MenuTimes for shapes

        MenuItem ellipseShapeItem = new MenuItem("Еліпс");
        MenuItem rectangleShapeItem = new MenuItem("Прямокутник");
        MenuItem pointShapeItem = new MenuItem("Точка");
        MenuItem lineShapeItem = new MenuItem("Лінія");


        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newItem, openFileItem, exitItem);

        // Add menuItems to the shapesMenu
        shapesMenu.getItems().addAll(ellipseShapeItem, rectangleShapeItem, pointShapeItem, lineShapeItem);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, shapesMenu, helpMenu);
        VBox vBox = new VBox(menuBar);



        Canvas canvas = new Canvas(400, 200);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        ShapeObjectsEditor shapeObjectsEditor = new ShapeObjectsEditor(gc);



        final ShapeEditor[] shapeEditor = new ShapeEditor[1];

        ellipseShapeItem.setOnAction(e -> {
            shapeEditor[0] = new EllipseShapeEditor(shapeObjectsEditor, gc);

            System.out.println("Ellipse menu item clicked");
            setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas);
        });





        StackPane root = new StackPane();
        root.getChildren().addAll(vBox, canvas);
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setupShapeEditor(ShapeEditor[] shapeEditor, ShapeObjectsEditor shapeObjectsEditor, Canvas canvas) {
        if (shapeEditor[0] != null) {
            shapeObjectsEditor.setCurrentShapeEditor(shapeEditor[0]);

            // Attach a mouse event handler to the canvas
            canvas.addEventHandler(MouseEvent.ANY, shapeEditor[0]::processMouseEvent);
        }
    }


}
