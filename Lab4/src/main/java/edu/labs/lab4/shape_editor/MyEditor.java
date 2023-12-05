package edu.labs.lab4.shape_editor;


import edu.labs.lab4.shape_editor.shapes.Shape;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MyEditor extends Application {
    private ArrayList<Shape> shapes = new ArrayList<>(108);

    private Shape currentShape;

    private int shapeCount = 0;

    private boolean isDrawing;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private GraphicsContext gc;


    public MyEditor(GraphicsContext gc) {
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
                System.out.println("is called");
                System.out.println("start x: " + startX);
                System.out.println("start y: " + startY);
                System.out.println("end x: " + endX);
                System.out.println("end y: " + endY);

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
                this.setDrawing(true);
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
                this.setDrawing(false);
                this.addShape(currentShape);
                break;
        }
    }

    public void saveShape() {
        // Implement this method to save the shape
    }



    public void drawPreviewShape(double startX, double startY, double endX, double endY) {
        // Implement this method to draw the shape
    }


    public boolean isDrawing() {
        return isDrawing;
    }

    public void setDrawing(boolean drawing) {
        this.isDrawing = drawing;
    }


    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }


    public void setStartX(double startX) {
        this.startX = startX;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(Shape currentShape) {
        this.currentShape = currentShape;
    }



}