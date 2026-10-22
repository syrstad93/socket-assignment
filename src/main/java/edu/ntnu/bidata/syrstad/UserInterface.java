package edu.ntnu.bidata.syrstad;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
  private static Scanner scanner = new Scanner(System.in);
  private static RemoteControl remote;

  private UserInterface() throws InterruptedException {}

  private static int mainQuery() {
    for(int i = 0; i < 10; i++) {
      System.out.println(" ");
    }
    System.out.println("Welcome to SmartTv Remote Control");
    System.out.println("1. Power");
    System.out.println("2. Set Channel ->");
    System.out.println("3. Set Volume ->");
    System.out.println("4. Channel Up");
    System.out.println("5. Channel Down");
    System.out.println("6. Status");
    System.out.println("9. Quit");
    System.out.println("Enter your choice:");
    return scanner.nextInt();
  }

  public static void main(String[] args) {
    try {
     remote = new RemoteControl(65534);
    }  catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    boolean running = remote.getConnected();

    while (running) {
      int input = 0;
      try {
        input = mainQuery();
      } catch (InputMismatchException e) {
        System.out.println("Invalid input");
        scanner.nextLine();
        continue;
      }

      switch (input) {
        case 1:
          remote.command("Power");
          break;
        case 2:
          System.out.println("What Channel would you like to set?");
          remote.command("SET_CHANNEL " + scanner.nextInt());
          break;
        case 3:
          System.out.println("What Volume would you like to set?");
          int volume = scanner.nextInt();
          remote.command("SET_VOLUME " + volume);
          break;
        case 4:
          remote.command("CHANNEL_UP");
          break;
        case 5:
          remote.command("CHANNEL_DOWN");
          break;
        case 6:
          remote.command("STATUS");
          break;
        case 9:
          running = false;
          remote.command("QUIT");
          break;
        default:
          System.out.println("Invalid input");
          System.out.println("Please enter a valid choice");
          mainQuery();
          break;
      }
    }
  }
}
