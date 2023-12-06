package edu.labs.lab4.shapes;

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

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(startX, startY, endX, endY);
    }

    public void draw(GraphicsContext gc, Color strokeColor) {
        gc.setStroke(strokeColor);
        gc.setLineWidth(1);
        gc.setLineDashes(10);
        gc.strokeLine(startX, startY, endX, endY);
        gc.setLineDashes(0);
    }

    @Override
    public void drawPreviewShape(double startX, double startY, double endX, double endY) {
        this.setStartX(startX);
        this.setStartY(startY);
        this.setEndX(endX);
        this.setEndY(endY);
        this.draw(gc, Color.RED);
    }

    @Override
    public LineShape clone() {
        return new LineShape(this.startX, this.startY, this.endX, this.endY, this.gc);
    }
}