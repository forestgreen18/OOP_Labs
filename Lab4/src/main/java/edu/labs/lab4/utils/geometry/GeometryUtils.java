package edu.labs.lab4.utils.geometry;

public class GeometryUtils {
    public static LineCoordinates calculateAdjustedLineCoordinates(double startX, double startY, double endX, double endY, double circleRadius) {
        double angle = Math.atan2(endY - startY, endX - startX); // Angle of the line
        double lineStartX = startX + circleRadius * Math.cos(angle); // Adjusted start X-coordinate of the line
        double lineStartY = startY + circleRadius * Math.sin(angle); // Adjusted start Y-coordinate of the line
        double lineEndX = endX - circleRadius * Math.cos(angle); // Adjusted end X-coordinate of the line
        double lineEndY = endY - circleRadius * Math.sin(angle); // Adjusted end Y-coordinate of the line

        return new LineCoordinates(lineStartX, lineStartY, lineEndX, lineEndY);
    }
}
