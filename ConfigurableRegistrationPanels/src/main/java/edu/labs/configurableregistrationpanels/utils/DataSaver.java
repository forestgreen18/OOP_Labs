package edu.labs.configurableregistrationpanels.utils;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class DataSaver {
  private JSONObject data;
  private JSONObject config;

  public DataSaver(JSONObject config) {
    data = new JSONObject();
    this.config = new JSONObject(config.toString());  // Deep copy
    setData(config);
  }

  public void saveInput(String panelType, String fieldName, String inputText) {
    // Update the configuration
    JSONArray panels = config.getJSONArray("panels");
    for (int i = 0; i < panels.length(); i++) {
      JSONObject panel = panels.getJSONObject(i);
      if (panel.getString("panelType").equals(panelType)) {
        JSONArray fields = panel.getJSONArray("fields");
        for (int j = 0; j < fields.length(); j++) {
          JSONObject field = fields.getJSONObject(j);
          if (field.getString("title").equals(fieldName)) {
            field.put("value", inputText);
          }
        }
      }
    }

    // Create a new JSONObject and put the panels JSONArray into it
    JSONObject dataObject = new JSONObject();
    dataObject.put("panels", panels);

    // Set this object as your data
    setData(dataObject);
  }






  public void saveToFile(String filename) {
    // Save the formatted text
    try (FileWriter file = new FileWriter(filename)) {
      file.write(formatData());
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Remove the .txt extension from the filename
    String baseFilename = filename.endsWith(".txt") ? filename.substring(0, filename.length() - 4) : filename;

    // Save the JSON data
    try (FileWriter file = new FileWriter(baseFilename + ".config.json")) {
      file.write(data.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public JSONObject getData() {
    return data;
  }


  public void setData(JSONObject dataObject) {
    this.data = dataObject;
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
    JSONArray panels = data.getJSONArray("panels");
    for (int i = 0; i < panels.length(); i++) {
      JSONObject panel = panels.getJSONObject(i);
      JSONArray fields = panel.getJSONArray("fields");
      for (int j = 0; j < fields.length(); j++) {
        JSONObject field = fields.getJSONObject(j);
        formatted.append(field.getString("title")).append(": ").append(field.getString("value")).append("\n");
      }
    }
    return formatted.toString();
  }


}
