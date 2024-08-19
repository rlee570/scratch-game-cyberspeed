package ae.cyberspeed.config.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WinCombinationWhen {
  @JsonProperty("same_symbols")
  SAME_SYMBOLS,
  @JsonProperty("linear_symbols")
  LINEAR_SYMBOLS
}
