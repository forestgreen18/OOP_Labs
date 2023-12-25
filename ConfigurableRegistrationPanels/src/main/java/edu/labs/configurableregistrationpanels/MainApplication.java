package edu.labs.configurableregistrationpanels;

import edu.labs.configurableregistrationpanels.panels.GeneralPanel;
import edu.labs.configurableregistrationpanels.panels.LastPanel;
import edu.labs.configurableregistrationpanels.panels.Panel;
import edu.labs.configurableregistrationpanels.utils.Configuration;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {
  private List<GeneralPanel> panels;
  private int currentPanelIndex;
  private Pane root;
  private DataSaver dataSaver;
  private Stage primaryStage;

  @Override
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;

    FileChooser fileChooser = new FileChooser();

    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    MenuItem openConfigMenuItem = new MenuItem("Open configuration file");




    fileMenu.getItems().add(openConfigMenuItem);
    menuBar.getMenus().add(fileMenu);

    Configuration config = new Configuration("F:\\Labs\\OOP\\ConfigurableRegistrationPanels\\src\\main\\resources\\edu\\labs\\configurableregistrationpanels\\formConfiguration.json");
    panels = new ArrayList<>();
    dataSaver = new DataSaver();

    for (int i = 0; i < config.getNumPanels(); i++) {

      GeneralPanel generalPanel = new GeneralPanel(config.getPanelConfig(i), dataSaver);
      generalPanel.getPanelObject().saveInput(dataSaver);
      panels.add(generalPanel);
    }

    openConfigMenuItem.setOnAction(e -> {
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file != null) {
        try {
          loadPanels(panels, dataSaver, file.getPath());
          // Reset the current panel index and refresh the view
          currentPanelIndex = 0;
          displayCurrentPanel();
        } catch (IOException ex) {
          // Handle the exception (e.g., show an error message to the user)
        }
      }
    });


    root = new Pane();
    VBox vBox = new VBox(menuBar, root);
    Scene scene = new Scene(vBox, 300, 200);
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

    // Check if the current panel is the last panel
    if (currentPanel.getPanelType().equals("last")) {
      // Cast the current panel to LastPanel so you can access the finishButton
      LastPanel lastPanel = (LastPanel) currentPanel;
      lastPanel.finishButton.setOnAction(e -> {
        // Save the input field values to a text file
        dataSaver.saveToFile("savedData.json");
        // Display the data scene
        displayDataScene();
      });
    }
  }


  private Scene createDataScene() {
    VBox layout = new VBox();
    for (String key : dataSaver.getData().keySet()) {
      Label label = new Label(key + ": " + dataSaver.getData().get(key));
      layout.getChildren().add(label);
    }
    return new Scene(layout, 300, 200);
  }


  public void displayDataScene() {
    primaryStage.setScene(createDataScene());
  }


  public void loadPanels(List<GeneralPanel> panels, DataSaver dataSaver, String configFilePath) throws IOException {
    // Clear the existing panels
    panels.clear();

    // Load the new configuration
    Configuration config = new Configuration(configFilePath);

    // Create the panels based on the new configuration
    for (int i = 0; i < config.getNumPanels(); i++) {
      GeneralPanel generalPanel = new GeneralPanel(config.getPanelConfig(i), dataSaver);
      generalPanel.getPanelObject().saveInput(dataSaver);
      panels.add(generalPanel);
    }
  }


  public static void main(String[] args) {
    launch(args);
  }
}
