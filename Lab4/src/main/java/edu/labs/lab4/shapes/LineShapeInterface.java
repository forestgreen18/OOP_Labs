package edu.labs.lab4.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface LineShapeInterface {
    void draw(GraphicsContext gc);

    void draw(GraphicsContext gc, Color strokeColor);

    void drawPreviewShape(double startX, double startY, double endX, double endY);
}
