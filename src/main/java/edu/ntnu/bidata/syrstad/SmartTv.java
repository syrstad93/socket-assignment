package edu.ntnu.bidata.syrstad;

/**
 * Represents a smart TV.
 */
public class SmartTv {
  private int channel;
  private int volume;
  private boolean isOn;

  public SmartTv() {
    this.channel = 1;
    this.volume = 50;
    this.isOn = false;
  }

  public void setChannel(int c) {
    if (isOn) {
      if (1 <= c && c <= 3) {
        this.channel = c;
        System.out.println(update());
      } else {
        System.out.println("Channel must be between 1 and 3");
      }
    } else {
      System.out.println("Tv is off");
    }
  }

  public void channelUp() {
    if (isOn) {
      if (channel == 3) {
        this.channel = 1;
      } else {
        this.channel += 1;
      }
      System.out.println(update());
    } else {
      System.out.println("Tv is off");
    }
  }

  public void channelDown() {
    if (isOn) {
      if (channel == 1) {
        this.channel = 3;
      } else {
        this.channel -= 1;
      }
      System.out.println(update());
    } else {
      System.out.println("Tv is off");
    }
  }

  public void setVolume(int v) {
    if (isOn) {
      if (1 <= v && v <= 100) {
        this.volume = v;
        System.out.println(update());
      } else {
        System.out.println("Volume must be between 1 and 100");
      }
    } else {
      System.out.println("Tv is off");
    }
  }

  public void toggleOn() {
    if (isOn) {
      setChannel(1);
      isOn = false;
    } else {
      isOn = true;
    }
    System.out.println(update());
  }

  public String update() {
    if (isOn) {
      return "SmartTv is " + "on" + ", channel: " + channel + ", volume: " + volume;
      }  else {
      return "SmartTv is off";
      }
    }
}
