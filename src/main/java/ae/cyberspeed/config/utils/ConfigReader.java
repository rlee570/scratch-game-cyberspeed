package ae.cyberspeed.config.utils;

import ae.cyberspeed.config.models.GameConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigReader {

  private final ObjectMapper mapper;

  public ConfigReader() {
    mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  }

  /**
   * This is a very naive approach and assumes that the config file is present and convertable
   * into the specified types.
   * @param filePath
   * @return A GameConfig object with all associated configuration added to it
   * @throws IOException
   * @throws URISyntaxException
   */
  public GameConfig readConfig(String filePath) throws IOException, URISyntaxException {
    Path paths = Paths.get(filePath);
    String configData = Files.readString(paths);

    return mapper.readValue(configData, GameConfig.class);
  }
}
