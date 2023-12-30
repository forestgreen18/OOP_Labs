package edu.labs.configurableregistrationpanels.ui;

import edu.labs.configurableregistrationpanels.MainApplication;
import edu.labs.configurableregistrationpanels.utils.DataSaver;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuBarComponent {
  private Stage primaryStage;
  private DataSaver dataSaver;
  private FileChooser fileChooser;

  private MenuBar menuBar;
  private MenuItem openConfigMenuItem;
  private MenuItem saveFormDataItem;
  private MainApplication mainApplication;

  public MenuBarComponent(Stage primaryStage, DataSaver dataSaver, MainApplication mainApplication) {
    this.primaryStage = primaryStage;
    this.dataSaver = dataSaver;
    this.fileChooser = new FileChooser();
    this.mainApplication = mainApplication;
  }

  public javafx.scene.control.MenuBar createMenuBar() {
    menuBar = new javafx.scene.control.MenuBar();
    Menu fileMenu = new Menu("File");
    openConfigMenuItem = new MenuItem("Open configuration file");
    saveFormDataItem = new MenuItem("Save data from the form into file");

    openConfigMenuItem.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Configuration File");

      // Set extension filter
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
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
      fileChooser.setTitle("Save Data");

      // Set extension filter
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
      fileChooser.getExtensionFilters().add(extFilter);

      File file = fileChooser.showSaveDialog(primaryStage);
      if (file != null) {
        dataSaver.saveToFile(file.getPath());
      }
    });



    fileMenu.getItems().addAll(openConfigMenuItem, saveFormDataItem);
    menuBar.getMenus().add(fileMenu);

    return menuBar;
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
