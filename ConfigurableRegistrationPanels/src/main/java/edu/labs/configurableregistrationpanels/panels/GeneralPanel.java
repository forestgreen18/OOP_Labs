package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.utils.DataSaver;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeneralPanel {
  private Panel panel;

  public GeneralPanel(JSONObject panelConfig, DataSaver dataSaver) {
    // Extract panelType
    String panelType = panelConfig.getString("panelType");

    // Extract field titles and types
    JSONArray fieldsArray = panelConfig.getJSONArray("fields");
    String[] titlesArray = new String[fieldsArray.length()];
    String[] typesArray = new String[fieldsArray.length()];

    for (int i = 0; i < fieldsArray.length(); i++) {
      JSONObject fieldObject = fieldsArray.getJSONObject(i);
      titlesArray[i] = fieldObject.getString("title");
      typesArray[i] = fieldObject.getString("type");
    }

    // Create the appropriate panel
    switch (panelType) {
      case "first":
        panel = new FirstPanel(titlesArray, typesArray, dataSaver);
        break;
      case "middle":
        panel = new MiddlePanel(titlesArray, typesArray, dataSaver);
        break;
      case "last":
        panel = new LastPanel(titlesArray, typesArray, dataSaver);
        break;
      default:
        throw new IllegalArgumentException("Invalid panel type: " + panelType);
    }
  }

  public Panel getPanelObject() {
    return panel;
  }
}
