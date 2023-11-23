module edu.labs.lab3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;

    opens edu.labs.lab3 to javafx.fxml;
    exports edu.labs.lab3;
    exports edu.labs.lab3.shape_editor;
    opens edu.labs.lab3.shape_editor to javafx.fxml;
}