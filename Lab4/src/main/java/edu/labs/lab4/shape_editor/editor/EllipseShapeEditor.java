package edu.labs.lab4.shape_editor.editor;

import edu.labs.lab4.shape_editor.MyEditor;
import edu.labs.lab4.shape_editor.shapes.EllipseShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EllipseShapeEditor extends ShapeEditor {
    private MyEditor myEditor;
    private GraphicsContext gc;
    private EllipseShape ellipseShape;

    public EllipseShapeEditor(MyEditor myEditor, GraphicsContext gc) {
        super( gc);
        this.myEditor = myEditor;
        this.gc = gc;
        this.ellipseShape = new EllipseShape(0, 0, 0, 0, gc);
    }

    @Override
    public void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        double x = event.getX();
        double y = event.getY();
        switch (event.getEventType().getName()) {
            case "MOUSE_PRESSED":
                myEditor.setDrawing(true);
                myEditor.setStartX(x);
                myEditor.setStartY(y);
                myEditor.setEndX(x);
                myEditor.setEndY(y);
                break;
            case "MOUSE_DRAGGED":
                myEditor.setEndX(x);
                myEditor.setEndY(y);
                myEditor.redrawShapes();
                break;
            case "MOUSE_RELEASED":
                myEditor.setDrawing(false);
                saveShape();
                break;
        }
    }

    @Override
    public void saveShape() {
        double startX = myEditor.getStartX();
        double startY = myEditor.getStartY();
        double endX = myEditor.getEndX();
        double endY = myEditor.getEndY();

        EllipseShape ellipse = new EllipseShape(startX, startY, endX, endY, gc);
        myEditor.addShape(ellipse);
    }

    @Override
    public void drawPreviewShape(double startX, double startY, double endX, double endY) {
        ellipseShape.setStartX(startX);
        ellipseShape.setStartY(startY);
        ellipseShape.setEndX(endX);
        ellipseShape.setEndY(endY);
        ellipseShape.draw(gc, Color.RED);
    }
}
