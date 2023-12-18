package edu.labs.configurableregistrationpanels.panels;

import javafx.scene.layout.VBox;
import org.json.JSONObject;

public class GeneralPanel {
  private Panel panel;

  public GeneralPanel(JSONObject panelConfig) {
    String panelType = panelConfig.getString("panelType");
    String[] fields = panelConfig.getJSONArray("fields").toList().toArray(new String[0]);


    switch (panelType) {
      case "first":
        panel = new FirstPanel(fields);
        break;
      case "middle":
        panel = new MiddlePanel(fields);
        break;
      case "last":
        panel = new LastPanel(fields);
        break;
      default:
        throw new IllegalArgumentException("Invalid panel type: " + panelType);
    }
  }

  public VBox getPanel() {
    return panel.getPanel();
  }


}
