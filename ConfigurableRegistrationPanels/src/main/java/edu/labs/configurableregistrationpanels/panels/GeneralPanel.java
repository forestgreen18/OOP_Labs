package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.utils.DataSaver;
import org.json.JSONObject;

public class GeneralPanel {
  private Panel panel;

  public GeneralPanel(JSONObject panelConfig, DataSaver dataSaver) {
    String panelType = panelConfig.getString("panelType");
    String[] fields = panelConfig.getJSONArray("fields").toList().toArray(new String[0]);


    switch (panelType) {
      case "first":
        panel = new FirstPanel(fields, dataSaver);
        break;
      case "middle":
        panel = new MiddlePanel(fields, dataSaver);
        break;
      case "last":
        panel = new LastPanel(fields, dataSaver);
        break;
      default:
        throw new IllegalArgumentException("Invalid panel type: " + panelType);
    }
  }

  public Panel getPanelObject() {
    return panel;
  }



}
