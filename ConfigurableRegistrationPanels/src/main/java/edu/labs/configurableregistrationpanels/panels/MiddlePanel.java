package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.utils.DataSaver;
import java.util.Collections;
import javafx.scene.control.Button;

public class MiddlePanel extends Panel {
  public MiddlePanel(String[] fieldTitles, String[] fieldTypes, DataSaver dataSaver) {
    super(fieldTitles, fieldTypes, dataSaver);
    backButton.setDisable(false);
  }

  @Override
  public void handleNextButton() {
    // Implement the functionality for the next button here
    // This could involve switching to the next panel
  }

  @Override
  public void handleBackButton() {
    // Implement the functionality for the back button here
    // This could involve switching to the previous panel
  }

  @Override
  public String getPanelType() {
    return "middle";
  }
}
