package ae.cyberspeed.game;

import ae.cyberspeed.config.utils.ConfigReader;
import ae.cyberspeed.rewards.calculator.RewardCalculator;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

  @Test
  void playGame() throws IOException, URISyntaxException {
    var reader = new ConfigReader();
    var config = reader.readConfig("src/test/resources/riggedConfig.json");
    var calculator = new RewardCalculator(config);
    var gameBoard = new GameBoard(config);

    var game = new Game( calculator, gameBoard);
    var rewardOutput = game.playGame(new BigDecimal("100"));

    assertEquals(new BigDecimal("50000"),rewardOutput.reward());
  }
}
