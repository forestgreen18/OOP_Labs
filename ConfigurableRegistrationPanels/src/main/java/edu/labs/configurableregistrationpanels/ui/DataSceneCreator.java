package edu.labs.configurableregistrationpanels.ui;

import edu.labs.configurableregistrationpanels.MainApplication;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.JSONObject;

public class DataSceneCreator {

  private Stage primaryStage;
  private DataSaver dataSaver;
  private MainApplication mainApplication;
  private VBox root;
  private Scene dataScene;

  public DataSceneCreator(Stage primaryStage, DataSaver dataSaver, VBox root,
      MainApplication mainApplication) {
    this.primaryStage = primaryStage;
    this.dataSaver = dataSaver;
    this.mainApplication = mainApplication;
    this.root = root;
  }

  public Scene createDataScene() {
    MenuBarComponent menuBarComponent = new MenuBarComponent(primaryStage, dataSaver,
        mainApplication);
    menuBarComponent.createMenuBar();
    menuBarComponent.getSaveFormDataItem().setDisable(false);
    MenuBar menuBar = menuBarComponent.getMenuBar();

    root.getChildren().clear();
    VBox layout = new VBox(menuBar, root);  // Include root in the layout
    layout.setAlignment(Pos.TOP_CENTER);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(layout);

    // Parse the JSON object
    JSONObject jsonObject = dataSaver.getData();

    // Iterate over the keys (i.e., "middle", "first")
    for (String key : jsonObject.keySet()) {
      // Check if the value associated with the key is a JSONObject
      if (jsonObject.get(key) instanceof JSONObject) {
        // Get the inner JSON object
        JSONObject innerJsonObject = jsonObject.getJSONObject(key);

        // Iterate over the keys of the inner JSON object
        for (String innerKey : innerJsonObject.keySet()) {
          // Create a label with the key and value from the inner JSON object
          Label label = new Label(innerKey + ": " + innerJsonObject.getString(innerKey));

          // Set the font size of the label
          label.setFont(new Font(20));  // Set the font size to 20

          // Add the label to the layout
          layout.getChildren().add(label);
        }
      } else {
        // Handle non-JSONObject values here
        // For example, if the value is a String, you can create a label with the key and value
        String value = jsonObject.getString(key);
        Label label = new Label(key + ": " + value);
        label.setFont(new Font(20));  // Set the font size to 20
        layout.getChildren().add(label);
      }
    }

    // Create a title label
    Label titleLabel = new Label("Data from the Form");
    titleLabel.setFont(new Font(24));  // Set the font size of the title to 24

    Button copyDataToClipboardButton = new Button("Copy data to clipboard");
    copyDataToClipboardButton.setOnAction(e -> {
      dataSaver.copyDataToClipboard();
    });

    Insets margin = new Insets(10, 10, 10, 10);

    VBox.setMargin(copyDataToClipboardButton, margin);

    layout.getChildren().add(1, titleLabel);  // Add the title label at the beginning of the layout
    layout.getChildren().add(copyDataToClipboardButton);
    this.dataScene = new Scene(borderPane, 800, 600);
    return this.dataScene;
  }


  public void hideDataScene() {
    if (this.dataScene != null && primaryStage.getScene() == this.dataScene) {
      // If the dataScene is currently displayed, hide the primaryStage
      primaryStage.hide();
    }
  }

  public Scene getDataScene() {
    return dataScene;
  }

  public void hideDataSceneContent() {
    if (this.dataScene != null && primaryStage.getScene() == this.dataScene) {
      // If the dataScene is currently displayed, clear its content
      ((Pane) this.dataScene.getRoot()).getChildren().clear();
    }
  }

}