package edu.labs.lab5.utils.fileWriter;


import edu.labs.lab5.shapes.Shape;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ShapeFileWriter {

    private String filename;

    public ShapeFileWriter(String filename) {
        this.filename = filename;
    }

    public void writeShape(Shape shape) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(shape.getClass().getSimpleName() + "\t" +
                    shape.getStartX() + "\t" +
                    shape.getStartY() + "\t" +
                    shape.getEndX() + "\t" +
                    shape.getEndY());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}