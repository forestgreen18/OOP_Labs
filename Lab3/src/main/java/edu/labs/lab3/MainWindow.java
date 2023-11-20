package edu.labs.lab3;

import edu.labs.lab3.shape_editor.ShapeObjectsEditor;
import edu.labs.lab3.shape_editor.editor.*;
import edu.labs.lab3.utils.JsonFileReader;
import edu.labs.lab3.utils.Titles;
import edu.labs.lab3.ui.PaintToolBar;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        JsonFileReader jsonFileReader = new JsonFileReader();
        Titles titles = jsonFileReader.readJsonFile();

        primaryStage.setTitle("Lab #3");

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu(titles.fileMenu.title);
        Menu shapesMenu = new Menu(titles.shapesMenu.title);
        Menu helpMenu = new Menu(titles.helpMenuTitle);

        // Create MenuItems
        MenuItem newItem = new MenuItem(titles.fileMenu.newItemTitle);
        MenuItem openFileItem = new MenuItem(titles.fileMenu.openFileItemTitle);
        MenuItem exitItem = new MenuItem(titles.fileMenu.exitItemTitle);

        // MenuTimes for shapes
        MenuItem ellipseShapeItem = new MenuItem(titles.shapesMenu.shapes.ellipseShapeItemTitle);
        MenuItem rectangleShapeItem = new MenuItem(titles.shapesMenu.shapes.rectangleShapeItemTitle);
        MenuItem pointShapeItem = new MenuItem(titles.shapesMenu.shapes.pointShapeItemTitle);
        MenuItem lineShapeItem = new MenuItem(titles.shapesMenu.shapes.lineShapeItemTitle);

        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newItem, openFileItem, exitItem);

        // Add menuItems to the shapesMenu
        shapesMenu.getItems().addAll(ellipseShapeItem, rectangleShapeItem, pointShapeItem, lineShapeItem);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, shapesMenu, helpMenu);


        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ShapeObjectsEditor shapeObjectsEditor = new ShapeObjectsEditor(gc);
        List<EventHandler<MouseEvent>> handlers = new ArrayList<>();
        final ShapeEditor[] shapeEditor = new ShapeEditor[1];

        ellipseShapeItem.setOnAction(e -> {
            shapeEditor[0] = new EllipseShapeEditor(shapeObjectsEditor, gc);
            setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas, handlers);
        });

        rectangleShapeItem.setOnAction(e -> {
            shapeEditor[0] = new RectangleShapeEditor(shapeObjectsEditor, gc);
            setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas, handlers);
        });

        lineShapeItem.setOnAction(e -> {
            shapeEditor[0] = new LineShapeEditor(shapeObjectsEditor, gc);
            setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas, handlers);
        });

        pointShapeItem.setOnAction(e -> {
            shapeEditor[0] = new PointShapeEditor(shapeObjectsEditor, gc);
            setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas, handlers);
        });

        shapesMenu.setOnShowing(e -> {
            // Reset all menu items to their default state
            ellipseShapeItem.setText(titles.shapesMenu.shapes.ellipseShapeItemTitle);
            rectangleShapeItem.setText(titles.shapesMenu.shapes.rectangleShapeItemTitle);
            lineShapeItem.setText(titles.shapesMenu.shapes.lineShapeItemTitle   );
            pointShapeItem.setText(titles.shapesMenu.shapes.pointShapeItemTitle);
            // Add the tick to the currently selected shape
            if (shapeEditor[0] instanceof EllipseShapeEditor) {
                ellipseShapeItem.setText(titles.shapesMenu.shapes.ellipseShapeItemTitle + titles.selectMark);
            } else if (shapeEditor[0] instanceof RectangleShapeEditor) {
                rectangleShapeItem.setText(titles.shapesMenu.shapes.rectangleShapeItemTitle + titles.selectMark);
            } else if (shapeEditor[0] instanceof LineShapeEditor) {
                lineShapeItem.setText(titles.shapesMenu.shapes.lineShapeItemTitle + titles.selectMark);
            }
            else if (shapeEditor[0] instanceof PointShapeEditor) {
                pointShapeItem.setText(titles.shapesMenu.shapes.pointShapeItemTitle + titles.selectMark);
            }
        });

        ToolBar toolbar = new PaintToolBar().createToolBar();

        for (Node node : toolbar.getItems()) {
            // check if the node is a button
            if (node instanceof Button) {
                // cast the node to a button
                Button button = (Button) node;
                // get the id of the button
                String id = button.getId();
                // set the event handler for the button based on its id
                switch (id) {
                    case "ellipseButton":
                        // set the event handler for drawing an ellipse
                        button.setOnAction(e -> {
                            shapeEditor[0] = new EllipseShapeEditor(shapeObjectsEditor, gc);
                            setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas, handlers);
                        });
                        break;
                    case "rectangleButton":
                        // set the event handler for drawing a rectangle
                        button.setOnAction(e -> {
                            shapeEditor[0] = new RectangleShapeEditor(shapeObjectsEditor, gc);
                            setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas, handlers);
                        });
                        break;

                    case "pointButton":
                        // set the event handler for drawing a rectangle
                        button.setOnAction(e -> {


                            shapeEditor[0] = new PointShapeEditor(shapeObjectsEditor, gc);
                            setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas, handlers);
                        });
                        break;
                    case "lineButton":
                        // set the event handler for drawing a rectangle
                        button.setOnAction(e -> {
                            shapeEditor[0] = new LineShapeEditor(shapeObjectsEditor, gc);
                            setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas, handlers);
                        });
                        break;
                    case "eraseButton":
                        // set the event handler for drawing a rectangle
                        button.setOnAction(e -> {
                            shapeObjectsEditor.clearCanvas();
                        });
                        break;
                    // add more cases as needed
                    default:
                        // handle the default case
                        break;
                }
            }
        }


        VBox vBox = new VBox(menuBar, toolbar);
        StackPane root = new StackPane();
        root.getChildren().addAll(vBox, canvas);
        primaryStage.setScene(new Scene(root, 900, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    public void setupShapeEditor(ShapeEditor[] shapeEditor, ShapeObjectsEditor shapeObjectsEditor, Canvas canvas, List<EventHandler<MouseEvent>> handlers) {
        // If there's an old shape editor, remove its event handler
        if (!handlers.isEmpty()) {
            for (EventHandler<MouseEvent> handler : handlers) {
                canvas.removeEventHandler(MouseEvent.ANY, handler);
            }
            handlers.clear();
        }

        // If there's a new shape editor, set it and add its event handler
        if (shapeEditor[0] != null) {
            shapeObjectsEditor.setCurrentShapeEditor(shapeEditor[0]);
            EventHandler<MouseEvent> newHandler = shapeEditor[0]::processMouseEvent;
            canvas.addEventHandler(MouseEvent.ANY, newHandler);
            handlers.add(newHandler);
        }
    }


}
