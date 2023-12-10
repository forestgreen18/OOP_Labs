package edu.labs.lab5.utils.dataFormatters;

import edu.labs.lab5.shapes.Shape;
import edu.labs.lab5.utils.fileReaders.JsonFileReader;
import edu.labs.lab5.utils.fileReaders.Titles;
import edu.labs.lab5.windows.TableWindow;

public class ShapeUtils {
    static JsonFileReader jsonFileReader = new JsonFileReader();
    private static Titles titles = jsonFileReader.readJsonFile(); // Assuming Titles is a class that holds the titles.


    public ShapeUtils(Titles titles) {
        this.titles = titles;
    }

    public static TableWindow.ShapeData getShapeData(Shape shape) {
        String name = getShapeTitle(shape.getClass().getSimpleName());
        String x1 = String.format("%.2f", shape.getStartX());
        String y1 = String.format("%.2f", shape.getStartY());
        String x2 = String.format("%.2f", shape.getEndX());
        String y2 = String.format("%.2f", shape.getEndY());
        return new TableWindow.ShapeData(name, x1, y1, x2, y2);
    }

    public static String getShapeTitle(String className) {
        switch (className) {
            case "EllipseShape":
                return titles.shapesMenu.shapes.ellipseShapeItemTitle;
            case "RectangleShape":
                return titles.shapesMenu.shapes.rectangleShapeItemTitle;
            case "LineShape":
                return titles.shapesMenu.shapes.lineShapeItemTitle;
            case "PointShape":
                return titles.shapesMenu.shapes.pointShapeItemTitle;
            case "LineSegmentWithCirclesAtEndsShape":
                return titles.shapesMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle;
            case "ParallelepipedShape":
                return titles.shapesMenu.shapes.parallelepipedShapeItemTitle;
            default:
                return "";
        }
    }
}
