package edu.labs.lab2.shape_editor.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EllipseShape extends Shape {
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private GraphicsContext gc;

    public EllipseShape(double startX, double startY, double endX, double endY, GraphicsContext gc) {
        super(startX, startY, endX, endY);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.gc = gc;
    }

    @Override
    public void draw(GraphicsContext gc) {
        double left = Math.min(startX, endX);
        double top = Math.min(startY, endY);
        double width = Math.abs(startX - endX);
        double height = Math.abs(startY - endY);

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(left, top, width, height);
        gc.fillOval(left, top, width, height);
    }
}
