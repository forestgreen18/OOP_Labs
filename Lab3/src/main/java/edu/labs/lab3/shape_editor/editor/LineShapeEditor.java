package edu.labs.lab3.shape_editor.editor;

import edu.labs.lab3.shape_editor.ShapeObjectsEditor;
import edu.labs.lab3.shape_editor.shapes.LineShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LineShapeEditor extends ShapeEditor {
    private ShapeObjectsEditor shapeObjectsEditor;
    private GraphicsContext gc;
    private LineShape lineShape;
    public LineShapeEditor(ShapeObjectsEditor shapeObjectsEditor, GraphicsContext gc) {
        super(shapeObjectsEditor, gc);
        this.shapeObjectsEditor = shapeObjectsEditor;
        this.gc = gc;
        this.lineShape = new LineShape(0, 0, 0, 0, gc);
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

        LineShape line = new LineShape(startX, startY, endX, endY, gc);
        shapeObjectsEditor.addShape(line);
    }

    @Override
    public void drawSolidShape(double startX, double startY, double endX, double endY) {
        lineShape.setStartX(startX);
        lineShape.setStartY(startY);
        lineShape.setEndX(endX);
        lineShape.setEndY(endY);
        lineShape.draw(gc, Color.RED);
    }
}
