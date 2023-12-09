module edu.labs.lab5 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;

    opens edu.labs.lab5 to javafx.fxml;
    exports edu.labs.lab5;
}