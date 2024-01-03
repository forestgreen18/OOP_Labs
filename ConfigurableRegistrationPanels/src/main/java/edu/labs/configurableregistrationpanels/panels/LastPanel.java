package edu.labs.configurableregistrationpanels.panels;

import edu.labs.configurableregistrationpanels.datastructures.FormFieldDataStructure;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.scene.control.Button;

public class LastPanel extends Panel {

  public final Button finishButton;

  public LastPanel(FormFieldDataStructure[] fields, DataSaver dataSaver) {
    super(fields, dataSaver);
    finishButton = new Button("Завершити >>");
    hideNextButton();  // Hide the Next button

    buttonBox.getChildren().add(1, finishButton);

    finishButton.setOnAction(event -> handleFinishButton());
  }

  @Override
  public String getPanelType() {
    return "last";
  }

  public void handleFinishButton() {
    // Save the input field values to a text file
    dataSaver.saveToFile("savedData.txt");
  }


}
