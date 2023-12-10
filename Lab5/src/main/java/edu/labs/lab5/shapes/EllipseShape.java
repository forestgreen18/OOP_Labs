package edu.labs.lab5.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EllipseShape extends Shape {

  public static final Color fillColor = Color.CYAN;
  public static final Color strokeColor = Color.BLACK;
  public static final Color previewStrokeColor = Color.RED;
  private final GraphicsContext gc;

  public EllipseShape(double startX, double startY, double endX, double endY, GraphicsContext gc) {
    super(startX, startY, endX, endY);
    this.gc = gc;
  }

  @Override
  public void draw(GraphicsContext gc) {
    draw(gc, strokeColor, fillColor, false);
  }

  @Override
  public void draw(GraphicsContext gc, Color strokeColor) {
    this.draw(gc, strokeColor, fillColor, false);
  }

  public void draw(GraphicsContext gc, Color strokeColor, Color fillColor) {
    this.draw(gc, strokeColor, fillColor, false);
  }

  public void draw(GraphicsContext gc, Color strokeColor, Color fillColor, boolean dashed) {
    double centerX = (this.getStartX() + this.getEndX()) / 2;
    double centerY = (this.getStartY() + this.getEndY()) / 2;
    double width = Math.abs(this.getStartX() - this.getEndX());
    double height = Math.abs(this.getStartY() - this.getEndY());

    gc.setFill(fillColor);
    gc.setStroke(strokeColor);
    gc.setLineWidth(1);

    if (dashed) {
      gc.setLineDashes(10);
    }

    gc.strokeOval(centerX - width / 2, centerY - height / 2, width, height);
    gc.fillOval(centerX - width / 2, centerY - height / 2, width, height);

    if (dashed) {
      gc.setLineDashes(0);
    }
  }

  @Override
  public void drawPreviewShape(double startX, double startY, double endX, double endY) {
    this.setStartX(startX);
    this.setStartY(startY);
    this.setEndX(endX);
    this.setEndY(endY);
    this.draw(gc, previewStrokeColor, fillColor, true);
  }

  public void drawPreviewShape(double startX, double startY, double endX, double endY,
      Color fillColor) {
    this.setStartX(startX);
    this.setStartY(startY);
    this.setEndX(endX);
    this.setEndY(endY);
    this.draw(gc, previewStrokeColor, fillColor, true);
  }

  @Override
  public EllipseShape clone() {
    return new EllipseShape(this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY(),
        this.gc);
  }


}

