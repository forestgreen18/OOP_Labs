package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.datastructures.FormFieldDataStructure;
import edu.labs.configurableregistrationpanels.utils.DataSaver;

public class MiddlePanel extends Panel {

  public MiddlePanel(FormFieldDataStructure[] fields, DataSaver dataSaver) {
    super(fields, dataSaver);
    backButton.setDisable(false);
  }

  @Override
  public String getPanelType() {
    return "middle";
  }
}
