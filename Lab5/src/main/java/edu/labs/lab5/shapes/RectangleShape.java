package edu.labs.lab5.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape extends Shape {

  public static final Color fillColor = Color.TRANSPARENT;
  public static final Color strokeColor = Color.BLACK;
  public static final Color previewStrokeColor = Color.RED;
  private final GraphicsContext gc;
  private double left;
  private double top;
  private double right;
  private double bottom;

  public RectangleShape(double startX, double startY, double endX, double endY,
      GraphicsContext gc) {
    super(startX, startY, endX, endY);
    calculateBounds();
    this.gc = gc;
  }


  private void calculateBounds() {
    double centerX = getStartX();
    double centerY = getStartY();
    double cornerX = getEndX();
    double cornerY = getEndY();

    double width = Math.abs(centerX - cornerX) * 2;
    double height = Math.abs(centerY - cornerY) * 2;
    this.left = centerX - width / 2;
    this.top = centerY - height / 2;
    this.right = this.left + width;
    this.bottom = this.top + height;
  }

  public double getLeft() {
    return this.left;
  }

  public void setLeft(double left) {
    this.left = left;
    calculateBounds();
  }

  public double getTop() {
    return this.top;
  }

  public void setTop(double top) {
    this.top = top;
    calculateBounds();
  }

  public double getRight() {
    return this.right;
  }

  public void setRight(double right) {
    this.right = right;
    calculateBounds();
  }

  public double getBottom() {
    return this.bottom;
  }

  public void setBottom(double bottom) {
    this.bottom = bottom;
    calculateBounds();
  }


  public void setStartX(double startX) {
    super.setStartX(startX);
    calculateBounds();
  }

  public void setStartY(double startY) {
    super.setStartY(startY);
    calculateBounds();
  }

  public void setEndX(double endX) {
    super.setEndX(endX);
    calculateBounds();
  }

  public void setEndY(double endY) {
    super.setEndY(endY);
    calculateBounds();
  }


  @Override
  public void draw(GraphicsContext gc) {
    draw(gc, strokeColor, fillColor, false);
  }

  @Override
  public void draw(GraphicsContext gc, Color strokeColor) {
    draw(gc, strokeColor, fillColor, false);
  }

  public void draw(GraphicsContext gc, Color strokeColor, Color fillColor) {
    draw(gc, strokeColor, fillColor, false);
  }

  public void draw(GraphicsContext gc, Color strokeColor, Color fillColor, boolean dashed) {
    calculateBounds();
    double width = Math.abs(getStartX() - getEndX()) * 2;
    double height = Math.abs(getStartY() - getEndY()) * 2;

    gc.setFill(fillColor);
    gc.setStroke(strokeColor);
    gc.setLineWidth(1);

    if (dashed) {
      gc.setLineDashes(10);
    }

    gc.strokeRect(getLeft(), getTop(), width, height);
    gc.fillRect(getLeft(), getTop(), width, height);

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
  public RectangleShape clone() {
    return new RectangleShape(this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY(),
        gc);
  }
}
