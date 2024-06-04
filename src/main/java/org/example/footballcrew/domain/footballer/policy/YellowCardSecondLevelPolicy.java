package org.example.footballcrew.domain.footballer.policy;

public final class YellowCardSecondLevelPolicy implements CardPenaltyPolicy {
  @Override
  public Integer matches() {
    return 2;
  }
}
