import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import edu.ntnu.bidata.syrstad.RemoteControl;
import edu.ntnu.bidata.syrstad.Server;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class RemoteControlTest {

  @Test
  public void TestConnection() {
    Server server = server();
    assertDoesNotThrow(() -> new RemoteControl(60050));
  }

  private Server server() {
    try {
      return new Server(60050);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
