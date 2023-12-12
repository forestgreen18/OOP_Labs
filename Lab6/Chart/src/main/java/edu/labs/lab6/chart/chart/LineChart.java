package edu.labs.lab6.chart.chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;



public class LineChart extends Application {

  // TODO: Replace with your actual data
  private double[][] dataPoints = {{1, 23}, {2, 14}, {3, 15}};

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
}
