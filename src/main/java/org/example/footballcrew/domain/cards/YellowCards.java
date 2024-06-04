package org.example.footballcrew.domain.cards;

import lombok.ToString;

@ToString
public final class YellowCards extends Cards {
  public YellowCards(Integer quantity) {
    super(quantity, CardType.YELLOW);
  }
}
