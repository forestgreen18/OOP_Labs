package edu.labs.lab6.chart.chart.utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ClipboardManager {

  public static double[][] readAndParseFromClipboard() {
    java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
    try {
      String data = (String) clipboard.getData(java.awt.datatransfer.DataFlavor.stringFlavor);

      // Create a Gson object
      Gson gson = new Gson();

      // Define the type of the data to be parsed
      Type type = new TypeToken<double[][]>(){}.getType();

      // Parse the JSON string to a 2D array
      double[][] points = gson.fromJson(data, type);

      return points;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new double[0][0];
  }
}
