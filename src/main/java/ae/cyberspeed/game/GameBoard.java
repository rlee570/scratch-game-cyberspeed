package ae.cyberspeed.game;

import ae.cyberspeed.config.models.GameConfig;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class GameBoard {
  private final GameConfig gameConfig;
  private final Random random = new Random();
  private String bonusSymbol;

  public GameBoard(GameConfig gameConfig) {
    this.gameConfig = gameConfig;
  }

  public String[][] createGameBoard() {
    String[][] matrix = new String[gameConfig.rows()][gameConfig.columns()];

    gameConfig
        .probabilities()
        .standardSymbols()
        .forEach((standardSymbol) -> matrix[standardSymbol.row()][standardSymbol.column()] =
            getRandomSymbol(standardSymbol.symbols()));

    bonusSymbol = getRandomSymbol(gameConfig.probabilities().bonusSymbols().symbols());
    matrix[random.nextInt(matrix.length)][random.nextInt(matrix[0].length)] = bonusSymbol;

    return matrix;
  }

  /**
   * Uses the sum of all probabilities then does an integer calculation to ascertain which symbol should be chosen
   * Random does not include the value itself so +1 has to be added
   * @param symbols the map of symbol to probability
   * @return Symbol to put into the slot of the matrix
   */
  private String getRandomSymbol(Map<String, Integer> symbols) {
    var totalSum = symbols.values().stream().mapToInt(Integer::intValue).sum();

    var target = random.nextInt(totalSum) + 1;
    var total = 0;

    for (Map.Entry<String, Integer> symbol : symbols.entrySet()) {
      total += symbol.getValue();
      if (total >= target) {
        return symbol.getKey();
      }
    }

    throw new RuntimeException("This shouldn't happen");
  }

  public String getBonusSymbol() {
    return bonusSymbol;
  }
}
