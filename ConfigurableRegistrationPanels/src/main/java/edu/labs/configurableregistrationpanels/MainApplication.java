package edu.labs.configurableregistrationpanels;

import edu.labs.configurableregistrationpanels.panels.GeneralPanel;
import edu.labs.configurableregistrationpanels.panels.LastPanel;
import edu.labs.configurableregistrationpanels.panels.Panel;
import edu.labs.configurableregistrationpanels.ui.DataSceneCreator;
import edu.labs.configurableregistrationpanels.ui.MenuBarComponent;
import edu.labs.configurableregistrationpanels.utils.Configuration;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class MainApplication extends Application {
  private List<GeneralPanel> panels;
  private int currentPanelIndex;
  private VBox root;
  private DataSaver dataSaver;
  private Stage primaryStage;
  private DataSceneCreator dataSceneCreator;

  private FileChooser fileChooser;




  @Override
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    setupScene();



    root.setPrefWidth(0.65 * primaryStage.getWidth());
    root.setPadding(new Insets(50, 25, 25, 25));

    createForm("F:\\Labs\\OOP\\ConfigurableRegistrationPanels\\src\\main\\resources\\edu\\labs\\configurableregistrationpanels\\formConfiguration.json");

    primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> {
      root.setPrefWidth(0.65 * newValue.doubleValue());
    });

    primaryStage.showingProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        root.setPrefWidth(0.65 * primaryStage.getWidth());
      }
    });


  }

  private void displayCurrentPanel() {
    root.getChildren().clear();

    // Get the root node of the scene
    Parent rootOfScene = primaryStage.getScene().getRoot();

// Check if the root is the expected layout component
    if (rootOfScene instanceof VBox vbox) {

      // Remove all labels from the VBox
      vbox.getChildren().removeIf(node -> node instanceof Label);
    }


    Panel currentPanel = panels.get(currentPanelIndex).getPanelObject();
    VBox currentVBox = currentPanel.getPanelLayout();
    root.getChildren().add(currentVBox);
    // Print the children of the root pane
    System.out.println("Root children: " + root.getChildren());

    // Print the children of the VBox
    for (Node child : currentVBox.getChildren()) {
      System.out.println("VBox child: " + child);
    }


    if (currentPanel.nextButton.getOnAction() == null) {
      currentPanel.nextButton.setOnAction(e -> {
        currentPanelIndex++;
        displayCurrentPanel();
      });
    }
    if (currentPanel.backButton.getOnAction() == null) {
      currentPanel.backButton.setOnAction(e -> {
        currentPanelIndex--;
        displayCurrentPanel();
      });
    }


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







  public void displayDataScene() {
    dataSceneCreator = new DataSceneCreator(primaryStage, dataSaver, root, this);
    Scene dataScene = dataSceneCreator.createDataScene();
    primaryStage.setScene(dataScene);
  }





  public void createForm(String configFilePath) throws IOException {
    setupScene();

    // Continue with the rest of your code...
    Configuration config = new Configuration(configFilePath);
    panels = new ArrayList<>();
    dataSaver = new DataSaver(config.getConfig());

    for (int i = 0; i < config.getNumPanels(); i++) {
      GeneralPanel generalPanel = new GeneralPanel(config.getPanelConfig(i), dataSaver);
      generalPanel.getPanelObject().saveInput(dataSaver);
      panels.add(generalPanel);
    }

    currentPanelIndex = 0;
    Platform.runLater(this::displayCurrentPanel);
  }




  private void setupScene() {
    // Create a new MenuBarComponent
    MenuBarComponent menuBarComponent = new MenuBarComponent(primaryStage, dataSaver, this);
    menuBarComponent.createMenuBar();
    MenuBar menuBar = menuBarComponent.getMenuBar();

    // Create a new root node
    root = new VBox();

    // Create a centered HBox with the root node
    HBox centeredRoot = new HBox(root);
    centeredRoot.setAlignment(Pos.CENTER);

    // Create a VBox with the menuBar and centeredRoot
    VBox vBox = new VBox(menuBar, centeredRoot);

    // Create a new scene with the vBox
    Scene scene = new Scene(vBox, 800, 600);

    // Set the new scene on the primaryStage
    primaryStage.setScene(scene);
    primaryStage.show();
  }



  public static void main(String[] args) {
    launch(args);
  }
}
