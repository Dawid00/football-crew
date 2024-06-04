package org.example.footballcrew.domain.policy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class RedCardPolicy implements CardPenaltyPolicy {

  private final Integer quantity;

  @Override
  public Integer matches() {
    return quantity;
  }
}
