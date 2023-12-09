package edu.labs.lab4.ui;

import edu.labs.lab4.utils.JsonFileReader;
import edu.labs.lab4.utils.SVGReader;
import edu.labs.lab4.utils.Titles;
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

        Button ellipseButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab4\\src\\main\\resources\\edu\\labs\\lab4\\images\\ellipse.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.ellipseShapeItemTitle, "ellipseButton");
        Button rectangleButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab4\\src\\main\\resources\\edu\\labs\\lab4\\images\\rectangle.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.rectangleShapeItemTitle, "rectangleButton");
        Button pointButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab4\\src\\main\\resources\\edu\\labs\\lab4\\images\\point.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.pointShapeItemTitle, "pointButton");
        Button lineButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab4\\src\\main\\resources\\edu\\labs\\lab4\\images\\line.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.lineShapeItemTitle,"lineButton");

        Button parallelepipedButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab4\\src\\main\\resources\\edu\\labs\\lab4\\images\\parallelepiped.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.parallelepipedShapeItemTitle, "parallelepipedButton");
        Button lineSegmentWithCirclesAtEndsButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab4\\src\\main\\resources\\edu\\labs\\lab4\\images\\lineSegmentWithCirclesAtEnds.svg", buttonHeight, titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle,"lineSegmentWithCirclesAtEndsButton");

        Button eraseButton = createButtonWithSVG("F:\\Labs\\OOP\\Lab4\\src\\main\\resources\\edu\\labs\\lab4\\images\\erase.svg", buttonHeight, titles.toolbarMenu.actions.erase, "eraseButton");

        ToolBar toolBar = new ToolBar(
                ellipseButton,
                rectangleButton,
                pointButton,
                lineButton,
                parallelepipedButton,
                lineSegmentWithCirclesAtEndsButton,
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