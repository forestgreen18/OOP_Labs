package edu.labs.configurableregistrationpanels.panels;

import javafx.scene.control.Button;

public class FirstPanel extends Panel {
  public FirstPanel(String[] fieldNames) {
    super(fieldNames);
    backButton = new Button("<< Back");
    backButton.setDisable(true);  // The back button should be disabled on the first panel
    panel.getChildren().add(0, backButton);  // Add the back button at the beginning
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
}
