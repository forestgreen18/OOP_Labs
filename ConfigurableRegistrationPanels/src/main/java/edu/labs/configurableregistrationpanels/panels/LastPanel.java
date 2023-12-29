package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.scene.control.Button;

public class LastPanel extends Panel {
  public Button finishButton;

  public LastPanel(String[] fieldNames, DataSaver dataSaver) {
    super(fieldNames, dataSaver);
    backButton = new Button("<< Back");
    finishButton = new Button("Finish >>");
    hideNextButton();  // Hide the Next button

    buttonBox.getChildren().remove(cancelButton);
    buttonBox.getChildren().addAll(backButton, finishButton, cancelButton);


    finishButton.setOnAction(event -> handleFinishButton());

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
    // Save the input field values to a text file
    dataSaver.saveToFile("savedData.json");
  }



}
