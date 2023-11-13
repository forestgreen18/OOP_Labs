package edu.labs.lab2.shape_editor.editor;

import edu.labs.lab2.shape_editor.ShapeObjectsEditor;
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
                System.out.println("The mouse is pressed");
                System.out.println("startX: " + x);
                System.out.println("startY: " + y);
                shapeObjectsEditor.setDrawing(true);
                shapeObjectsEditor.setStartX(x);
                shapeObjectsEditor.setStartY(y);
                shapeObjectsEditor.setEndX(x);
                shapeObjectsEditor.setEndY(y);
                break;
            case "MOUSE_DRAGGED":
                System.out.println("The mouse is pressed");
                System.out.println("endX: " + x);
                System.out.println("endY: " + y);
                shapeObjectsEditor.setEndX(x);
                shapeObjectsEditor.setEndY(y);
                break;
            case "MOUSE_RELEASED":
                shapeObjectsEditor.setDrawing(false);
                // saveShape(); // You said not to implement this method
                break;
        }
    }

    @Override
    public void drawSolidShape(double startX, double startY, double endX, double endY) {
        double left = Math.min(startX, endX);
        double top = Math.min(startY, endY);
        double width = Math.abs(startX - endX);
        double height = Math.abs(startY - endY);

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(left, top, width, height);
        gc.fillOval(left, top, width, height);
    }
}
