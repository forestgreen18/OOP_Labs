package edu.labs.lab5.editor;


import edu.labs.lab5.shapes.Shape;
import edu.labs.lab5.utils.fileReaders.JsonFileReader;
import edu.labs.lab5.utils.fileReaders.Titles;
import edu.labs.lab5.utils.fileWriter.ShapeFileWriter;
import edu.labs.lab5.windows.TableWindow;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Editor extends Application {
    private TableWindow tableWindow = TableWindow.getInstance();

    private ArrayList<Shape> shapes = new ArrayList<>(108);

    private Shape currentShape;

    private int shapeCount = 0;

    private boolean isDrawing;

    private GraphicsContext gc;
    JsonFileReader jsonFileReader = new JsonFileReader();
    Titles titles = jsonFileReader.readJsonFile();


    // Private static class that holds the Singleton
    private static class Holder {
        private static final Editor INSTANCE = new Editor();
    }

    // Private constructor to prevent instantiation
    private Editor() {


    }

    // Method to get the singleton instance
    public static Editor getInstance() {
        return Holder.INSTANCE;
    }

    // Method to set the GraphicsContext
    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void start(Stage stage) throws IOException {

    }

    public static void main(String[] args) {
        launch();
    }

    public void addShape(Shape shape) {
        if (shapes.size() < 108) {
            shapes.add(shape);
            redrawShapes();

            updateTable(shape);
            ShapeFileWriter writer = new ShapeFileWriter("shapes.txt");
            writer.writeShape(shape);
        }
    }

    public void redrawShapes() {
        if (gc != null) {
            // Clear the canvas
            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

            // Redraw all shapes
            for (Shape shape : shapes) {
                if (shape != null) {
                    shape.draw(gc);
                }
            }

            // Draw the new shape if isDrawing is true
            if (isDrawing) {
                currentShape.drawPreviewShape(currentShape.getStartX(), currentShape.getStartY(), currentShape.getEndX(), currentShape.getEndY());
            }
        } else {
            System.out.println("gc is null. The start() method might not have been called yet.");
        }
    }

    public void clearCanvas() {
        // Clear the canvas
        if (gc != null) {
            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        }

        // Reset the shapes array
        shapes.clear();
        shapeCount = 0;

        // Clear the table
        tableWindow.clearTable();
    }


    public void draw(MouseEvent event) {

        double x = event.getX();
        double y = event.getY();
        switch (event.getEventType().getName()) {
            case "MOUSE_PRESSED":
                currentShape = currentShape.clone();
                isDrawing = true;
                currentShape.setStartX(x);
                currentShape.setStartY(y);
                currentShape.setEndX(x);
                currentShape.setEndY(y);
                break;
            case "MOUSE_DRAGGED":

                currentShape.setEndX(x);
                currentShape.setEndY(y);
                this.redrawShapes();
                break;
            case "MOUSE_RELEASED":
                isDrawing = false;
                this.addShape(currentShape);
                break;
        }
    }

    public void setCurrentShape(Shape currentShape) {
        this.currentShape = currentShape;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public TableWindow.ShapeData getShapeData(Shape shape) {
        String name = getShapeTitle(shape.getClass().getSimpleName());
        String x1 = String.format("%.2f", shape.getStartX());
        String y1 = String.format("%.2f", shape.getStartY());
        String x2 = String.format("%.2f", shape.getEndX());
        String y2 = String.format("%.2f", shape.getEndY());
        return new TableWindow.ShapeData(name, x1, y1, x2, y2);
    }



    public void updateTable(Shape shape) {
        TableWindow.ShapeData data = getShapeData(shape);
        tableWindow.addRow(data.getName(), data.getX1(), data.getY1(), data.getX2(), data.getY2());
    }


    public String getShapeTitle(String className) {
        switch (className) {
            case "EllipseShape":
                return titles.shapesMenu.shapes.ellipseShapeItemTitle;
            case "RectangleShape":
                return titles.shapesMenu.shapes.rectangleShapeItemTitle;
            case "LineShape":
                return titles.shapesMenu.shapes.lineShapeItemTitle;
            case "PointShape":
                return titles.shapesMenu.shapes.pointShapeItemTitle;
            case "LineSegmentWithCirclesAtEndsShape":
                return titles.shapesMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle;
            case "ParallelepipedShape":
                return titles.shapesMenu.shapes.parallelepipedShapeItemTitle;
            default:
                return "";
        }
    }



}