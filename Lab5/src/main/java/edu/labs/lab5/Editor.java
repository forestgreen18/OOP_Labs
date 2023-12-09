package edu.labs.lab5;


import edu.labs.lab5.shapes.Shape;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Editor extends Application {
    private ArrayList<Shape> shapes = new ArrayList<>(108);

    private Shape currentShape;

    private int shapeCount = 0;

    private boolean isDrawing;

    private GraphicsContext gc;


    public Editor(GraphicsContext gc) {
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
}