package edu.labs.lab5.windows;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableWindow {
    private Stage stage;
    private TableView<ShapeData> table;

    // Data model class
    public static class ShapeData {
        private final SimpleStringProperty name;
        private final SimpleStringProperty x;
        private final SimpleStringProperty y;
        private final SimpleStringProperty z;
        private final SimpleStringProperty w;

        public ShapeData(String name, String x, String y, String z, String w) {
            this.name = new SimpleStringProperty(name);
            this.x = new SimpleStringProperty(x);
            this.y = new SimpleStringProperty(y);
            this.z = new SimpleStringProperty(z);
            this.w = new SimpleStringProperty(w);
        }

        // getters and setters for each property...
    }

    public TableWindow() {
        stage = new Stage();
        stage.setTitle("Table Window");

        // Create a table
        table = new TableView<>();
        table.setEditable(true);

        // Create columns
        TableColumn<ShapeData, String> nameColumn = new TableColumn<>("Name");
        TableColumn<ShapeData, String> xColumn = new TableColumn<>("X");
        TableColumn<ShapeData, String> yColumn = new TableColumn<>("Y");
        TableColumn<ShapeData, String> zColumn = new TableColumn<>("Z");
        TableColumn<ShapeData, String> wColumn = new TableColumn<>("W");

        // Add columns to the table
        table.getColumns().addAll(nameColumn, xColumn, yColumn, zColumn, wColumn);

        // Create a VBox and add the table to it
        VBox vbox = new VBox(table);

        // Create a scene and add the VBox to it
        Scene scene = new Scene(vbox);

        // Set the scene to the stage
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    public void addRow(String name, String x, String y, String z, String w) {
        ShapeData data = new ShapeData(name, x, y, z, w);
        table.getItems().add(data);
    }
}
