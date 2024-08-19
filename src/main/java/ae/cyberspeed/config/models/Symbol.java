package ae.cyberspeed.config.models;

import ae.cyberspeed.config.constants.SymbolImpact;
import ae.cyberspeed.config.constants.SymbolType;
import java.math.BigDecimal;

public record Symbol(
    BigDecimal rewardMultiplier, SymbolType type, SymbolImpact impact, BigDecimal extra) {}
