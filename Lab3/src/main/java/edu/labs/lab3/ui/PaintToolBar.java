package edu.labs.lab3.ui;

import edu.labs.lab3.utils.JsonFileReader;
import edu.labs.lab3.utils.SVGReader;
import edu.labs.lab3.utils.Titles;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.SVGPath;

public class PaintToolBar {

    public ToolBar createToolBar() {
        JsonFileReader jsonFileReader = new JsonFileReader();
        Titles titles = jsonFileReader.readJsonFile();

        double buttonHeight = 50;  // Replace with your desired button height

        Button ellipseButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\ellipse.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.ellipseShapeItemTitle, "ellipseButton");
        Button rectangleButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\rectangle.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.rectangleShapeItemTitle, "rectangleButton");
        Button pointButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\point.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.pointShapeItemTitle, "pointButton");
        Button lineButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\line.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.lineShapeItemTitle,"lineButton");
        Button eraseButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\erase.svg", buttonHeight, titles.toolbarMenu.actions.erase, "eraseButton");

        ToolBar toolBar = new ToolBar(
                ellipseButton,
                rectangleButton,
                pointButton,
                lineButton,
                new Separator(),
                eraseButton
        );
        return toolBar;
    }


    private void setButtonHeight(Button button, double height) {
        button.setMinHeight(height);
        button.setMaxHeight(height);
        button.setPrefHeight(height);
    }

    private void setButtonTooltip(Button button, String tooltipText) {
        Tooltip tooltip = new Tooltip(tooltipText);
        button.setTooltip(tooltip);
    }

    private Button createButtonWithSVG(String svgFilePath, double height, String tooltipText, String id) {
        String svgContent = SVGReader.readSVGFile(svgFilePath);
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svgContent);

        Button button = new Button();
        button.setGraphic(svgPath);

        setButtonHeight(button, height);
        setButtonTooltip(button, tooltipText);
        // set the id of the button
        button.setId(id);

        return button;
    }



}