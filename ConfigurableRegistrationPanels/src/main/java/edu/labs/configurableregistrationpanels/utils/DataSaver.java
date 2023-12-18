package edu.labs.configurableregistrationpanels.utils;

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
    // Implement the functionality to save the data JSONObject to a file
  }

  public JSONObject getData () {
    return data;
  }
}
