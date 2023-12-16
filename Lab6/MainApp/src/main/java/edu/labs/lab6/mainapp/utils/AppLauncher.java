package edu.labs.lab6.mainapp.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppLauncher {

  public static void launchApp(String filePath) {
    try {
      // Create a new process builder
      ProcessBuilder pb = new ProcessBuilder(filePath);

      // Start the process
      Process p = pb.start();

      BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
