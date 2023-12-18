package edu.labs.lab6.chart.chart.utils;


import edu.labs.lab6.chart.chart.LineChart;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private ServerSocket serverSocket;
  private final LineChart lineChartApp;

  public Server(LineChart lineChartApp) {
    this.lineChartApp = lineChartApp;
  }

  public void startServer() throws IOException {
    serverSocket = new ServerSocket(6668);
    new Thread(() -> {
      while (!serverSocket.isClosed()) {
        try {
          Socket socket = serverSocket.accept(); // establishes connection
          DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
          String str = dataInputStream.readUTF();
          System.out.println("message= " + str);

          // If the received message is "UPDATE", generate new coordinates
          if (str.equals("UPDATE")) {
            System.out.println("I get update message. Working on it.");
            javafx.application.Platform.runLater(
                () -> lineChartApp.addDataToSeries(lineChartApp.getLineChart()));
          }

        } catch (IOException e) {
          if (serverSocket.isClosed()) {
            System.out.println("Server socket is closed, stopping accept loop.");
          } else {
            e.printStackTrace();
          }
        } catch (Exception e) {
          throw new RuntimeException(e);
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
