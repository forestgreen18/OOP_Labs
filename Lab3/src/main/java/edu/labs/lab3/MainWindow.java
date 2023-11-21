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
        MenuItem clearCanvasItem = new MenuItem(titles.toolbarMenu.actions.erase);

        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newItem, openFileItem, exitItem);

        // Add menuItems to the shapesMenu
        shapesMenu.getItems().addAll(ellipseShapeItem, rectangleShapeItem, pointShapeItem, lineShapeItem, clearCanvasItem);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, shapesMenu, helpMenu);


        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ShapeObjectsEditor shapeObjectsEditor = new ShapeObjectsEditor(gc);
        List<EventHandler<MouseEvent>> handlers = new ArrayList<>();
        ShapeEditor shapeEditor = null;




        shapesMenu.setOnShowing(e -> {
            // Reset all menu items to their default state
            ellipseShapeItem.setText(titles.shapesMenu.shapes.ellipseShapeItemTitle);
            rectangleShapeItem.setText(titles.shapesMenu.shapes.rectangleShapeItemTitle);
            lineShapeItem.setText(titles.shapesMenu.shapes.lineShapeItemTitle   );
            pointShapeItem.setText(titles.shapesMenu.shapes.pointShapeItemTitle);
            // Add the tick to the currently selected shape
            if (shapeEditor instanceof EllipseShapeEditor) {
                setupShape(titles.shapesMenu.shapes.ellipseShapeItemTitle, new EllipseShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
            } else if (shapeEditor instanceof RectangleShapeEditor) {
                setupShape(titles.shapesMenu.shapes.rectangleShapeItemTitle, new RectangleShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
            } else if (shapeEditor instanceof LineShapeEditor) {
                setupShape(titles.shapesMenu.shapes.lineShapeItemTitle, new LineShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
            }
            else if (shapeEditor instanceof PointShapeEditor) {
                setupShape(titles.shapesMenu.shapes.pointShapeItemTitle, new PointShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
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
                            setupShape(titles.shapesMenu.shapes.ellipseShapeItemTitle, new EllipseShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
                        });
                        break;
                    case "rectangleButton":
                        // set the event handler for drawing a rectangle
                        button.setOnAction(e -> {
                            setupShape(titles.shapesMenu.shapes.rectangleShapeItemTitle, new RectangleShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
                        });
                        break;

                    case "pointButton":
                        // set the event handler for drawing a rectangle
                        button.setOnAction(e -> {
                            setupShape(titles.shapesMenu.shapes.pointShapeItemTitle, new PointShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
                        });
                        break;
                    case "lineButton":
                        // set the event handler for drawing a rectangle
                        button.setOnAction(e -> {
                            setupShape(titles.shapesMenu.shapes.lineShapeItemTitle, new LineShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
                        });
                        break;
                    case "eraseButton":
                        // set the event handler for drawing a rectangle
                        button.setOnAction(e -> {
                            primaryStage.setTitle(titles.toolbarMenu.actions.erase);
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


        ellipseShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.ellipseShapeItemTitle, new EllipseShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
            fireButtonById(toolbar, "ellipseButton");

        });

        rectangleShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.rectangleShapeItemTitle, new RectangleShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
            fireButtonById(toolbar, "rectangleButton");
        });

        lineShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.lineShapeItemTitle, new LineShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
            fireButtonById(toolbar, "lineButton");

        });

        pointShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.pointShapeItemTitle, new PointShapeEditor(shapeObjectsEditor, gc), primaryStage, shapeEditor, shapeObjectsEditor, canvas, handlers);
            fireButtonById(toolbar, "pointButton");

        });

        clearCanvasItem.setOnAction(e -> {
            primaryStage.setTitle(titles.toolbarMenu.actions.erase);
            shapeObjectsEditor.clearCanvas();
            fireButtonById(toolbar, "eraseButton");
        });


        VBox vBox = new VBox(menuBar, toolbar);
        StackPane root = new StackPane();
        root.getChildren().addAll(vBox, canvas);
        primaryStage.setScene(new Scene(root, 900, 800));
        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }



    public void setupShapeEditor(ShapeEditor shapeEditor, ShapeObjectsEditor shapeObjectsEditor, Canvas canvas, List<EventHandler<MouseEvent>> handlers) {
        // If there's an old shape editor, remove its event handler
        if (!handlers.isEmpty()) {
            for (EventHandler<MouseEvent> handler : handlers) {
                canvas.removeEventHandler(MouseEvent.ANY, handler);
            }
            handlers.clear();
        }

        // If there's a new shape editor, set it and add its event handler
        if (shapeEditor != null) {
            shapeObjectsEditor.setCurrentShapeEditor(shapeEditor);
            EventHandler<MouseEvent> newHandler = shapeEditor::processMouseEvent;
            canvas.addEventHandler(MouseEvent.ANY, newHandler);
            handlers.add(newHandler);
        }
    }

    public void setupShape(String title, ShapeEditor editor, Stage primaryStage, ShapeEditor shapeEditor, ShapeObjectsEditor shapeObjectsEditor,  Canvas canvas, List<EventHandler<MouseEvent>> handlers) {
        primaryStage.setTitle(title);
        shapeEditor = editor;
        setupShapeEditor(shapeEditor, shapeObjectsEditor, canvas, handlers);
    }


    public void fireButtonById(ToolBar toolbar, String id) {
        for (Node node : toolbar.getItems()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.getId().equals(id)) {
                    button.requestFocus();
                    break;
                }
            }
        }
    }







}
