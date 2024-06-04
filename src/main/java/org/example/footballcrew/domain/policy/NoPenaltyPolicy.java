package org.example.footballcrew.domain.policy;

public final class NoPenaltyPolicy implements CardPenaltyPolicy {


  @Override
  public Integer matches() {
    return 0;
  }
}
