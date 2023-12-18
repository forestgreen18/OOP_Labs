package edu.labs.configurableregistrationpanels;

import edu.labs.configurableregistrationpanels.panels.FirstPanel;
import edu.labs.configurableregistrationpanels.panels.GeneralPanel;
import edu.labs.configurableregistrationpanels.utils.Configuration;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
  @Override
  public void start(Stage primaryStage) throws IOException {

    Configuration config = new Configuration("F:\\Labs\\OOP\\ConfigurableRegistrationPanels\\src\\main\\resources\\edu\\labs\\configurableregistrationpanels\\formConfiguration.json");

    GeneralPanel generalPanel = new GeneralPanel(config.getPanelConfig(3));

    // Add the panel to a scene and display it
    Scene scene = new Scene(generalPanel.getPanel(), 300, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
