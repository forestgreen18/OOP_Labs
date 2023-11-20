package edu.labs.lab3.utils;


import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;

public class JsonFileReader {
    public Titles readJsonFile() {
        try {
            InputStream is = new FileInputStream("F:\\Labs\\OOP\\Lab3\\src\\main\\resources\\edu\\labs\\lab3\\menuTitles.json");
            JSONTokener tokener = new JSONTokener(is);
            JSONObject root = new JSONObject(tokener);

            Titles titles = new Titles();
            titles.fileMenu = new Titles.FileMenu();
            titles.shapesMenu = new Titles.ShapesMenu();
            titles.shapesMenu.shapes = new Titles.ShapesMenu.ShapeTitles();

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

            titles.helpMenuTitle = root.getString("helpMenuTitle");
            titles.selectMark = root.getString("selectMark");

            return titles;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}