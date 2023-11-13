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
        primaryStage.setTitle("JavaFX Shape Editor Test");

        Canvas canvas = new Canvas(400, 200);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        ShapeObjectsEditor shapeObjectsEditor = new ShapeObjectsEditor();
        ShapeEditor shapeEditor = new EllipseShapeEditor(shapeObjectsEditor, gc);

        // Attach a mouse event handler to the canvas
        canvas.addEventHandler(MouseEvent.ANY, shapeEditor::processMouseEvent);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
