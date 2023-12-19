package edu.labs.lab5.editor;


import static edu.labs.lab5.utils.dataFormatters.ShapeUtils.getShapeData;

import edu.labs.lab5.shapes.Shape;
import edu.labs.lab5.utils.fileWriter.ShapeFileWriter;
import edu.labs.lab5.windows.TableWindow;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Editor extends Application {

  private final TableWindow tableWindow = TableWindow.getInstance();

  private final ArrayList<Shape> shapes = new ArrayList<>(108);
  ShapeFileWriter writer = new ShapeFileWriter("shapes.txt");
  private Shape currentShape;
  private int shapeCount = 0;
  private boolean isDrawing;
  private GraphicsContext gc;

  // Private constructor to prevent instantiation
  private Editor() {

  }

  // Method to get the singleton instance
  public static Editor getInstance() {
    return Holder.INSTANCE;
  }

  public static void main(String[] args) {
    launch();
  }

  // Method to set the GraphicsContext
  public void setGraphicsContext(GraphicsContext gc) {
    this.gc = gc;
  }

  @Override
  public void start(Stage stage) throws IOException {

  }

  public void addShape(Shape shape) {
    if (shapes.size() < 108) {
      shapes.add(shape);
      redrawShapes();

      updateTable(shape);
      writer.writeShape(shape);
    }
  }

  public void redrawShapes() {
    if (gc != null) {
      // Clear the canvas
      gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

      // Redraw all shapes
      for (Shape shape : shapes) {
        if (shape != null) {
          shape.draw(gc);
        }
      }

      // Draw the new shape if isDrawing is true
      if (isDrawing) {
        currentShape.drawPreviewShape(currentShape.getStartX(), currentShape.getStartY(),
            currentShape.getEndX(), currentShape.getEndY());
      }
    } else {
      System.out.println("gc is null. The start() method might not have been called yet.");
    }
  }

  public void clearCanvas() {
    // Clear the canvas
    if (gc != null) {
      gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    // Reset the shapes array
    shapes.clear();
    shapeCount = 0;

    // Clear the table
    tableWindow.clearTable();
    writer.clearFileContent();
  }

  public void draw(MouseEvent event) {

    double x = event.getX();
    double y = event.getY();
    switch (event.getEventType().getName()) {
      case "MOUSE_PRESSED":
        currentShape = currentShape.clone();
        isDrawing = true;
        currentShape.setStartX(x);
        currentShape.setStartY(y);
        currentShape.setEndX(x);
        currentShape.setEndY(y);
        break;
      case "MOUSE_DRAGGED":

        currentShape.setEndX(x);
        currentShape.setEndY(y);
        this.redrawShapes();
        break;
      case "MOUSE_RELEASED":
        isDrawing = false;
        this.addShape(currentShape);
        break;
    }
  }

  public void setCurrentShape(Shape currentShape) {
    this.currentShape = currentShape;
  }

  public ArrayList<Shape> getShapes() {
    return shapes;
  }

  public void setShapes(ArrayList<Shape> newShapes) {
    for (Shape shape : newShapes) {
      addShape(shape);
    }
  }

  public void updateTable(Shape shape) {
    TableWindow.ShapeData data = getShapeData(shape);
    tableWindow.addRow(data.getName(), data.getX1(), data.getY1(), data.getX2(), data.getY2());
  }

  // Private static class that holds the Singleton
  private static class Holder {

    private static final Editor INSTANCE = new Editor();
  }


}