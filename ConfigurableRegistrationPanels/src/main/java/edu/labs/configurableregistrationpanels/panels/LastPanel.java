package edu.labs.configurableregistrationpanels.panels;

import javafx.scene.control.Button;

public class LastPanel extends Panel {
  private Button finishButton;

  public LastPanel(String[] fieldNames) {
    super(fieldNames);
    backButton = new Button("<< Back");
    finishButton = new Button("Finish >>");
    panel.getChildren().addAll(backButton, finishButton);  // Add the back and finish buttons
    hideNextButton();  // Hide the Next button
  }

  @Override
  public void handleNextButton() {
    // The next button is not present on the last panel, so this method can be left empty
  }

  @Override
  public void handleBackButton() {
    // Implement the functionality for the back button here
    // This could involve switching to the previous panel
  }

  @Override
  public String getPanelType() {
    return "last";
  }

  public void handleFinishButton() {
    // Implement the functionality for the finish button here
    // This could involve saving the input field values to a text file
  }
}
