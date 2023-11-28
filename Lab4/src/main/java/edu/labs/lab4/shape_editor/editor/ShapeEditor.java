package edu.labs.lab4.shape_editor.editor;

import edu.labs.lab4.shape_editor.MyEditor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class ShapeEditor extends Editor {



    @Override
    public void processMouseEvent(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        // Handle the different types of mouse events
        switch (event.getEventType().getName()) {
            case "MOUSE_PRESSED":
                // Handle mouse pressed event
                break;
            case "MOUSE_RELEASED":
                // Handle mouse released event
                break;
            case "MOUSE_DRAGGED":
                // Handle mouse dragged event
                break;
        }
    }

    public void saveShape() {
        // Implement this method to save the shape
    }

    public void drawPreviewShape(double startX, double startY, double endX, double endY) {
        // Implement this method to draw the shape
    }
}
