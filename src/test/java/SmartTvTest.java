import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.bidata.syrstad.SmartTv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SmartTvTest {
  private SmartTv tv;

  @BeforeEach
  void setUp() {
    tv = new SmartTv();
  }

  @Test
  void newTvIsOff() {
    assertEquals("SmartTv is off", tv.update());
  }

  @Test
  void toggleOnTurnsTvOn() {
    tv.toggleOn();
    assertEquals("SmartTv is on, channel: 1, volume: 50", tv.update());
  }

  @Test
  void toggleTwiceTurnsTvOff() {
    tv.toggleOn();
    tv.toggleOn();
    assertEquals("SmartTv is off", tv.update());
  }

  @Test
  void powerOffResetsChannel() {
    tv.toggleOn();
    tv.setChannel(3);
    tv.toggleOn(); // off
    tv.toggleOn(); // on again
    assertEquals("SmartTv is on, channel: 1, volume: 50", tv.update());
  }
}
