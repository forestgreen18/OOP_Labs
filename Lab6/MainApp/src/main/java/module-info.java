module edu.labs.lab6.mainapp.mainapp {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.datatransfer;
  requires java.desktop;
  requires com.google.gson;

  opens edu.labs.lab6.mainapp to javafx.fxml;
  exports edu.labs.lab6.mainapp;
}