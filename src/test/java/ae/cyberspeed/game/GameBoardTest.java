package ae.cyberspeed.game;

import ae.cyberspeed.config.utils.ConfigReader;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameBoardTest {

  @Test
  void createGameBoard() throws IOException, URISyntaxException {
    var reader = new ConfigReader();
    var config = reader.readConfig("src/test/resources/testConfig.json");

    var gameBoard = new GameBoard(config).createGameBoard();

    assertEquals(3,gameBoard.length);
  }
}
