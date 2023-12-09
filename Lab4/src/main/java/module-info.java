module edu.labs.lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;

    opens edu.labs.lab4 to javafx.fxml;
    exports edu.labs.lab4;
}