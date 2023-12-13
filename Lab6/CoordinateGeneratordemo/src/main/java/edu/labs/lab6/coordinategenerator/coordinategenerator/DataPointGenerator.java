package edu.labs.lab6.coordinategenerator.coordinategenerator;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DataPointGenerator {

  private int nPoints;
  private double xMin;
  private double xMax;
  private double yMin;
  private double yMax;

  public DataPointGenerator(int nPoints, double xMin, double xMax, double yMin, double yMax) {
    this.nPoints = nPoints;
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
  }

  public double[][] generatePoints() {
    Random rand = new Random();
    double[][] points = new double[nPoints][2];

    for (int i = 0; i < nPoints; i++) {
      double x = xMin + (xMax - xMin) * rand.nextDouble();
      double y = yMin + (yMax - yMin) * rand.nextDouble();
      points[i][0] = Math.round(x * 100.0) / 100.0;
      points[i][1] = Math.round(y * 100.0) / 100.0;
    }

    return points;
  }

  public Map<String, Double> readFromClipboard() {
    Map<String, Double> result = new HashMap<>();
    java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
    try {
      String data = (String) clipboard.getData(java.awt.datatransfer.DataFlavor.stringFlavor);
      String[] lines = data.split("\\n");
      for (String line : lines) {
        String[] parts = line.split(": ");
        if (parts.length == 2) {
          result.put(parts[0], Double.parseDouble(parts[1].replace(";", "")));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }


  public void writePointsToClipboard(double[][] points) {
    StringBuilder clipboardString = new StringBuilder();

    for (int i = 0; i < points.length; i++) {
      clipboardString.append("Number: ").append(i + 1).append(", X: ").append(points[i][0]).append(", Y: ").append(points[i][1]).append(";\n");
    }

    // Create a StringSelection object
    java.awt.datatransfer.StringSelection stringSelection = new java.awt.datatransfer.StringSelection(clipboardString.toString());

    // Get the system clipboard
    java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();

    // Write the string to the clipboard
    clipboard.setContents(stringSelection, null);
  }



}
