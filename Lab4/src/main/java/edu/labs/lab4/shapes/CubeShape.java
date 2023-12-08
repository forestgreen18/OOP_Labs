package edu.labs.lab4.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CubeShape extends Shape {
    private LineShape[] lines;
    private RectangleShape[] rectangles;
    private GraphicsContext gc;

    public CubeShape(double startX, double startY, double endX, double endY, GraphicsContext gc) {
        super(startX, startY, endX, endY);
        double offset = Math.min(endX - startX, endY - startY) / 2; // Adjust this value as needed

        // Initialize your rectangles (squares) here
        rectangles = new RectangleShape[2];
        rectangles[0] = new RectangleShape(startX, startY, endX, endY, gc);
        rectangles[1] = new RectangleShape(startX + offset, startY + offset, endX + offset, endY + offset, gc);

        // Initialize your lines here
        lines = new LineShape[4];
        lines[0] = new LineShape(startX, startY, startX + offset, startY + offset, gc);
        lines[1] = new LineShape(endX, startY, endX + offset, startY + offset, gc);
        lines[2] = new LineShape(startX, endY, startX + offset, endY + offset, gc);
        lines[3] = new LineShape(endX, endY, endX + offset, endY + offset, gc);
        this.gc = gc;
    }

    @Override
    public void draw(GraphicsContext gc) {
        for (LineShape line : lines) {
            line.draw(gc);
        }
        for (RectangleShape rectangle : rectangles) {
            rectangle.draw(gc);
        }
    }

    @Override
    public void draw(GraphicsContext gc, Color strokeColor) {
        for (LineShape line : lines) {
            line.draw(gc, strokeColor);
        }
        for (RectangleShape rectangle : rectangles) {
            rectangle.draw(gc, strokeColor);
        }
    }

    @Override
    public void drawPreviewShape(double startX, double startY, double endX, double endY) {
        double offset = Math.min(endX - startX, endY - startY) / 2; // Adjust this value as needed

        // Update the positions of your rectangles (squares) here
        rectangles[0].setStartX(startX);
        rectangles[0].setStartY(startY);
        rectangles[0].setEndX(endX);
        rectangles[0].setEndY(endY);

        rectangles[1].setStartX(startX + offset);
        rectangles[1].setStartY(startY + offset);
        rectangles[1].setEndX(endX + offset);
        rectangles[1].setEndY(endY + offset);



        draw(gc, Color.RED);
    }





    @Override
    public Shape clone() {
        return new CubeShape(getStartX(), getStartY(), getEndX(), getEndY(), gc);
    }
}
