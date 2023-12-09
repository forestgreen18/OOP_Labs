package edu.labs.lab5.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineShape extends Shape {

    private GraphicsContext gc;

    public LineShape(double startX, double startY, double endX, double endY, GraphicsContext gc) {
        super(startX, startY, endX, endY);
        this.gc = gc;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY());
    }

    @Override
    public void draw(GraphicsContext gc, Color strokeColor) {
        gc.setStroke(strokeColor);
        gc.setLineWidth(1);
        gc.setLineDashes(10);
        gc.strokeLine(this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY());
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
        return new LineShape(this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY(), this.gc);
    }
}