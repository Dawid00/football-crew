package org.example.footballcrew.domain.footballer.policy;

public final class NoPenaltyPolicy implements CardPenaltyPolicy {


  @Override
  public Integer matches() {
    return 0;
  }
}
