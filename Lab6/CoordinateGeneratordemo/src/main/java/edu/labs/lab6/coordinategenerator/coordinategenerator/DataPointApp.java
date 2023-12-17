package edu.labs.lab6.coordinategenerator.coordinategenerator;

import edu.labs.lab6.coordinategenerator.coordinategenerator.sockets.Server;
import edu.labs.lab6.coordinategenerator.coordinategenerator.utils.AppLauncher;
import edu.labs.lab6.coordinategenerator.coordinategenerator.utils.RunningJavaApps;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DataPointApp extends Application {

  public static class Point {

    private final int number;
    private final double x;
    private final double y;

    public Point(int number, double x, double y) {
      this.number = number;
      this.x = x;
      this.y = y;
    }

    public int getNumber() {
      return number;
    }

    public double getX() {
      return x;
    }

    public double getY() {
      return y;
    }
  }
  private Server server;
  private TableView<Point> table = new TableView<>();


  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Генератор координат");

    table.setPrefHeight(200);
    table.setPrefWidth(300);

    TableColumn<Point, Integer> numberColumn = new TableColumn<>("Номер");
    numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn<Point, Double> xColumn = new TableColumn<>("X");
    xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
    TableColumn<Point, Double> yColumn = new TableColumn<>("Y");
    yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

    // Set the column widths to fill the table's width
    numberColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
    xColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.43));
    yColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.43));

    table.getColumns().addAll(numberColumn, xColumn, yColumn);

    Button generateButton = new Button("Згенерувати набір координат");
    generateButton.setPrefWidth(generateButton.getMaxWidth());
    generateButton.setPrefHeight(30);
    generateButton.setFont(new Font(18));

    generateButton.setOnAction(event -> {
      try {
        generateCoordinatesAndLaunchApp();
      } catch (Exception e) {
        showError(e);
      }
    });
    VBox vbox = new VBox(10, table, generateButton);
    vbox.setAlignment(Pos.CENTER);
    vbox.setPadding(new Insets(10));

    Scene scene = new Scene(vbox, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }


  private void generateCoordinatesAndLaunchApp() throws Exception {
    // Create a DataPointGenerator with default values
    DataPointGenerator generator = new DataPointGenerator(10, 0.0, 100.0, 0.0, 100.0);

    // Read the values from the clipboard and update the generator's values
    generator.readFromClipboard();

    double[][] points = generator.generatePoints();

    generator.writePointsToClipboard(points);

    ObservableList<Point> data = FXCollections.observableArrayList();
    for (int i = 0; i < points.length; i++) {
      data.add(new Point(i + 1, points[i][0], points[i][1]));
    }

    table.setItems(data);

    boolean isThereChartApp = RunningJavaApps.isAppRunning("Chart.jar");
    System.out.println(isThereChartApp);

    // Only launch the app if it's not already running
    if (!isThereChartApp) {
      // Run launchApp in a new thread
      new Thread(() -> {
        AppLauncher.launchApp("\"F:\\Labs\\OOP\\Lab6\\Chart\\out\\artifacts\\Chart_jar\\chart.bat\"");
      }).start();
    }
  }


  private void showError(Exception e) {
    Platform.runLater(() -> {
      // Create an Alert
      javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
      alert.setTitle("Помилка");
      alert.setHeaderText(null);
      alert.setContentText("Виникла помилка під час читання даних з буфера обміну. \n" + "Error: " + e.getMessage());

      // Show the Alert
      alert.showAndWait();
    });
  }



  @Override
  public void init() throws Exception {
    server = new Server();
    new Thread(() -> {
      try {
        server.startServer();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();

    try {
      generateCoordinatesAndLaunchApp();
    } catch (Exception e) {
      e.printStackTrace();
      showError(e);
    }
  }

  @Override
  public void stop() {
    try {
      System.out.println("Stopped");
      server.stopServer();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }




}
