package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.scene.control.Button;

public class MiddlePanel extends Panel {
  public MiddlePanel(String[] fieldNames, DataSaver dataSaver) {
    super(fieldNames, dataSaver);
    backButton = new Button("<< Back");

    buttonBox.getChildren().removeAll(nextButton,cancelButton);
    buttonBox.getChildren().addAll(backButton, nextButton, cancelButton);
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
