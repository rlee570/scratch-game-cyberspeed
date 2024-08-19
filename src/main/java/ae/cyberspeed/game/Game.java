package ae.cyberspeed.game;

import ae.cyberspeed.rewards.calculator.RewardCalculator;
import ae.cyberspeed.rewards.models.RewardOutput;
import java.math.BigDecimal;

public class Game {
  private final GameBoard gameBoard;
  private final RewardCalculator rewardCalculator;

  public Game(RewardCalculator rewardCalculator, GameBoard gameBoard) {
    this.rewardCalculator = rewardCalculator;
    this.gameBoard = gameBoard;
  }

  public RewardOutput playGame(BigDecimal bettingAmount) {
    var matrix = gameBoard.createGameBoard();
    var reward = rewardCalculator.calculateReward(bettingAmount, gameBoard.getBonusSymbol(),matrix);

    return new RewardOutput(
        matrix,
        reward,
        rewardCalculator.getAppliedWinningCombinations(),
        gameBoard.getBonusSymbol());
  }
}
