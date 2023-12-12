module edu.labs.lab6.chart.chart {
  requires javafx.controls;
  requires javafx.fxml;

  opens edu.labs.lab6.chart.chart to javafx.fxml;
  exports edu.labs.lab6.chart.chart;
}