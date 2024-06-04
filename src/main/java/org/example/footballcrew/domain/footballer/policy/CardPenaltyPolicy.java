package org.example.footballcrew.domain.footballer.policy;

import org.example.footballcrew.availability.CardType;
import org.example.footballcrew.domain.footballer.Card;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

@FunctionalInterface
public interface CardPenaltyPolicy extends PenaltyPolicy {

  Integer matches();

  static CardPenaltyPolicy chooseFor(Card card) {
    return chooseFor(card, 2);
  }

  static CardPenaltyPolicy chooseFor(Card card, Integer redCardMatches) {
    return Match(card.getCardType())
            .of(
                    Case($(CardType.RED), new RedCardPolicy(redCardMatches)),
                    Case($(CardType.YELLOW), YellowCardPolicy.chooseFor(card)),
                    Case($(), new NoPenaltyPolicy())
            );
  }

}
