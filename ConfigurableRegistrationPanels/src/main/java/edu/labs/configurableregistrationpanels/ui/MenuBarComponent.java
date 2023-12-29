package edu.labs.configurableregistrationpanels.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuBarComponent {
  private Stage primaryStage;
  private FileChooser fileChooser;

  private MenuBar menuBar;
  private MenuItem openConfigMenuItem;
  private MenuItem saveFormDataItem;

  public MenuBarComponent(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.fileChooser = new FileChooser();
  }

  public javafx.scene.control.MenuBar createMenuBar() {
    menuBar = new javafx.scene.control.MenuBar();
    Menu fileMenu = new Menu("File");
    openConfigMenuItem = new MenuItem("Open configuration file");
    saveFormDataItem = new MenuItem("Save data from the form into file");

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

    saveFormDataItem.setOnAction(e -> {
      // Your action here
    });

    fileMenu.getItems().addAll(openConfigMenuItem, saveFormDataItem);
    menuBar.getMenus().add(fileMenu);

    return menuBar;
  }

  private void createForm(String path) throws IOException {
    // Your implementation here
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
