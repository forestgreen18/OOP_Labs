package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.datastructures.FormFieldDataStructure;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.scene.control.Button;

public class LastPanel extends Panel {
  public Button finishButton;

  public LastPanel(FormFieldDataStructure[] fields, DataSaver dataSaver) {
    super(fields, dataSaver);
    finishButton = new Button("Finish >>");
    hideNextButton();  // Hide the Next button

    buttonBox.getChildren().add(1, finishButton);

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
