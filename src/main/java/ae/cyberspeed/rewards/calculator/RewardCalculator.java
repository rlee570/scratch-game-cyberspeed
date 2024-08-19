package ae.cyberspeed.rewards.calculator;

import ae.cyberspeed.config.constants.WinCombinationGroup;
import ae.cyberspeed.config.models.GameConfig;
import ae.cyberspeed.config.models.WinCombination;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RewardCalculator {
  private final HashMap<String, List<WinCombination>> winningCombinations = new HashMap<>();
  private final GameConfig gameConfig;
  private final HashMap<String,List<String>> appliedWinningCombinations = new HashMap<>();

  public RewardCalculator(GameConfig gameConfig) {
    this.gameConfig = gameConfig;
  }

  public BigDecimal calculateReward(BigDecimal bettingAmount, String bonusSymbol, String[][] matrix) {
    getWinningCombinations(gameConfig.winCombinations(),matrix);
    AtomicReference<BigDecimal> reward = new AtomicReference<>(BigDecimal.ZERO);
    winningCombinations.forEach((key, value) -> {
      BigDecimal rewardMultiplier = value.get(0).rewardMultiplier();
      BigDecimal symbolMultiplier = gameConfig.symbols().get(key).rewardMultiplier();

      reward.updateAndGet(
          x -> x.add(bettingAmount.multiply(rewardMultiplier.multiply(symbolMultiplier))));
    });

    return getBonus(reward.get(), bonusSymbol);
  }

  private void getWinningCombinations(
          Map<String, WinCombination> winCombinations, String[][] matrix) {
    winCombinations.entrySet().stream()
            .collect(Collectors.groupingBy(
                    entry -> entry.getValue().group(),
                    Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
            .forEach((group, entries) -> {
              switch (group) {
                case SAME_SYMBOLS -> calculateSameSymbols(matrix, entries);
                case VERTICALLY_LINEAR_SYMBOLS -> calculateVerticalSymbols();
                case HORIZONTALLY_LINEAR_SYMBOLS -> calculateHorizontalSymbols();
                case LTR_DIAGONALLY_LINEAR_SYMBOLS,
                     RTL_DIAGONALLY_LINEAR_SYMBOLS -> calculateDiagonally();
                default -> System.out.println("Unknown group: " + group);
              }
            });
  }

  private void calculateSameSymbols(
      String[][] matrix, Map<String, WinCombination> winCombinations) {
    var result = Arrays.stream(matrix)
        .flatMap(Arrays::stream)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(symbol -> 1)));

    winCombinations.forEach((key, winCombination) -> result.forEach((id, value) -> {
      if (value >= winCombination.count()) {
        if (winningCombinations.containsKey(id)
            && winningCombinations.get(id).get(0).count() < winCombination.count()) {
          winningCombinations.put(id, List.of(winCombination));
        } else if (!winningCombinations.containsKey(id)) {
          winningCombinations.put(id, List.of(winCombination));
          appliedWinningCombinations.put(id,List.of(key));
        }
      }
    }));
  }

  private void calculateHorizontalSymbols() {}

  private void calculateVerticalSymbols() {}

  private void calculateDiagonally() {}

  private BigDecimal getBonus(BigDecimal reward, String bonusSymbol) {
    var symbol = gameConfig.symbols().get(bonusSymbol);

    return switch (symbol.impact()) {
      case MULTIPLY_REWARD -> reward.multiply(symbol.rewardMultiplier());
      case EXTRA_BONUS -> reward.add(symbol.extra());
      case MISS -> BigDecimal.ZERO;
    };
  }

  public Map<String, List<String>> getAppliedWinningCombinations() {
    return appliedWinningCombinations;
  }
}
