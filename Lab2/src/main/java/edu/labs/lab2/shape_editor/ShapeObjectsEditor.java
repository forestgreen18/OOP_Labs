package edu.labs.lab2.shape_editor;

import edu.labs.lab2.shape_editor.shapes.Shape;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class ShapeObjectsEditor extends Application {
    Shape[] shapes = new Shape[108];
    private int shapeCount = 0;

    private boolean isDrawing;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private GraphicsContext gc;

    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(400, 200);
        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root, 400, 200));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
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


    public void addShape(Shape shape) {
        if (shapeCount < shapes.length) {
            shapes[shapeCount] = shape;
            shapeCount++;
        }
    }

    public void redrawShapes() {
        for (Shape shape : shapes) {
            if (shape != null) {
                shape.draw(gc);
            }
        }
    }

    public boolean isDrawing() {
        return isDrawing;
    }
}