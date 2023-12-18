package edu.labs.lab6.coordinategenerator.coordinategenerator.sockets;


import java.io.*;
import java.net.*;

public class Client {
  public boolean sendMessage(String message) {
    int maxRetries = 5; // Maximum number of retries
    int retries = 0; // Current number of retries

    while (retries < maxRetries) {
      try {
        Socket socket = new Socket("localhost", 6668);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();

        dataOutputStream.close();
        socket.close();

        // Connection successful, break the loop
        System.out.println("Connection is established");
        return true;
      } catch (IOException e) {
        System.out.println("Connection failed, retrying...");
        retries++; // Increment the number of retries

        try {
          // Wait for a short period before retrying
          Thread.sleep(1000); // 1000 milliseconds = 1 second
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
      }
    }

    // If the method reaches this point, it means it couldn't establish a connection
    return false;
  }
}

