module edu.labs.lab6.chart.chart {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.datatransfer;
  requires java.desktop;

  opens edu.labs.lab6.chart.chart to javafx.fxml;
  exports edu.labs.lab6.chart.chart;
}