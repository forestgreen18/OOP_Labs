package edu.labs.lab6.mainapp.sockets;

import java.io.*;
import java.net.*;

public class Client {
  public void sendMessage() {
    try {
      Socket socket = new Socket("localhost", 6667);
      DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

      dataOutputStream.writeUTF("Clipboard data updated");
      dataOutputStream.flush();

      dataOutputStream.close();
      socket.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
