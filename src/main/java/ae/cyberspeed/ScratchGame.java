package ae.cyberspeed;

import ae.cyberspeed.config.models.GameConfig;
import ae.cyberspeed.config.utils.ConfigReader;
import ae.cyberspeed.game.Game;
import ae.cyberspeed.game.GameBoard;
import ae.cyberspeed.rewards.calculator.RewardCalculator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class ScratchGame {
  private static final String USAGE =
      "Usage: scratch-game-0.0.1.jar --config <path_to_file> --betting-amount <positive integer amount>";

  public static void main(String[] args) throws JsonProcessingException {
    parseInput(args);
  }

  private static void parseInput(String[] args) throws JsonProcessingException {
    if (args.length != 4) {
      System.err.println(USAGE);
    }

    var keysCheck = args[0].equals("--config") && args[2].equals("--betting-amount");
    var bettingAmount = new BigDecimal(args[3]);
    var filePath = args[1];

    validateCommandInput(keysCheck, bettingAmount, filePath);

    var gameConfig = readConfig(filePath);
    var calculator = new RewardCalculator(gameConfig);
    var gameBoard = new GameBoard(gameConfig);

    var game = new Game(calculator, gameBoard);
    var output = game.playGame(bettingAmount);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    System.out.println(objectMapper.writeValueAsString(output));
  }

  private static void validateCommandInput(
      boolean keysCheck, BigDecimal bettingAmount, String filePath) {
    if (keysCheck) {
      if (filePath.isEmpty()) {
        System.err.println("Empty config file path");
        throw new RuntimeException("Invalid betting amount specified");
      }

      if (bettingAmount.signum() != 1) {
        System.err.println("Invalid betting amount specified");
        throw new RuntimeException("Invalid betting amount specified");
      }
    } else {
      System.err.println(USAGE);
      throw new RuntimeException("Invalid betting amount specified");
    }
  }

  private static GameConfig readConfig(String filePath) {
    ConfigReader configReader = new ConfigReader();
    try {
      return configReader.readConfig(filePath);
    } catch (IOException | URISyntaxException e) {
      System.err.println("Unable to locate file");
      throw new RuntimeException(e);
    }
  }
}
