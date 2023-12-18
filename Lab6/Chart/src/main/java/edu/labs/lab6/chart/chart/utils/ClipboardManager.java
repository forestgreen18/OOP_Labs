package edu.labs.lab6.chart.chart.utils;

public class ClipboardManager {

  public static double[][] readAndParseFromClipboard() {
    java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit()
        .getSystemClipboard();
    try {
      String data = (String) clipboard.getData(java.awt.datatransfer.DataFlavor.stringFlavor);
      String[] lines = data.split("\\n");
      double[][] points = new double[lines.length][2];
      for (int i = 0; i < lines.length; i++) {
        String[] parts = lines[i].split(", ");
        if (parts.length == 3) {
          points[i][0] = Double.parseDouble(parts[1].split(": ")[1]);
          points[i][1] = Double.parseDouble(parts[2].split(": ")[1].replace(";", ""));
        }
      }
      return points;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new double[0][0];
  }
}
