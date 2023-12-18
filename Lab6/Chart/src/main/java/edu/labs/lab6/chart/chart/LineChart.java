package edu.labs.lab6.chart.chart;

import edu.labs.lab6.chart.chart.utils.ClipboardManager;
import edu.labs.lab6.chart.chart.utils.Server;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class LineChart extends Application {

  private Server server;
  private final XYChart.Series<Number, Number> series = new XYChart.Series<>();
  private javafx.scene.chart.LineChart<Number, Number> lineChart;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle("Лінійний графік");

    // Defining the axes
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("X значення");
    yAxis.setLabel("Y значення");

    // Creating the line chart
    javafx.scene.chart.LineChart<Number, Number> lineChart = new javafx.scene.chart.LineChart<>(
        xAxis, yAxis);

    // Preparing the series and adding data

    series.setName("Мій графік");

    addDataToSeries(lineChart);

    // Creating a scene object
    Scene scene = new Scene(lineChart, 800, 600);

    // Setting title to the stage
    stage.setScene(scene);

    // Displaying the contents of the stage
    stage.show();
  }

  public void addDataToSeries(javafx.scene.chart.LineChart<Number, Number> lineChart) {
    double[][] dataPoints = ClipboardManager.readAndParseFromClipboard();

    // Clear the existing data
    series.getData().clear();

    // Adding data from dataPoints to the series
    for (double[] dataPoint : dataPoints) {
      series.getData().add(new XYChart.Data<>(dataPoint[0], dataPoint[1]));
    }

    // Clear the chart's data and add the updated series
    lineChart.getData().clear();
    lineChart.getData().add(series);
  }


  @Override
  public void init() throws Exception {
    server = new Server(this);
    new Thread(() -> {
      try {
        server.startServer();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();

    try {
      addDataToSeries(getLineChart());
    } catch (Exception e) {
      e.printStackTrace();
//      showError(e);
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


  public javafx.scene.chart.LineChart<Number, Number> getLineChart() {
    return lineChart;
  }


}
