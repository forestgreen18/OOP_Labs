module edu.labs.lab6.mainapp.mainapp {
  requires javafx.controls;
  requires javafx.fxml;

  opens edu.labs.lab6.mainapp to javafx.fxml;
  exports edu.labs.lab6.mainapp;
}