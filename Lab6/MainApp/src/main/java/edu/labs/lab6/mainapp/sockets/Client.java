package edu.labs.lab6.mainapp.sockets;

import java.io.*;
import java.net.*;

public class Client {
  public void sendMessage(String message) {
    while (true) {
      try {
        Socket socket = new Socket("localhost", 6667);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();

        dataOutputStream.close();
        socket.close();

        // Connection successful, break the loop
        System.out.println("Connection is established");
        break;
      } catch (IOException e) {
        System.out.println("Connection failed, retrying...");
        try {
          // Wait for a short period before retrying
          Thread.sleep(1000); // 1000 milliseconds = 1 second
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
      }
    }
  }
}
