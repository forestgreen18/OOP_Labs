package edu.labs.lab4.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ParallelepipedShape extends Shape {
    private LineShape[] lines;
    private RectangleShape[] rectangles;
    private GraphicsContext gc;

    public ParallelepipedShape(double startX, double startY, double endX, double endY, GraphicsContext gc) {
        super(startX, startY, endX, endY);
        double offset = Math.min(endX - startX, endY - startY) / 2; // Adjust this value as needed

        // Initialize your rectangles (squares) here
        rectangles = new RectangleShape[2];
        rectangles[0] = new RectangleShape(startX - offset, startY - offset, startX + offset, startY + offset, gc);
        rectangles[1] = new RectangleShape(endX - offset, endY - offset, endX + offset, endY + offset, gc);

        // Initialize your lines here
        lines = new LineShape[4];
        lines[0] = new LineShape(rectangles[0].getLeft(), rectangles[0].getTop(), rectangles[1].getLeft(), rectangles[1].getTop(), gc);
        lines[1] = new LineShape(rectangles[0].getRight(), rectangles[0].getTop(), rectangles[1].getRight(), rectangles[1].getTop(), gc);
        lines[2] = new LineShape(rectangles[0].getLeft(), rectangles[0].getBottom(), rectangles[1].getLeft(), rectangles[1].getBottom(), gc);
        lines[3] = new LineShape(rectangles[0].getRight(), rectangles[0].getBottom(), rectangles[1].getRight(), rectangles[1].getBottom(), gc);
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
            rectangle.draw(gc, strokeColor, Color.TRANSPARENT, true);
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

        // Update the positions of your lines here
        lines[0].setStartX(rectangles[0].getLeft());
        lines[0].setStartY(rectangles[0].getTop());
        lines[0].setEndX(rectangles[1].getLeft());
        lines[0].setEndY(rectangles[1].getTop());

        lines[1].setStartX(rectangles[0].getRight());
        lines[1].setStartY(rectangles[0].getTop());
        lines[1].setEndX(rectangles[1].getRight());
        lines[1].setEndY(rectangles[1].getTop());

        lines[2].setStartX(rectangles[0].getLeft());
        lines[2].setStartY(rectangles[0].getBottom());
        lines[2].setEndX(rectangles[1].getLeft());
        lines[2].setEndY(rectangles[1].getBottom());

        lines[3].setStartX(rectangles[0].getRight());
        lines[3].setStartY(rectangles[0].getBottom());
        lines[3].setEndX(rectangles[1].getRight());
        lines[3].setEndY(rectangles[1].getBottom());

        draw(gc, Color.RED);
    }






    @Override
    public Shape clone() {
        return new ParallelepipedShape(getStartX(), getStartY(), getEndX(), getEndY(), gc);
    }
}
