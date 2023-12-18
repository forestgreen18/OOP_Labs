package edu.labs.configurableregistrationpanels;

import edu.labs.configurableregistrationpanels.panels.GeneralPanel;
import edu.labs.configurableregistrationpanels.panels.Panel;
import edu.labs.configurableregistrationpanels.utils.Configuration;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {
  private List<GeneralPanel> panels;
  private int currentPanelIndex;
  private Pane root;
  private DataSaver dataSaver;

  @Override
  public void start(Stage primaryStage) throws IOException {
    Configuration config = new Configuration("F:\\Labs\\OOP\\ConfigurableRegistrationPanels\\src\\main\\resources\\edu\\labs\\configurableregistrationpanels\\formConfiguration.json");
    panels = new ArrayList<>();
    dataSaver = new DataSaver();

    for (int i = 0; i < config.getNumPanels(); i++) {

      GeneralPanel generalPanel = new GeneralPanel(config.getPanelConfig(i), dataSaver);
      generalPanel.getPanelObject().saveInput(dataSaver);
      panels.add(generalPanel);
    }

    root = new Pane();
    Scene scene = new Scene(root, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.show();

    currentPanelIndex = 0;
    displayCurrentPanel();
  }

  private void displayCurrentPanel() {
    root.getChildren().clear();
    Panel currentPanel = panels.get(currentPanelIndex).getPanelObject();
    VBox currentVBox = currentPanel.getPanelLayout();
    root.getChildren().add(currentVBox);

    currentPanel.nextButton.setOnAction(e -> {
      currentPanelIndex++;
      displayCurrentPanel();
    });
    currentPanel.backButton.setOnAction(e -> {
      currentPanelIndex--;
      displayCurrentPanel();
    });

    currentPanel.cancelButton.setOnAction(e -> {
      for (GeneralPanel generalPanel : panels) {
        generalPanel.getPanelObject().clearInputFields();
      }
      dataSaver.clearData();
    });
  }


  public static void main(String[] args) {
    launch(args);
  }
}
