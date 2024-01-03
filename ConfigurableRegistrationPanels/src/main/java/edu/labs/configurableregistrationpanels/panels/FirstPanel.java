package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.datastructures.FormFieldDataStructure;
import edu.labs.configurableregistrationpanels.utils.DataSaver;

public class FirstPanel extends Panel {

  public FirstPanel(FormFieldDataStructure[] fields, DataSaver dataSaver) {
    super(fields, dataSaver);
    backButton.setDisable(true);  // The back button should be disabled on the first panel
  }

  @Override
  public String getPanelType() {
    return "first";
  }
}
