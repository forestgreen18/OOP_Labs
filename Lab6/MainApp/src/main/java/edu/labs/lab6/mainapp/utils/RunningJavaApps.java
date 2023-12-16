package edu.labs.lab6.mainapp.utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;

;

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

  public static void listRunningJavaApps() {
    try {
      // Run the 'jps' command
      Process process = Runtime.getRuntime().exec("jps");
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      // Read the output of the 'jps' command
      String line;
      System.out.println("List of running Java applications:");
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

