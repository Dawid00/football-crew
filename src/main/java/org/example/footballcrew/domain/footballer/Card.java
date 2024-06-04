package org.example.footballcrew.domain.footballer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.footballcrew.availability.CardType;


@AllArgsConstructor
@Getter
public final class Card {

  private final Integer quantity;
  private final CardType cardType;

  public Card increase() {
    return new Card(quantity + 1, cardType);
  }

}

