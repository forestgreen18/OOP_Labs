package edu.labs.lab2.shape_editor.editor;

import edu.labs.lab2.shape_editor.ShapeObjectsEditor;
import edu.labs.lab2.shape_editor.shapes.EllipseShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EllipseShapeEditor extends ShapeEditor {
    private ShapeObjectsEditor shapeObjectsEditor;
    private GraphicsContext gc;

    public EllipseShapeEditor(ShapeObjectsEditor shapeObjectsEditor, GraphicsContext gc) {
        super(shapeObjectsEditor, gc);
        this.shapeObjectsEditor = shapeObjectsEditor;
        this.gc = gc;
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

        EllipseShape ellipse = new EllipseShape(startX, startY, endX, endY, gc);
        shapeObjectsEditor.addShape(ellipse);
    }

    @Override
    public void drawSolidShape(double startX, double startY, double endX, double endY) {
        double left = Math.min(startX, endX);
        double top = Math.min(startY, endY);
        double width = Math.abs(startX - endX);
        double height = Math.abs(startY - endY);

        gc.setFill(Color.TRANSPARENT);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(left, top, width, height);
        gc.fillOval(left, top, width, height);
    }
}
