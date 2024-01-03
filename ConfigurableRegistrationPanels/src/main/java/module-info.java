module edu.labs.configurableregistrationpanels.configurableregistrationpanels {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.json;
  requires java.datatransfer;
  requires java.desktop;

  opens edu.labs.configurableregistrationpanels to javafx.fxml;
  exports edu.labs.configurableregistrationpanels;
}