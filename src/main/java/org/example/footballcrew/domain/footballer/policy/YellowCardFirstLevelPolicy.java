package org.example.footballcrew.domain.footballer.policy;

public final class YellowCardFirstLevelPolicy implements CardPenaltyPolicy {
  @Override
  public Integer matches() {
    return 1;
  }
}
