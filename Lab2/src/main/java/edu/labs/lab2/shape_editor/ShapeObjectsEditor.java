package edu.labs.lab2.shape_editor;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class ShapeObjectsEditor extends Application {
    private boolean drawing;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    @Override
    public void start(Stage stage) throws IOException {

    }

    public static void main(String[] args) {
        launch();
    }


    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }
}