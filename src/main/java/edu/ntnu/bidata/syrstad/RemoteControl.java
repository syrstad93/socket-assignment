package edu.ntnu.bidata.syrstad;

import java.io.IOException;

public class RemoteControl {
  private final Client socket;
  private boolean connected;

  public RemoteControl() throws InterruptedException {
    socket = new Client(65534);
    connected = socket.isConnected();
  }

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

  public boolean getConnected() {
    return connected;
  }
}
