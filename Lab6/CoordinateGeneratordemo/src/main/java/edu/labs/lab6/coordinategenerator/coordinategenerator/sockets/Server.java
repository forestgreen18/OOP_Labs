package edu.labs.lab6.coordinategenerator.coordinategenerator.sockets;

import java.io.*;
import java.net.*;

public class Server {
  private ServerSocket serverSocket;

  public void startServer() throws IOException {
    serverSocket = new ServerSocket(6666);
    new Thread(() -> {
      while (!serverSocket.isClosed()) {
        try {
          Socket socket = serverSocket.accept(); // establishes connection
          DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
          String str = dataInputStream.readUTF();
          System.out.println("message= " + str);
        } catch (IOException e) {
          if(serverSocket.isClosed()) {
            System.out.println("Server socket is closed, stopping accept loop.");
          } else {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }


  public void stopServer() throws IOException {
    if (serverSocket != null) {
      serverSocket.close();
    }
  }
}
