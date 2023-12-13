package edu.labs.lab6.chart.chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;



public class LineChart extends Application {

  @Override
  public void start(Stage stage) {
    stage.setTitle("Лінійний графік");

    // Defining the axes
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("X значення");
    yAxis.setLabel("Y значення");

    // Creating the line chart
    javafx.scene.chart.LineChart<Number, Number> lineChart = new javafx.scene.chart.LineChart<>(xAxis, yAxis);

    // Preparing the series and adding data
    XYChart.Series<Number, Number> series = new XYChart.Series<>();
    series.setName("Мій графік");

    double[][] dataPoints = readAndParseFromClipboard();

    // Adding data from dataPoints to the series
    for (double[] dataPoint : dataPoints) {
      series.getData().add(new XYChart.Data<>(dataPoint[0], dataPoint[1]));
    }

    lineChart.getData().add(series);

    // Creating a scene object
    Scene scene = new Scene(lineChart, 800, 600);

    // Setting title to the stage
    stage.setScene(scene);

    // Displaying the contents of the stage
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }


  public double[][] readAndParseFromClipboard() {
    java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
    try {
      String data = (String) clipboard.getData(java.awt.datatransfer.DataFlavor.stringFlavor);
      String[] lines = data.split("\\n");
      double[][] points = new double[lines.length][2];
      for (int i = 0; i < lines.length; i++) {
        String[] parts = lines[i].split(", ");
        if (parts.length == 3) {
          points[i][0] = Double.parseDouble(parts[1].split(": ")[1]);
          points[i][1] = Double.parseDouble(parts[2].split(": ")[1].replace(";", ""));
        }
      }
      return points;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new double[0][0];
  }

}
