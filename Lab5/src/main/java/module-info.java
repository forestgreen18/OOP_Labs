module edu.labs.lab5 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;


    exports edu.labs.lab5.windows;
    opens edu.labs.lab5.windows to javafx.fxml;
    exports edu.labs.lab5.editor;
    opens edu.labs.lab5.editor to javafx.fxml;
}