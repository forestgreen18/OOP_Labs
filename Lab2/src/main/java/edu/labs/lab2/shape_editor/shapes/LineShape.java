package edu.labs.lab2.shape_editor.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineShape extends Shape {
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private GraphicsContext gc;

    public LineShape(double startX, double startY, double endX, double endY, GraphicsContext gc) {
        super(startX, startY, endX, endY);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.gc = gc;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(startX, startY, endX, endY);
    }
}