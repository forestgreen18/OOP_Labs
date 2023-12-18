module edu.labs.configurableregistrationpanels.configurableregistrationpanels {
  requires javafx.controls;
  requires javafx.fxml;

  opens edu.labs.configurableregistrationpanels to javafx.fxml;
  exports edu.labs.configurableregistrationpanels;
}