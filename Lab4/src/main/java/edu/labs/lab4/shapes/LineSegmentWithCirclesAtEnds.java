package edu.labs.lab4.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineSegmentWithCirclesAtEnds extends Shape {
    private LineShape line;
    private EllipseShape startCircle;
    private EllipseShape endCircle;
    private GraphicsContext gc;

    public LineSegmentWithCirclesAtEnds(double startX, double startY, double endX, double endY, GraphicsContext gc) {
        super(startX, startY, endX, endY);
        line = new LineShape(startX, startY, endX, endY, gc);
        startCircle = new EllipseShape(startX - 10, startY - 10, startX + 10, startY + 10, gc); // 5 is the radius of the circle
        endCircle = new EllipseShape(endX - 10, endY - 10, endX + 10, endY + 10, gc); // 5 is the radius of the circle
        this.gc = gc;
    }

    @Override
    public void draw(GraphicsContext gc) {
        line.draw(gc);
        startCircle.draw(gc, Color.BLACK, Color.TRANSPARENT);
        endCircle.draw(gc, Color.BLACK, Color.TRANSPARENT);
    }

    @Override
    public void draw(GraphicsContext gc, Color strokeColor) {
        line.draw(gc, strokeColor);
        startCircle.draw(gc, strokeColor, Color.TRANSPARENT);
        endCircle.draw(gc, strokeColor, Color.TRANSPARENT);
    }

    @Override
    public void drawPreviewShape(double startX, double startY, double endX, double endY) {
        line.drawPreviewShape(startX, startY, endX, endY);
        startCircle.drawPreviewShape(startX - 10, startY - 10, startX + 10, startY + 10, Color.TRANSPARENT);
        endCircle.drawPreviewShape(endX - 10, endY - 10, endX + 10, endY + 10, Color.TRANSPARENT);
    }

    @Override
    public Shape clone() {
        return new LineSegmentWithCirclesAtEnds(getStartX(), getStartY(), getEndX(), getEndY(), gc);
    }
}
