package org.example.footballcrew.domain.policy;


import org.example.footballcrew.domain.cards.Cards;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

public interface YellowCardPolicy extends CardPenaltyPolicy {
  int YELLOW_FIRST_LEVEL = 8;
  int CARD_MULTIPLICATION = 4;

  static CardPenaltyPolicy chooseFor(Cards card) {
    if (card.getQuantity() == 0 || card.getQuantity() % CARD_MULTIPLICATION != 0) return new NoPenaltyPolicy();
    return
            Match(card.getQuantity()).of(
                    Case($(n -> n <= YELLOW_FIRST_LEVEL), new YellowCardFirstLevelPolicy()),
                    Case($(n -> n > YELLOW_FIRST_LEVEL), new YellowCardSecondLevelPolicy()),
                    Case($(), new NoPenaltyPolicy())
            );
  }
}
