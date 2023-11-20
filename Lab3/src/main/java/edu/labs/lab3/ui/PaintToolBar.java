package edu.labs.lab3.ui;

import edu.labs.lab3.utils.SVGReader;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.shape.SVGPath;

public class PaintToolBar {

    public ToolBar createToolBar() {

        String svgFilePath = "F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\ellipse.svg";
        String svgContent = SVGReader.readSVGFile(svgFilePath);

        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svgContent);

        Button button = new Button();
        button.setGraphic(svgPath);

        ToolBar toolBar = new ToolBar(
                button,
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