package edu.labs.lab5.utils.fileReaders;

import edu.labs.lab5.shapes.EllipseShape;
import edu.labs.lab5.shapes.LineSegmentWithCirclesAtEndsShape;
import edu.labs.lab5.shapes.LineShape;
import edu.labs.lab5.shapes.ParallelepipedShape;
import edu.labs.lab5.shapes.PointShape;
import edu.labs.lab5.shapes.RectangleShape;
import edu.labs.lab5.shapes.Shape;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class ShapeFileReader {

  private final String filename;

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
        double startX = Double.parseDouble(parts[1].replace(",", "."));
        double startY = Double.parseDouble(parts[2].replace(",", "."));
        double endX = Double.parseDouble(parts[3].replace(",", "."));
        double endY = Double.parseDouble(parts[4].replace(",", "."));
        Shape shape = createShape(shapeType, startX, startY, endX, endY, gc);
        shapes.add(shape);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading the file.");
      e.printStackTrace();
    }
    return shapes;
  }


  private Shape createShape(String shapeType, double startX, double startY, double endX,
      double endY, GraphicsContext gc) {
    switch (shapeType) {
      case "RectangleShape":
        return new RectangleShape(startX, startY, endX, endY, gc);
      case "EllipseShape":
        return new EllipseShape(startX, startY, endX, endY, gc);
      case "LineShape":
        return new LineShape(startX, startY, endX, endY, gc);
      case "PointShape":
        return new PointShape(startX, startY, gc);
      case "LineSegmentWithCirclesAtEndsShape":
        return new LineSegmentWithCirclesAtEndsShape(startX, startY, endX, endY, gc);
      case "ParallelepipedShape":
        return new ParallelepipedShape(startX, startY, endX, endY, gc);
      default:
        throw new IllegalArgumentException("Invalid shape type: " + shapeType);
    }
  }
}