package edu.ntnu.bidata.syrstad;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
  private ServerSocket serverSocket;
  private static SmartTv smartTv;
  private DataOutputStream dataOutputStream;
  private ArrayList<DataOutputStream> clients = new ArrayList<>();

  /**
   * Creates a server listening on port '1238'
   * Always accepts new clients
   *
   * @throws IOException
   */
  public Server() throws IOException {
    serverSocket = new ServerSocket(1238);
    System.out.println("Server started on port " + "1238");

    while (true) {
      Socket clientSocket = serverSocket.accept();
      new Thread(() -> handleClient(clientSocket)).start();
    }
  }

  /**
   * Creates a server listening on the specified port.
   * Always accepts new clients
   *
   * @param port
   * @throws IOException
   */
  public Server(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    System.out.println("Server started on port " + port);

    while (true) {
      Socket clientSocket = serverSocket.accept();
      new Thread(() -> handleClient(clientSocket)).start();
      clients.add(new DataOutputStream(clientSocket.getOutputStream()));
    }
  }

  /**
   * Handles a client socket connection and receives messages.
   *
   * @param socket to handle.
   */
  public void handleClient(Socket socket) {
    System.out.println("Client connected: " + socket
        .getInetAddress()
        .getHostAddress());
    try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
      try {
        String line;
        while ((line = in.readUTF()) != null && !line.equals("null")) {
          String[] parts = line.trim().toUpperCase().split(" ");
          Commands command = null;
          for (Commands commands : Commands.values()) {
            if (parts[0].equals(commands.name())) {
              command = commands;
              break;
            }
          }
          if (command != null) {
            switch (command) {
              case POWER:
                smartTv.toggleOn();
                message(smartTv.update());
                break;
              case SET_CHANNEL:
                smartTv.setChannel(Integer.parseInt(parts[1]));
                message(smartTv.update());
                break;
              case SET_VOLUME:
                smartTv.setVolume(Integer.parseInt(parts[1]));
                message(smartTv.update());
                break;
              case CHANNEL_UP:
                smartTv.channelUp();
                message(smartTv.update());
                break;
              case CHANNEL_DOWN:
                smartTv.channelDown();
                message(smartTv.update());
                break;
              case STATUS:
                message(smartTv.update());
                break;
              default:
                message(smartTv.update());
                break;
            }
          }
        }
      } catch (IOException | RuntimeException e) {
        throw new RuntimeException(e);
      }
    } catch (IOException | RuntimeException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Sends a message to the client
   *
   * @param message
   */
  public void message(String message) {
    for (DataOutputStream out : clients) {
      try {
        out.writeUTF(message);
        out.flush();
      } catch (IOException e) {
        clients.remove(out);
      }
    }
  }


  /**
   * Starts the server
   *
   * @param args
   */
  public static void main(String[] args) {
    smartTv = new SmartTv();
    try {
      new Server(65534);
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
