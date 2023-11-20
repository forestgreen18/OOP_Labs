package edu.labs.lab3.shape_editor;

import edu.labs.lab3.shape_editor.editor.ShapeEditor;
import edu.labs.lab3.shape_editor.shapes.Shape;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class ShapeObjectsEditor extends Application {
    private Shape[] shapes = new Shape[108];
    private ShapeEditor currentShapeEditor;
    private int shapeCount = 0;

    private boolean isDrawing;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private GraphicsContext gc;


    public ShapeObjectsEditor(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void start(Stage stage) throws IOException {

    }

    public static void main(String[] args) {
        launch();
    }

    public void addShape(Shape shape) {
        if (shapeCount < shapes.length) {
            shapes[shapeCount] = shape;
            System.out.println(Arrays.toString(shapes));
            shapeCount++;
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
                currentShapeEditor.drawSolidShape(startX, startY, endX, endY);
            }
        } else {
            System.out.println("gc is null. The start() method might not have been called yet.");
        }
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

    public ShapeEditor getCurrentShapeEditor() {
        return currentShapeEditor;
    }

    public void setCurrentShapeEditor(ShapeEditor currentShapeEditor) {
        this.currentShapeEditor = currentShapeEditor;
    }

}