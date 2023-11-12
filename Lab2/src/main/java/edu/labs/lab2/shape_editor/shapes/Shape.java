package edu.labs.lab2.shape_editor.shapes;

public abstract class Shape {

    private double startX;
    private double startY;
    private double endX;
    private double endY;

    // Constructor
    public Shape(String color, double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    // Getters and setters
    public double getStartX() {
        return startX;
    }

    public void setStartX(double x) {
        this.startX = x;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double y) {
        this.startY = y;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double x) {
        this.endX = x;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double y) {
        this.endY = y;
    }
}
