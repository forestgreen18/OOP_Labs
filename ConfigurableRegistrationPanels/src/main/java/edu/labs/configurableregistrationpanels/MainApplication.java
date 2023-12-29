package edu.labs.configurableregistrationpanels;

import edu.labs.configurableregistrationpanels.panels.GeneralPanel;
import edu.labs.configurableregistrationpanels.panels.LastPanel;
import edu.labs.configurableregistrationpanels.panels.Panel;
import edu.labs.configurableregistrationpanels.utils.Configuration;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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

  private FileChooser fileChooser;




  @Override
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;

    fileChooser = new FileChooser();
    MenuBar menuBar = createMenuBar();




    root = new VBox();

    HBox centeredRoot = new HBox(root);
    centeredRoot.setAlignment(Pos.CENTER);


    VBox vBox = new VBox(menuBar, centeredRoot);
    Scene scene = new Scene(vBox, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.show();



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


  private Scene createDataScene() {
    MenuBar menuBar = createMenuBar();
    root.getChildren().clear();
    VBox layout = new VBox(menuBar, root);  // Include root in the layout
    layout.setAlignment(Pos.CENTER);  // Center the layout

    // Parse the JSON object
      JSONObject jsonObject = dataSaver.getData();
    System.out.println("=============== data saver get data below ===============");
//    System.out.println(dataSaver.getData().toString());
    System.out.println(dataSaver.getData().getClass());

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
    layout.getChildren().add(0, titleLabel);  // Add the title label at the beginning of the layout

    return new Scene(layout, 800, 600);
  }




  public void displayDataScene() {
    primaryStage.setScene(createDataScene());
  }



  private MenuBar createMenuBar() {
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    MenuItem openConfigMenuItem = new MenuItem("Open configuration file");

    openConfigMenuItem.setOnAction(e -> {
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file != null) {
        try {
          createForm(file.getPath());
        } catch (IOException ex) {
          // Handle the exception (e.g., show an error message to the user)
        }
      }
    });


    fileMenu.getItems().add(openConfigMenuItem);
    menuBar.getMenus().add(fileMenu);

    return menuBar;
  }


  public void createForm(String configFilePath) throws IOException {
    Configuration config = new Configuration(configFilePath);
    panels = new ArrayList<>();
    dataSaver = new DataSaver();

    for (int i = 0; i < config.getNumPanels(); i++) {
      GeneralPanel generalPanel = new GeneralPanel(config.getPanelConfig(i), dataSaver);
      generalPanel.getPanelObject().saveInput(dataSaver);
      panels.add(generalPanel);
    }

    currentPanelIndex = 0;
    Platform.runLater(this::displayCurrentPanel);
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
