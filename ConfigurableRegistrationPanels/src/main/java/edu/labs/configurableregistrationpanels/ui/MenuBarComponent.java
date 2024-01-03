package edu.labs.configurableregistrationpanels.ui;

import edu.labs.configurableregistrationpanels.MainApplication;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuBarComponent {

  private final Stage primaryStage;
  private final DataSaver dataSaver;
  private final MainApplication mainApplication;
  private MenuBar menuBar;
  private MenuItem openConfigMenuItem;
  private MenuItem saveFormDataItem;

  public MenuBarComponent(Stage primaryStage, DataSaver dataSaver,
      MainApplication mainApplication) {
    this.primaryStage = primaryStage;
    this.dataSaver = dataSaver;
    this.mainApplication = mainApplication;
  }

  public void createMenuBar() {
    menuBar = new javafx.scene.control.MenuBar();
    Menu fileMenu = new Menu("Файл");
    openConfigMenuItem = new MenuItem("Відкрити файл конфігурації");
    saveFormDataItem = new MenuItem("Зберегти дані з форми у файл");

    openConfigMenuItem.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Відкрити файл конфігурації");

      // Set extension filter
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)",
          "*.json");
      fileChooser.getExtensionFilters().add(extFilter);

      File file = fileChooser.showOpenDialog(primaryStage);
      if (file != null) {
        try {
          mainApplication.createForm(file.getPath());
        } catch (IOException ex) {
          // Handle the exception (e.g., show an error message to the user)
        }
      }
    });

    saveFormDataItem.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Зберегти дані");

      // Set extension filter
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)",
          "*.txt");
      fileChooser.getExtensionFilters().add(extFilter);

      File file = fileChooser.showSaveDialog(primaryStage);
      if (file != null) {
        dataSaver.saveToFile(file.getPath());
      }
    });

    fileMenu.getItems().addAll(openConfigMenuItem, saveFormDataItem);
    menuBar.getMenus().add(fileMenu);

  }

  public MenuItem getOpenConfigMenuItem() {
    return openConfigMenuItem;
  }

  public MenuItem getSaveFormDataItem() {
    return saveFormDataItem;
  }

  public MenuBar getMenuBar() {
    return menuBar;
  }
}
