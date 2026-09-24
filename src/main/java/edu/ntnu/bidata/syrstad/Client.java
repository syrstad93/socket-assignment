package edu.ntnu.bidata.syrstad;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Represents a client connection to a server.
 */
public class Client {
  private Socket socket;
  private DataOutputStream outputStream;
  private Boolean connected = false;
  private int retries = 0;
  private final String address;
  private final int port;

  public Client() throws InterruptedException {
    address = "127.0.0.1";
    this.port = 1238;
    connect(this.port, this.address);
  }

  public Client(int port) throws InterruptedException {
    address = "127.0.0.1";
    this.port = port;
    connect(this.port, this.address);
  }

  private void connect(int port, String address) throws InterruptedException {
    try {
      socket = new Socket(address, port);
      System.out.println("Connected to server at " + address + ":" + port);
      outputStream = new DataOutputStream(socket.getOutputStream());
      connected = true;
      retries = 0;
    } catch (IOException io) {
      System.out.println(io.getMessage());
      retryConnection(port, address);
    }
  }

  private void retryConnection(int port, String address) throws InterruptedException {
    if (retries++ < 3) {
      System.out.println("Retrying connection...");
      Thread.sleep(2000);
      connect(port, address);
    }
  }

  public void message(String message) throws IOException, InterruptedException {
    try {
      outputStream.writeUTF(message);
      outputStream.flush();
    } catch (IOException io) {
      System.out.println(io.getMessage());
      retryConnection(port, address);
    }
  }

  public void close() {
    try {
      socket.close();
      outputStream.close();
    } catch (IOException io) {
      System.out.println(io.getMessage());
    }
  }

  public boolean isConnected() {
    return connected;
  }
}
