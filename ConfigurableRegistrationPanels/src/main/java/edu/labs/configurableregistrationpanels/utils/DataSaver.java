package edu.labs.configurableregistrationpanels.utils;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

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

  public void copyDataToClipboard() {
    String formattedData = formatData();
    StringSelection stringSelection = new StringSelection(formattedData);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
  }

  private String formatData() {
    StringBuilder formatted = new StringBuilder();
    for (String key : data.keySet()) {
      JSONObject innerData = data.getJSONObject(key);
      for (String innerKey : innerData.keySet()) {
        formatted.append(innerKey).append(": ").append(innerData.getString(innerKey)).append("\n");
      }
    }
    return formatted.toString();
  }

}
