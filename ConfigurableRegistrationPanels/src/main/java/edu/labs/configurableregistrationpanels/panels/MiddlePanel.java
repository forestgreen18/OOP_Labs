package edu.labs.configurableregistrationpanels.panels;

import javafx.scene.control.Button;

public class MiddlePanel extends Panel {
  public MiddlePanel(String[] fieldNames) {
    super(fieldNames);
    backButton = new Button("<< Back");
    panel.getChildren().add(0, backButton);  // Add the back button at the beginning
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
}
