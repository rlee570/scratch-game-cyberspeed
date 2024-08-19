package ae.cyberspeed.rewards;

import ae.cyberspeed.config.utils.ConfigReader;
import ae.cyberspeed.game.GameBoard;
import ae.cyberspeed.rewards.calculator.RewardCalculator;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardCalculatorTest {

  @Test
  void calculateReward() throws IOException, URISyntaxException {
    var reader = new ConfigReader();
    var config = reader.readConfig("src/test/resources/riggedConfig.json");
    var gameBoard = new GameBoard(config).createGameBoard();
    var calculator = new RewardCalculator(config);

    var reward = calculator.calculateReward(new BigDecimal("1000"),"10x", gameBoard);

    assertEquals(new BigDecimal("500000"),reward);
  }
}
