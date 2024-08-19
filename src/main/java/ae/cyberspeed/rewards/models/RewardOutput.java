package ae.cyberspeed.rewards.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record RewardOutput(
        String[][] matrix,
        BigDecimal reward,
        Map<String, List<String>> appliedWinningCombinations,
        String appliedBonusSymbol) {}
