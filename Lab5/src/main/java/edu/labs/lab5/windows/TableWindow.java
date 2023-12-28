package edu.labs.lab5.windows;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableWindow {

  private static TableWindow instance;

  private final Stage stage;
  private final TableView<ShapeData> table;

  private TableWindow() {
    stage = new Stage();
    stage.setTitle("Table Window");

    // Create a table
    table = new TableView<>();
    table.setEditable(true);

    // Create columns
    TableColumn<ShapeData, String> nameColumn = new TableColumn<>("Назва");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<ShapeData, String> x1Column = new TableColumn<>("X1");
    x1Column.setCellValueFactory(new PropertyValueFactory<>("x1"));

    TableColumn<ShapeData, String> y1Column = new TableColumn<>("Y1");
    y1Column.setCellValueFactory(new PropertyValueFactory<>("y1"));

    TableColumn<ShapeData, String> x2Column = new TableColumn<>("X2");
    x2Column.setCellValueFactory(new PropertyValueFactory<>("x2"));

    TableColumn<ShapeData, String> y2Column = new TableColumn<>("Y2");
    y2Column.setCellValueFactory(new PropertyValueFactory<>("y2"));

    // Add columns to the table
    table.getColumns().addAll(nameColumn, x1Column, y1Column, x2Column, y2Column);

    // Create a VBox and add the table to it
    VBox vbox = new VBox(table);

    // Create a scene and add the VBox to it
    Scene scene = new Scene(vbox);

    // Set the scene to the stage
    stage.setScene(scene);
  }

  public static TableWindow getInstance() {
    if (instance == null) {
      instance = new TableWindow();
    }
    return instance;
  }

  public boolean isShowing() {
    return stage.isShowing();
  }

  public void hide() {
    stage.hide();
  }

  public void close() {
    stage.close();
  }

  public void show() {
    stage.show();
  }

  public void addRow(String name, String x1, String y1, String x2, String y2) {
    ShapeData data = new ShapeData(name, x1, y1, x2, y2);
    table.getItems().add(data);
  }

  public void printTable() {
    for (ShapeData data : table.getItems()) {
      System.out.println(
          "Name: " + data.name.get() + ", X1: " + data.x1.get() + ", Y1: " + data.y1.get()
              + ", X2: " + data.x2.get() + ", Y2: " + data.y2.get());
    }
  }

  public void clearTable() {
    table.getItems().clear();
  }

  // Data model class
  public static class ShapeData {

    private final SimpleStringProperty name;
    private final SimpleStringProperty x1;
    private final SimpleStringProperty y1;
    private final SimpleStringProperty x2;
    private final SimpleStringProperty y2;

    public ShapeData(String name, String x1, String y1, String x2, String y2) {
      this.name = new SimpleStringProperty(name);
      this.x1 = new SimpleStringProperty(x1);
      this.y1 = new SimpleStringProperty(y1);
      this.x2 = new SimpleStringProperty(x2);
      this.y2 = new SimpleStringProperty(y2);
    }

    public String getName() {
      return name.get();
    }

    public String getX1() {
      return x1.get();
    }

    public String getY1() {
      return y1.get();
    }

    public String getX2() {
      return x2.get();
    }

    public String getY2() {
      return y2.get();
    }
  }

}
