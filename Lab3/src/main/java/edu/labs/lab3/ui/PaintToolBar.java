package edu.labs.lab3.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;

public class PaintToolBar {

    public ToolBar createToolBar() {
        ToolBar toolBar = new ToolBar(
                new Button("New"),
                new Button("Open"),
                new Button("Save"),
                new Separator(),
                new Button("Clean"),
                new Button("Compile"),
                new Button("Run"),
                new Separator(),
                new Button("Debug"),
                new Button("Profile")
        );
        return toolBar;
    }
}