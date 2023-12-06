package edu.labs.lab4.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CubeShape extends Shape{
    public CubeShape(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public void draw(GraphicsContext gc, Color strokeColor) {

    }

    @Override
    public void drawPreviewShape(double startX, double startY, double endX, double endY) {

    }

    @Override
    public Shape clone() {
        return null;
    }
}
