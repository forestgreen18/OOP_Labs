package edu.labs.lab6.mainapp.utils;

import com.google.gson.Gson; // You'll need to add the Gson library to your project
import java.util.HashMap;
import java.util.Map;

public class DataFormatter {
  public String getFormattedData(String nPoint, String xMin, String xMax, String yMin, String yMax) {
    // Create a map to hold the key-value pairs
    Map<String, String> dataMap = new HashMap<>();
    dataMap.put("nPoint", nPoint);
    dataMap.put("xMin", xMin);
    dataMap.put("xMax", xMax);
    dataMap.put("yMin", yMin);
    dataMap.put("yMax", yMax);

    // Convert the map to a JSON string
    Gson gson = new Gson();
    return gson.toJson(dataMap);
  }
}


