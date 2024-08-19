package ae.cyberspeed.config.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Test;

class ConfigReaderTest {

  @Test
  void readConfig() throws IOException, URISyntaxException {
    var reader = new ConfigReader();
    var config = reader.readConfig("src/test/resources/testConfig.json");

    assertEquals(config.columns(), 3);
    assertEquals(config.rows(), 3);
    assertEquals(config.symbols().size(), 11);
    assertEquals(config.probabilities().standardSymbols().size(), 9);
    assertNotNull(config.probabilities().bonusSymbols());
    assertEquals(config.winCombinations().size(), 11);
  }
}
