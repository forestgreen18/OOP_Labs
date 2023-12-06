package edu.labs.lab4.shapes;

import edu.labs.lab4.utils.geometry.GeometryUtils;
import edu.labs.lab4.utils.geometry.LineCoordinates;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineSegmentWithCirclesAtEnds extends Shape {
    private LineShape line;
    private EllipseShape startCircle;
    private EllipseShape endCircle;
    private GraphicsContext gc;
    public static final Color fillColor = Color.TRANSPARENT;
    public static final Color strokeColor = Color.BLACK;
    public static final Color previewStrokeColor = Color.RED;
    private double circleRadius = 10.0; // Radius of the circles

    public LineSegmentWithCirclesAtEnds(double startX, double startY, double endX, double endY, GraphicsContext gc) {
        super(startX, startY, endX, endY);
        LineCoordinates adjustedCoords = GeometryUtils.calculateAdjustedLineCoordinates(startX, startY, endX, endY, circleRadius);

        line = new LineShape(adjustedCoords.lineStartX, adjustedCoords.lineStartY, adjustedCoords.lineEndX, adjustedCoords.lineEndY, gc);
        startCircle = new EllipseShape(startX - 10, startY - 10, startX + 10, startY + 10, gc); // 5 is the radius of the circle
        endCircle = new EllipseShape(endX - 10, endY - 10, endX + 10, endY + 10, gc); // 5 is the radius of the circle
        this.gc = gc;
    }

        @Override
    public void draw(GraphicsContext gc) {
        line.draw(gc);
        startCircle.draw(gc, strokeColor, fillColor);
        endCircle.draw(gc, strokeColor, fillColor);
    }

    @Override
    public void draw(GraphicsContext gc, Color strokeColor) {
        line.draw(gc, strokeColor);
        startCircle.draw(gc, strokeColor, fillColor);
        endCircle.draw(gc, strokeColor, fillColor);
    }

    @Override
    public void drawPreviewShape(double startX, double startY, double endX, double endY) {
        LineCoordinates adjustedCoords = GeometryUtils.calculateAdjustedLineCoordinates(startX, startY, endX, endY, circleRadius);

        line.drawPreviewShape(adjustedCoords.lineStartX, adjustedCoords.lineStartY, adjustedCoords.lineEndX, adjustedCoords.lineEndY);
        startCircle.drawPreviewShape(startX - circleRadius, startY - circleRadius, startX + circleRadius, startY + circleRadius, fillColor);
        endCircle.drawPreviewShape(endX - circleRadius, endY - circleRadius, endX + circleRadius, endY + circleRadius, fillColor);
    }


    @Override
    public Shape clone() {
        return new LineSegmentWithCirclesAtEnds(getStartX(), getStartY(), getEndX(), getEndY(), gc);
    }
}
