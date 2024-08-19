package ae.cyberspeed.config.models;

import java.util.Map;

public record GameConfig(
    int columns,
    int rows,
    Map<String, Symbol> symbols,
    Probabilities probabilities,
    Map<String, WinCombination> winCombinations) {}
