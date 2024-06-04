package org.example.footballcrew.domain.cards;

import lombok.ToString;

@ToString
public final class RedCards extends Cards {
  public RedCards(Integer quantity) {
    super(quantity, CardType.RED);
  }
}
