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


        String svgEllipseFilePath = "F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\ellipse.svg";
        String svgEllipseContent = SVGReader.readSVGFile(svgEllipseFilePath);
        SVGPath svgEllipsePath = new SVGPath();
        svgEllipsePath.setContent(svgEllipseContent);

        Button ellipseButton = new Button();
        ellipseButton.setGraphic(svgEllipsePath);

        String svgRectangleFilePath = "F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\rectangle.svg";
        String svgRectangleContent = SVGReader.readSVGFile(svgRectangleFilePath);
        SVGPath svgRectanglePath = new SVGPath();
        svgRectanglePath.setContent(svgRectangleContent);

        Button rectangleButton = new Button();
        rectangleButton.setGraphic(svgRectanglePath);

        String svgPointFilePath = "F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\point.svg";
        String svgPointContent = SVGReader.readSVGFile(svgPointFilePath);
        SVGPath svgPointPath = new SVGPath();
        svgPointPath.setContent(svgPointContent);

        Button pointButton = new Button();
        pointButton.setGraphic(svgPointPath);

        String svgLineFilePath = "F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\line.svg";
        String svgLineContent = SVGReader.readSVGFile(svgLineFilePath);
        SVGPath svgLinePath = new SVGPath();
        svgLinePath.setContent(svgLineContent);

        Button lineButton = new Button();
        lineButton.setGraphic(svgLinePath);

        String svgEraseFilePath = "F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\images\\erase.svg";
        String svgEraseContent = SVGReader.readSVGFile(svgEraseFilePath);
        SVGPath svgErasePath = new SVGPath();
        svgErasePath.setContent(svgEraseContent);

        Button eraseButton = new Button();
        eraseButton.setGraphic(svgErasePath);


        ellipseButton.setMinHeight(buttonHeight);
        ellipseButton.setMaxHeight(buttonHeight);
        ellipseButton.setPrefHeight(buttonHeight);

        rectangleButton.setMinHeight(buttonHeight);
        rectangleButton.setMaxHeight(buttonHeight);
        rectangleButton.setPrefHeight(buttonHeight);

        pointButton.setMinHeight(buttonHeight);
        pointButton.setMaxHeight(buttonHeight);
        pointButton.setPrefHeight(buttonHeight);

        lineButton.setMinHeight(buttonHeight);
        lineButton.setMaxHeight(buttonHeight);
        lineButton.setPrefHeight(buttonHeight);

        eraseButton.setMinHeight(buttonHeight);
        eraseButton.setMaxHeight(buttonHeight);
        eraseButton.setPrefHeight(buttonHeight);


        Tooltip ellipseTooltip = new Tooltip(titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.ellipseShapeItemTitle);
        ellipseButton.setTooltip(ellipseTooltip);

        Tooltip rectangleTooltip = new Tooltip(titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.rectangleShapeItemTitle);
        rectangleButton.setTooltip(rectangleTooltip);

        Tooltip pointTooltip = new Tooltip(titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.pointShapeItemTitle);
        pointButton.setTooltip(pointTooltip);

        Tooltip lineTooltip = new Tooltip(titles.toolbarMenu.actions.draw + titles.toolbarMenu.shapes.lineShapeItemTitle);
        lineButton.setTooltip(lineTooltip);

        Tooltip eraseTooltip = new Tooltip(titles.toolbarMenu.actions.erase);
        eraseButton.setTooltip(eraseTooltip);


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
}