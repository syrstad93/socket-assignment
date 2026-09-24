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
        update();
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
      update();
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
      update();
    } else {
      System.out.println("Tv is off");
    }
  }

  public void setVolume(int v) {
    if (isOn) {
      if (1 <= v && v <= 100) {
        this.volume = v;
        update();
      } else {
        System.out.println("Volume must be between 1 and 100");
      }
    } else {
      System.out.println("Tv is off");
    }
  }

  public void toggleOn() {
    if (isOn) {
      isOn = false;
      setChannel(1);
    } else {
      isOn = true;
    }
    update();
  }

  public void update() {
    if (isOn) {
      System.out.println(
          "SmartTv is " + "on" + ", channel: " + channel + ", volume: " + volume);
      }  else {
      System.out.println("SmartTv is off");
      }
    }
}
