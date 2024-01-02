package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.datastructures.FormFieldDataStructure;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.scene.control.Button;

public class FirstPanel extends Panel {
  public FirstPanel(FormFieldDataStructure[] fields, DataSaver dataSaver) {
    super(fields, dataSaver);
    backButton.setDisable(true);  // The back button should be disabled on the first panel
  }

  @Override
  public void handleNextButton() {
    // Implement the functionality for the next button here
    // This could involve switching to the next panel
  }

  @Override
  public void handleBackButton() {
    // The back button is disabled on the first panel, so this method can be left empty
  }

  @Override
  public String getPanelType() {
    return "first";
  }
}
