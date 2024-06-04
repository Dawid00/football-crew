package org.example.footballcrew.domain.cards;

import lombok.Getter;


@Getter
public class Cards {

  private final Integer quantity;
  private final CardType cardType;

  public Cards(Integer quantity, CardType cardType) {
    if (quantity < 0) {
      throw new RuntimeException("cards must not be negative ");
    }
    this.quantity = quantity;
    this.cardType = cardType;
  }

  public Cards increase() {
    return new Cards(quantity + 1, cardType);
  }

}

