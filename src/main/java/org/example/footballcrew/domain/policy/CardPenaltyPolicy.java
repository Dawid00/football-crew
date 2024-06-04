package org.example.footballcrew.domain.policy;

import org.example.footballcrew.domain.cards.CardType;
import org.example.footballcrew.domain.cards.Cards;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

@FunctionalInterface
public interface CardPenaltyPolicy extends PenaltyPolicy {

  Integer matches();

  static CardPenaltyPolicy chooseFor(Cards card) {
    return chooseFor(card, 2);
  }

  static CardPenaltyPolicy chooseFor(Cards card, Integer redCardMatches) {
    return Match(card.getCardType())
            .of(
                    Case($(CardType.RED), new RedCardPolicy(redCardMatches)),
                    Case($(CardType.YELLOW), YellowCardPolicy.chooseFor(card)),
                    Case($(), new NoPenaltyPolicy())
            );
  }

}
