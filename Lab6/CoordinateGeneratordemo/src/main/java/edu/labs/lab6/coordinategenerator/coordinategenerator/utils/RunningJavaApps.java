package edu.labs.lab6.coordinategenerator.coordinategenerator.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunningJavaApps {

  public static boolean isAppRunning(String appName) {
    try {
      // Run the 'jps' command
      Process process = Runtime.getRuntime().exec("jps");
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      // Read the output of the 'jps' command
      String line;
      while ((line = reader.readLine()) != null) {
        // If the output contains the application name, it means the application is already running
        if (line.contains(appName)) {
          return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // If the application name was not found in the output, it means the application is not running
    return false;
  }
}

