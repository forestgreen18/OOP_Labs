package edu.labs.configurableregistrationpanels.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class Configuration {

  private final JSONArray panels;
  private final JSONObject config;

  public Configuration(String filename) throws IOException {
    String content = new String(Files.readAllBytes(Paths.get(filename)));
    config = new JSONObject(content);
    JSONObject jsonObject = new JSONObject(content);
    panels = jsonObject.getJSONArray("panels");
  }

  public int getNumPanels() {
    return panels.length();
  }

  public JSONObject getPanelConfig(int index) {
    return panels.getJSONObject(index);
  }

  public JSONObject getConfig() {
    return config;
  }


}

