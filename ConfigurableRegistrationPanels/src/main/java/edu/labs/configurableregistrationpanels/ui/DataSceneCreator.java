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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataSceneCreator {

  private final Stage primaryStage;
  private final DataSaver dataSaver;
  private final MainApplication mainApplication;
  private final VBox root;

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
// Get the panels JSONArray from the jsonObject
    JSONArray panels = jsonObject.getJSONArray("panels");

    // Iterate over the panels
    for (int i = 0; i < panels.length(); i++) {
      // Get the panel JSONObject
      JSONObject panel = panels.getJSONObject(i);

      // Get the fields JSONArray from the panel
      JSONArray fields = panel.getJSONArray("fields");

      // Iterate over the fields
      for (int j = 0; j < fields.length(); j++) {
        // Get the field JSONObject
        JSONObject field = fields.getJSONObject(j);

        // Create a label with the title and value from the field
        Label label = new Label(field.getString("title") + ": " + field.getString("value"));

        // Set the font size of the label
        label.setFont(new Font(20));  // Set the font size to 20

        // Add the label to the layout
        layout.getChildren().add(label);
      }
    }

    // Create a title label
    Label titleLabel = new Label("Дані з форми");
    titleLabel.setFont(new Font(24));  // Set the font size of the title to 24

    Button copyDataToClipboardButton = new Button("Копіювати дані в буфер обміну");
    copyDataToClipboardButton.setOnAction(e -> dataSaver.copyDataToClipboard());

    Insets margin = new Insets(10, 10, 10, 10);

    VBox.setMargin(copyDataToClipboardButton, margin);

    layout.getChildren().add(1, titleLabel);  // Add the title label at the beginning of the layout
    layout.getChildren().add(copyDataToClipboardButton);
    return new Scene(borderPane, 800, 600);
  }


}