package edu.labs.configurableregistrationpanels.utils;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class DataSaver {
  private JSONObject data;

  public DataSaver() {
    data = new JSONObject();
  }

  public void saveInput(String panelType, String fieldName, String inputText) {
    JSONObject panelData = data.has(panelType) ? data.getJSONObject(panelType) : new JSONObject();
    panelData.put(fieldName, inputText);
    data.put(panelType, panelData);
  }

  public void saveToFile(String filename) {
    try (FileWriter file = new FileWriter(filename)) {
      file.write(data.toString());
//      System.out.println("Successfully Copied JSON Object to File...");
//      System.out.println("\nJSON Object: " + data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public JSONObject getData() {
    return data;
  }


  public void clearData() {
    data = new JSONObject();
  }


}
