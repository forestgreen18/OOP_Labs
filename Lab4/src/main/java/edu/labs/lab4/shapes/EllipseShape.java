package edu.labs.lab4.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class EllipseShape extends Shape  {
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private GraphicsContext gc;
    public static final Color fillColor = Color.CYAN;
    public static final Color strokeColor = Color.BLACK;
    public static final Color previewStrokeColor = Color.RED;

    public EllipseShape(double startX, double startY, double endX, double endY, GraphicsContext gc) {
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
        draw(gc, strokeColor, fillColor, false );
    }

    @Override
    public void draw(GraphicsContext gc, Color strokeColor) {
        this.draw(gc, strokeColor ,fillColor , false);
    }

    public void draw(GraphicsContext gc, Color strokeColor, Color fillColor) {
        this.draw(gc, strokeColor, fillColor, false);
    }

    public void draw(GraphicsContext gc, Color strokeColor, Color fillColor, boolean dashed) {
        double centerX = (startX + endX) / 2;
        double centerY = (startY + endY) / 2;
        double width = Math.abs(startX - endX);
        double height = Math.abs(startY - endY);

        gc.setFill(fillColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(1);

        if (dashed) {
            gc.setLineDashes(10);
        }

        gc.strokeOval(centerX - width / 2, centerY - height / 2, width, height);
        gc.fillOval(centerX - width / 2, centerY - height / 2, width, height);

        if (dashed) {
            gc.setLineDashes(0);
        }
    }

    @Override
    public void drawPreviewShape(double startX, double startY, double endX, double endY) {
        this.draw(gc, previewStrokeColor, fillColor, true);
    }

    public void drawPreviewShape(double startX, double startY, double endX, double endY, Color fillColor) {
        this.draw(gc, previewStrokeColor, fillColor, true);
    }

    @Override
    public EllipseShape clone() {
        return new EllipseShape(this.startX, this.startY, this.endX, this.endY, this.gc);
    }


}

