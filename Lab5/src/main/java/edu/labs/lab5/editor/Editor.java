package edu.labs.lab5.editor;


import edu.labs.lab5.shapes.Shape;
import edu.labs.lab5.windows.TableWindow;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Editor extends Application {
    private ArrayList<Shape> shapes = new ArrayList<>(108);

    private Shape currentShape;

    private int shapeCount = 0;

    private boolean isDrawing;

    private GraphicsContext gc;

    // Private static class that holds the Singleton
    private static class Holder {
        private static final Editor INSTANCE = new Editor();
    }

    // Private constructor to prevent instantiation
    private Editor() {}

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

    public List<TableWindow.ShapeData> getShapeData() {
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