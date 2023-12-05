package edu.labs.lab4.shape_editor.editor;

import edu.labs.lab4.shape_editor.MyEditor;
import edu.labs.lab4.shape_editor.shapes.PointShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PointShapeEditor extends ShapeEditor {
    private MyEditor myEditor;
    private GraphicsContext gc;
    private PointShape pointShape;


    public PointShapeEditor(MyEditor myEditor, GraphicsContext gc) {
        super( gc);
        this.myEditor = myEditor;
        this.gc = gc;
        this.pointShape = new PointShape(0, 0, gc);
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
            case "MOUSE_RELEASED":
                myEditor.setDrawing(false);
                saveShape();
                break;
        }
    }

    @Override
    public void saveShape() {
        double x = myEditor.getStartX();
        double y = myEditor.getStartY();

        PointShape point = new PointShape(x, y, gc);
        myEditor.addShape(point);
    }

    @Override
    public void drawPreviewShape(double x, double y, double endX, double endY) {
        pointShape.setX(x);
        pointShape.setY(y);
        pointShape.draw(gc, Color.RED);
    }
}
