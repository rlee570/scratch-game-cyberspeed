package ae.cyberspeed.config.models;

import ae.cyberspeed.config.constants.WinCombinationGroup;
import ae.cyberspeed.config.constants.WinCombinationWhen;
import java.math.BigDecimal;
import java.util.List;

public record WinCombination(
    BigDecimal rewardMultiplier,
    WinCombinationWhen when,
    int count,
    WinCombinationGroup group,
    List<List<String>> coveredAreas) {}
