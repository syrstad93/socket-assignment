package edu.ntnu.bidata.syrstad;

import java.io.IOException;

/**
 * Represents a remote control.
 * controls a smart TV.
 */
public class RemoteControl {
  private final Client socket;
  private boolean connected;

  /**
   * Creates an instance of RemoteControl.
   */
  public RemoteControl(int port) throws InterruptedException {
    socket = new Client(port);
    connected = socket.isConnected();
  }

  /**
   * Sends a command.
   */
  public void command(String command) {
    command = command.toUpperCase();
    String[] cmd = command.split(" ");
    boolean valid = false;
    for (Commands c : Commands.values()) {
      if (c .name().equals(cmd[0])) {
        valid = true;
      }
    }
    if (valid) {
      try {
        switch (cmd[0]) {
          case "SET_CHANNEL", "SET_VOLUME":
            if (cmd.length == 2) {
              socket.message(command);
            }
            break;
          case "QUIT":
            socket.message(command);
            socket.close();
            connected = false;
            break;
          default:
            socket.message(command);
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Gets the status if remote control is connected to a server.
   */
  public boolean getConnected() {
    return connected;
  }
}
