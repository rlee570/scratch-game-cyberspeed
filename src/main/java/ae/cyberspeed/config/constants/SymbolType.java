package ae.cyberspeed.config.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SymbolType {
  @JsonProperty("standard")
  STANDARD,
  @JsonProperty("bonus")
  BONUS,
}
