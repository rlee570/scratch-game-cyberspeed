package ae.cyberspeed.config.models;

import java.util.Map;

public record StandardSymbol(int column, int row, Map<String, Integer> symbols) {}
