package org.example.footballcrew.domain.policy;

public final class YellowCardSecondLevelPolicy implements CardPenaltyPolicy {
  @Override
  public Integer matches() {
    return 2;
  }
}
