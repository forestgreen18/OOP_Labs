package edu.labs.lab4.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape  extends Shape {
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private GraphicsContext gc;
    public static final Color fillColor = Color.TRANSPARENT;
    public static final Color strokeColor = Color.BLACK;
    public static final Color previewStrokeColor = Color.RED;

    public RectangleShape(double startX, double startY, double endX, double endY, GraphicsContext gc) {
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
        draw(gc, strokeColor, fillColor, false);
    }

    @Override
    public void draw(GraphicsContext gc, Color strokeColor) {
        draw(gc, strokeColor, fillColor, false);
    }

    public void draw(GraphicsContext gc, Color strokeColor, Color fillColor) {
        draw(gc, strokeColor, fillColor, false);
    }

    public void draw(GraphicsContext gc, Color strokeColor, Color fillColor, boolean dashed) {
        double centerX = startX;
        double centerY = startY;
        double cornerX = endX;
        double cornerY = endY;

        double width = Math.abs(centerX - cornerX) * 2;
        double height = Math.abs(centerY - cornerY) * 2;
        double left = centerX - width / 2;
        double top = centerY - height / 2;

        gc.setFill(fillColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(1);

        if (dashed) {
            gc.setLineDashes(10);
        }

        gc.strokeRect(left, top, width, height);
        gc.fillRect(left, top, width, height);

        if (dashed) {
            gc.setLineDashes(0);
        }
    }

    @Override
    public void drawPreviewShape(double startX, double startY, double endX, double endY) {
        this.setStartX(startX);
        this.setStartY(startY);
        this.setEndX(endX);
        this.setEndY(endY);
        this.draw(gc, previewStrokeColor, fillColor, true);
    }

    public void drawPreviewShape(double startX, double startY, double endX, double endY, Color fillColor) {
        this.setStartX(startX);
        this.setStartY(startY);
        this.setEndX(endX);
        this.setEndY(endY);
        this.draw(gc, previewStrokeColor, fillColor, true);
    }
    @Override
    public RectangleShape clone() {
        return new RectangleShape(this.startX, this.startY, this.endX, this.endY, this.gc);
    }
}
