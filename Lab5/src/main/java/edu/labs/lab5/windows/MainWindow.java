package edu.labs.lab5.windows;

import edu.labs.lab5.editor.Editor;
import edu.labs.lab5.shapes.*;
import edu.labs.lab5.utils.FileHandler;
import edu.labs.lab5.utils.fileReaders.JsonFileReader;
import edu.labs.lab5.utils.fileReaders.ShapeFileReader;
import edu.labs.lab5.utils.fileReaders.Titles;
import edu.labs.lab5.ui.PaintToolBar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Application {
    private Shape shape;

    @Override
    public void start(Stage primaryStage) {
        JsonFileReader jsonFileReader = new JsonFileReader();
        Titles titles = jsonFileReader.readJsonFile();

        primaryStage.setTitle("Lab #5");

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu(titles.fileMenu.title);
        Menu shapesMenu = new Menu(titles.shapesMenu.title);
        Menu helpMenu = new Menu(titles.helpMenu.title);

        // Create MenuItems
        MenuItem newItem = new MenuItem(titles.fileMenu.newItemTitle);
        MenuItem openFileItem = new MenuItem(titles.fileMenu.openFileItemTitle);
        MenuItem exitItem = new MenuItem(titles.fileMenu.exitItemTitle);

        // MenuTimes for shapes
        MenuItem ellipseShapeItem = new MenuItem(titles.shapesMenu.shapes.ellipseShapeItemTitle);
        MenuItem rectangleShapeItem = new MenuItem(titles.shapesMenu.shapes.rectangleShapeItemTitle);
        MenuItem pointShapeItem = new MenuItem(titles.shapesMenu.shapes.pointShapeItemTitle);
        MenuItem lineShapeItem = new MenuItem(titles.shapesMenu.shapes.lineShapeItemTitle);
        MenuItem lineSegmentWithCirclesAtEndsShapeItem = new MenuItem(titles.shapesMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle);
        MenuItem parallelepipedShapeItem = new MenuItem(titles.shapesMenu.shapes.parallelepipedShapeItemTitle);
        MenuItem clearCanvasItem = new MenuItem(titles.toolbarMenu.actions.erase);

        MenuItem showCoordinatesTableItem = new MenuItem(titles.helpMenu.items.showCoordinatesTableItemTitle);

        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newItem, openFileItem, exitItem);

        // Add menuItems to the shapesMenu
        shapesMenu.getItems().addAll(ellipseShapeItem, rectangleShapeItem, pointShapeItem, lineShapeItem, lineSegmentWithCirclesAtEndsShapeItem, parallelepipedShapeItem, clearCanvasItem);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, shapesMenu, helpMenu);

        // Add Menus to the HelpMenu
        helpMenu.getItems().addAll(showCoordinatesTableItem);


        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Editor editor = Editor.getInstance();
        editor.setGraphicsContext(gc);
        List<EventHandler<MouseEvent>> handlers = new ArrayList<>();

        TableWindow tableWindow = TableWindow.getInstance();
        FileHandler fileHandler = new FileHandler(primaryStage, gc, editor);


        showCoordinatesTableItem.setOnAction(e -> {
            if (tableWindow.isShowing()) {
                tableWindow.hide();
                showCoordinatesTableItem.setText(titles.helpMenu.items.showCoordinatesTableItemTitle);
            } else {
                showCoordinatesTableItem.setText("Сховати таблицю з координатами");
                tableWindow.show();
            }
        });



        shapesMenu.setOnShowing(e -> {
            // Reset all menu items to their default state
            ellipseShapeItem.setText(titles.shapesMenu.shapes.ellipseShapeItemTitle);
            rectangleShapeItem.setText(titles.shapesMenu.shapes.rectangleShapeItemTitle);
            lineShapeItem.setText(titles.shapesMenu.shapes.lineShapeItemTitle);
            pointShapeItem.setText(titles.shapesMenu.shapes.pointShapeItemTitle);
            lineSegmentWithCirclesAtEndsShapeItem.setText(titles.shapesMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle);
            parallelepipedShapeItem.setText(titles.shapesMenu.shapes.parallelepipedShapeItemTitle);
            // Add the tick to the currently selected shape
            if (shape instanceof EllipseShape) {
                setupShape(titles.shapesMenu.shapes.ellipseShapeItemTitle, primaryStage, new EllipseShape(0,0,0,0,gc) , editor, canvas, handlers);
            } else if (shape instanceof RectangleShape) {

                setupShape(titles.shapesMenu.shapes.rectangleShapeItemTitle, primaryStage, new RectangleShape(0,0,0,0,gc) , editor, canvas, handlers);
            } else if (shape instanceof LineShape) {
                setupShape(titles.shapesMenu.shapes.lineShapeItemTitle, primaryStage, new LineShape(0,0,0,0,gc)  , editor, canvas, handlers);
            }
            else if (shape instanceof PointShape) {
                setupShape(titles.shapesMenu.shapes.pointShapeItemTitle, primaryStage, new PointShape(0,0,gc) , editor, canvas, handlers);
            } else if (shape instanceof LineSegmentWithCirclesAtEndsShape) {
                setupShape(titles.shapesMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle, primaryStage, new LineSegmentWithCirclesAtEndsShape(0,0,0,0,gc)  , editor, canvas, handlers);
            } else if (shape instanceof ParallelepipedShape) {
                setupShape(titles.shapesMenu.shapes.parallelepipedShapeItemTitle, primaryStage, new ParallelepipedShape(0,0,0,0,gc)  , editor, canvas, handlers);
            }
        });

        ToolBar toolbar = new PaintToolBar().createToolBar();

        for (Node node : toolbar.getItems()) {
            // check if the node is a button
            if (node instanceof Button button) {
                // get the id of the button
                String id = button.getId();
                // set the event handler for the button based on its id
                switch (id) {
                    case "ellipseButton" ->
                        // set the event handler for drawing an ellipse
                    button.setOnAction(e -> setupShape(titles.shapesMenu.shapes.ellipseShapeItemTitle, primaryStage, new EllipseShape(0,0,0,0,gc) , editor, canvas, handlers));

                    case "rectangleButton" ->
                        // set the event handler for drawing a rectangle
                    button.setOnAction(e -> setupShape(titles.shapesMenu.shapes.rectangleShapeItemTitle, primaryStage, new RectangleShape(0,0,0,0,gc) , editor, canvas, handlers));
                  case "pointButton" ->
                       // set the event handler for drawing a point
                          button.setOnAction(e -> setupShape(titles.shapesMenu.shapes.pointShapeItemTitle, primaryStage, new PointShape(0,0,gc) , editor, canvas, handlers));
                   case "lineButton" ->
                      // set the event handler for drawing a line
                           button.setOnAction(e -> setupShape(titles.shapesMenu.shapes.lineShapeItemTitle, primaryStage, new LineShape(0,0,0,0,gc), editor, canvas, handlers));
                    case "parallelepipedButton" ->
                        // set the event handler for drawing a line
                            button.setOnAction(e -> setupShape(titles.shapesMenu.shapes.parallelepipedShapeItemTitle, primaryStage, new ParallelepipedShape(0,0,0,0,gc), editor, canvas, handlers));
                    case "lineSegmentWithCirclesAtEndsButton" ->
                        // set the event handler for drawing a line
                            button.setOnAction(e -> setupShape(titles.shapesMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle, primaryStage, new LineSegmentWithCirclesAtEndsShape(0,0,0,0,gc), editor, canvas, handlers));
                   case "eraseButton" ->
                      // set the event handler for erasing
                         button.setOnAction(e -> {
                               primaryStage.setTitle(titles.toolbarMenu.actions.erase);
                               editor.clearCanvas();
                            });
                    default ->
                    // handle the default case
                    {}
                }
            }
        }



        ellipseShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.ellipseShapeItemTitle, primaryStage, new EllipseShape(0,0,0,0,gc) , editor, canvas, handlers);
            fireButtonById(toolbar, "ellipseButton");

        });

        rectangleShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.rectangleShapeItemTitle, primaryStage, new RectangleShape(0,0,0,0,gc) , editor, canvas, handlers);
            fireButtonById(toolbar, "rectangleButton");
        });

        lineShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.lineShapeItemTitle, primaryStage, new LineShape(0,0,0,0,gc), editor, canvas, handlers);
            fireButtonById(toolbar, "lineButton");

        });

        pointShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.pointShapeItemTitle, primaryStage, new PointShape(0,0,gc), editor, canvas, handlers);
            fireButtonById(toolbar, "pointButton");

        });


        lineSegmentWithCirclesAtEndsShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle, primaryStage, new LineSegmentWithCirclesAtEndsShape(0,0,0,0,gc), editor, canvas, handlers);
            fireButtonById(toolbar, "lineSegmentWithCirclesButton");

        });

        parallelepipedShapeItem.setOnAction(e -> {
            setupShape(titles.shapesMenu.shapes.parallelepipedShapeItemTitle, primaryStage, new ParallelepipedShape(0,0,0,0,gc), editor, canvas, handlers);
            fireButtonById(toolbar, "parallelepipedButton");

        });

        clearCanvasItem.setOnAction(e -> {
            primaryStage.setTitle(titles.toolbarMenu.actions.erase);
            editor.clearCanvas();
            fireButtonById(toolbar, "eraseButton");

        });


        openFileItem.setOnAction(e -> fileHandler.handleOpenTxtFile());
        

        double toolbarHeight = toolbar.getHeight();
        double menuHeight = menuBar.getHeight();

        BorderPane borderPane = new BorderPane();


        Pane pane = new Pane();
        pane.getChildren().add(canvas);
        VBox vBox = new VBox(menuBar, toolbar);
        borderPane.setTop(vBox);
        borderPane.setCenter(pane);
        primaryStage.setScene(new Scene(borderPane, 900, 800));
        primaryStage.show();




        primaryStage.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            canvas.setWidth((double) newSceneWidth);
        });

        primaryStage.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            canvas.setHeight((double) newSceneHeight - toolbarHeight - menuHeight);
        });


        // Set the initial Canvas size
        Platform.runLater(() -> {
            canvas.setWidth(primaryStage.getWidth());
            canvas.setHeight(primaryStage.getHeight() - toolbarHeight - menuHeight);
        });

        canvas.widthProperty().addListener((observableValue, oldCanvasWidth, newCanvasWidth) -> {
            editor.redrawShapes();
        });

        canvas.heightProperty().addListener((observableValue, oldCanvasHeight, newCanvasHeight) -> {
            editor.redrawShapes();
        });


        // Close other windows when closing main window
        primaryStage.setOnCloseRequest(e -> {
            tableWindow.close();
        });


    }

    public static void main(String[] args) {
        launch(args);
    }



    public void setupShape(String title, Stage primaryStage, Shape shape, Editor editor, Canvas canvas, List<EventHandler<MouseEvent>> handlers) {
        primaryStage.setTitle(title);
        // If there's an old shape editor, remove its event handler
        if (!handlers.isEmpty()) {
            for (EventHandler<MouseEvent> handler : handlers) {
                canvas.removeEventHandler(MouseEvent.ANY, handler);
            }
            handlers.clear();
        }

        // If there's a new shape editor, set it and add its event handler
        if (editor != null) {
            editor.setCurrentShape(shape);
            EventHandler<MouseEvent> newHandler = editor::draw;
            canvas.addEventHandler(MouseEvent.ANY, newHandler);
            handlers.add(newHandler);
        }
    }

    public void fireButtonById(ToolBar toolbar, String id) {
        for (Node node : toolbar.getItems()) {
            if (node instanceof Button button) {
                if (button.getId().equals(id)) {
                    button.requestFocus();
                    break;
                }
            }
        }
    }


    public List<TableWindow.ShapeData> getShapeData(ArrayList<Shape> shapes) {
        List<TableWindow.ShapeData> shapeDataList = new ArrayList<>();
        for (Shape shape : shapes) {
            String name = shape.getClass().getSimpleName();
            String x1 = Double.toString(shape.getStartX());
            String y1 = Double.toString(shape.getStartY());
            String x2 = Double.toString(shape.getEndX());
            String y2 = Double.toString(shape.getEndY());
            shapeDataList.add(new TableWindow.ShapeData(name, x1, y1, x2, y2));
        }
        return shapeDataList;
    }






}
