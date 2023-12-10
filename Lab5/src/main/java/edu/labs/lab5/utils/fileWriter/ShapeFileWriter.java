package edu.labs.lab5.utils.fileWriter;


import edu.labs.lab5.shapes.Shape;
import edu.labs.lab5.utils.dataFormatters.ShapeUtils;
import edu.labs.lab5.utils.fileReaders.JsonFileReader;
import edu.labs.lab5.utils.fileReaders.Titles;
import edu.labs.lab5.windows.TableWindow;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ShapeFileWriter {

  static JsonFileReader jsonFileReader = new JsonFileReader();
  private static final Titles titles = jsonFileReader.readJsonFile(); // Assuming Titles is a class that holds the titles.

  private final String filename;


  public ShapeFileWriter(String filename) {
    this.filename = filename;
    deleteFileIfExists();
  }

  private void deleteFileIfExists() {
    Path path = Paths.get(filename);
    if (Files.exists(path)) {
      try {
        Files.delete(path);
      } catch (IOException e) {
        System.out.println("An error occurred while deleting the file.");
        e.printStackTrace();
      }
    }
  }

  public void clearFileContent() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename, false))) {
      // PrintWriter initiated with FileWriter having append flag as false will overwrite the file,
      // effectively clearing it.
    } catch (IOException e) {
      System.out.println("An error occurred while clearing the file content.");
      e.printStackTrace();
    }
  }

  public void writeShape(Shape shape) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
      ShapeUtils shapeUtils = new ShapeUtils(titles); // Assuming titles is available here
      TableWindow.ShapeData data = ShapeUtils.getShapeDataWithoutTitle(shape);
      writer.println(data.getName() + "\t" +
          data.getX1() + "\t" +
          data.getY1() + "\t" +
          data.getX2() + "\t" +
          data.getY2());
    } catch (IOException e) {
      System.out.println("An error occurred while writing to the file.");
      e.printStackTrace();
    }
  }

}