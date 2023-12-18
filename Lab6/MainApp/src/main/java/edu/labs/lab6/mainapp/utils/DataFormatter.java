package edu.labs.lab6.mainapp.utils;

public class DataFormatter {

  public String getFormattedData(String nPoint, String xMin, String xMax, String yMin,
      String yMax) {
    return "nPoint: " + nPoint + ";\n" +
        "xMin: " + xMin + ";\n" +
        "xMax: " + xMax + ";\n" +
        "yMin: " + yMin + ";\n" +
        "yMax: " + yMax + ";";
  }
}

