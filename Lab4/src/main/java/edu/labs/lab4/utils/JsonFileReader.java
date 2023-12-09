package edu.labs.lab4.utils;


import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;

public class JsonFileReader {
    public Titles readJsonFile() {
        try {
            InputStream is = new FileInputStream("F:\\Labs\\OOP\\Lab4\\src\\main\\resources\\edu\\labs\\lab4\\menuTitles.json");
            JSONTokener tokener = new JSONTokener(is);
            JSONObject root = new JSONObject(tokener);

            Titles titles = new Titles();
            titles.fileMenu = new Titles.FileMenu();
            titles.shapesMenu = new Titles.ShapesMenu();
            titles.shapesMenu.shapes = new Titles.ShapesMenu.ShapeTitles();
            titles.toolbarMenu = new Titles.ToolbarMenu();  // Add this line
            titles.toolbarMenu.actions = new Titles.ToolbarMenu.Actions();  // Add this line
            titles.toolbarMenu.shapes = new Titles.ToolbarMenu.ShapeTitles();  // Add this

            JSONObject fileMenu = root.getJSONObject("fileMenu");
            titles.fileMenu.title = fileMenu.getString("title");
            titles.fileMenu.newItemTitle = fileMenu.getString("newItemTitle");
            titles.fileMenu.openFileItemTitle = fileMenu.getString("openFileItemTitle");
            titles.fileMenu.exitItemTitle = fileMenu.getString("exitItemTitle");

            JSONObject shapesMenu = root.getJSONObject("shapesMenu");
            titles.shapesMenu.title = shapesMenu.getString("title");

            JSONObject shapes = shapesMenu.getJSONObject("shapes");
            titles.shapesMenu.shapes.ellipseShapeItemTitle = shapes.getString("ellipseShapeItemTitle");
            titles.shapesMenu.shapes.rectangleShapeItemTitle = shapes.getString("rectangleShapeItemTitle");
            titles.shapesMenu.shapes.lineShapeItemTitle = shapes.getString("lineShapeItemTitle");
            titles.shapesMenu.shapes.pointShapeItemTitle = shapes.getString("pointShapeItemTitle");
            titles.shapesMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle = shapes.getString("lineSegmentWithCirclesAtEndsShapeItemTitle");
            titles.shapesMenu.shapes.parallelepipedShapeItemTitle = shapes.getString("parallelepipedShapeItemTitle");

            JSONObject toolbarMenu = root.getJSONObject("toolbarMenu");  // Add this line

            JSONObject actions = toolbarMenu.getJSONObject("actions");  // Add this line
            titles.toolbarMenu.actions.draw = actions.getString("draw");  // Add this line
            titles.toolbarMenu.actions.erase = actions.getString("erase");  // Add this line

            JSONObject toolbarShapes = toolbarMenu.getJSONObject("shapes");  // Add this line
            titles.toolbarMenu.shapes.ellipseShapeItemTitle = toolbarShapes.getString("ellipseShapeItemTitle");  // Add this line
            titles.toolbarMenu.shapes.rectangleShapeItemTitle = toolbarShapes.getString("rectangleShapeItemTitle");  // Add this line
            titles.toolbarMenu.shapes.lineShapeItemTitle = toolbarShapes.getString("lineShapeItemTitle");  // Add this line
            titles.toolbarMenu.shapes.pointShapeItemTitle = toolbarShapes.getString("pointShapeItemTitle");  // Add this line
            titles.toolbarMenu.shapes.lineSegmentWithCirclesAtEndsShapeItemTitle = shapes.getString("lineSegmentWithCirclesAtEndsShapeItemTitle");
            titles.toolbarMenu.shapes.parallelepipedShapeItemTitle = shapes.getString("parallelepipedShapeItemTitle");


            titles.helpMenuTitle = root.getString("helpMenuTitle");
            titles.selectMark = root.getString("selectMark");

            return titles;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}