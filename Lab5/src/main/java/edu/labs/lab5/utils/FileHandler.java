package edu.labs.lab5.utils;

import edu.labs.lab5.editor.Editor;
import edu.labs.lab5.shapes.Shape;
import edu.labs.lab5.utils.fileReaders.ShapeFileReader;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileHandler {

  private final Stage primaryStage;
  private final GraphicsContext gc;
  private final Editor editor;

  public FileHandler(Stage primaryStage, GraphicsContext gc, Editor editor) {
    this.primaryStage = primaryStage;
    this.gc = gc;
    this.editor = editor;
  }

  public void handleOpenTxtFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    File selectedFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedFile != null) {
      ShapeFileReader reader = new ShapeFileReader(selectedFile.getPath());
      ArrayList<Shape> newShapes = reader.readShapes(gc);
      editor.setShapes(newShapes);
    }
  }
}
