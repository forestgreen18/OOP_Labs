package edu.labs.lab5.utils.fileReaders;

import edu.labs.lab5.shapes.RectangleShape;
import edu.labs.lab5.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShapeFileReader {

    private String filename;

    public ShapeFileReader(String filename) {
        this.filename = filename;
    }

    public ArrayList<Shape> readShapes(GraphicsContext gc) {
        ArrayList<Shape> shapes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                String shapeType = parts[0];
                double startX = Double.parseDouble(parts[1]);
                double startY = Double.parseDouble(parts[2]);
                double endX = Double.parseDouble(parts[3]);
                double endY = Double.parseDouble(parts[4]);
                Shape shape = createShape(shapeType, startX, startY, endX, endY, gc);
                shapes.add(shape);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return shapes;
    }

    private Shape createShape(String shapeType, double startX, double startY, double endX, double endY, GraphicsContext gc) {
        switch (shapeType) {
            case "RectangleShape":
                return new RectangleShape(startX, startY, endX, endY, gc);
            // Add cases for other shape types...
            default:
                throw new IllegalArgumentException("Invalid shape type: " + shapeType);
        }
    }
}