package org.example.footballcrew.domain.policy;

public final class YellowCardFirstLevelPolicy implements CardPenaltyPolicy {
  @Override
  public Integer matches() {
    return 1;
  }
}
