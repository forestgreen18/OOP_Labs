package edu.labs.lab3.shape_editor.editor;

import edu.labs.lab3.shape_editor.ShapeObjectsEditor;
import edu.labs.lab3.shape_editor.shapes.RectangleShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class RectangleShapeEditor  extends ShapeEditor {

    private ShapeObjectsEditor shapeObjectsEditor;
    private GraphicsContext gc;
    private RectangleShape rectangleShape;

    public RectangleShapeEditor(ShapeObjectsEditor shapeObjectsEditor, GraphicsContext gc) {
        super(shapeObjectsEditor, gc);
        this.shapeObjectsEditor = shapeObjectsEditor;
        this.gc = gc;
        this.rectangleShape = new RectangleShape(0, 0, 0, 0, gc);
    }

    @Override
    public void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        double x = event.getX();
        double y = event.getY();
        switch (event.getEventType().getName()) {
            case "MOUSE_PRESSED":
                shapeObjectsEditor.setDrawing(true);
                shapeObjectsEditor.setStartX(x);
                shapeObjectsEditor.setStartY(y);
                shapeObjectsEditor.setEndX(x);
                shapeObjectsEditor.setEndY(y);
                break;
            case "MOUSE_DRAGGED":
                shapeObjectsEditor.setEndX(x);
                shapeObjectsEditor.setEndY(y);
                shapeObjectsEditor.redrawShapes();
                break;
            case "MOUSE_RELEASED":
                shapeObjectsEditor.setDrawing(false);
                saveShape();
                break;
        }
    }

    @Override
    public void saveShape() {
        double startX = shapeObjectsEditor.getStartX();
        double startY = shapeObjectsEditor.getStartY();
        double endX = shapeObjectsEditor.getEndX();
        double endY = shapeObjectsEditor.getEndY();

        RectangleShape rectangle = new RectangleShape(startX, startY, endX, endY, gc);
        shapeObjectsEditor.addShape(rectangle);
    }

    @Override
    public void drawSolidShape(double startX, double startY, double endX, double endY) {
        rectangleShape.setStartX(startX);
        rectangleShape.setStartY(startY);
        rectangleShape.setEndX(endX);
        rectangleShape.setEndY(endY);
        rectangleShape.draw(gc);
    }

}
