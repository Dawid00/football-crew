package org.example.footballcrew.domain.cards;

import org.example.footballcrew.domain.exclusion.Exclusion;
import org.example.footballcrew.domain.exclusion.QuantitativeExclusion;
import org.example.footballcrew.domain.policy.CardPenaltyPolicy;
import org.example.footballcrew.domain.policy.NoPenaltyPolicy;

import java.util.Optional;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

class CardExclusionResolver {
  static Optional<Exclusion> resolveExclusion(Cards card) {
    CardPenaltyPolicy policy = CardPenaltyPolicy.chooseFor(card);
    return Match(policy).of(
            Case($(instanceOf(NoPenaltyPolicy.class)), Optional.empty()),
            Case($(instanceOf(CardPenaltyPolicy.class)), Optional.of(new QuantitativeExclusion(UUID.randomUUID(), policy.matches()))),
            Case($(), Optional.empty())
    );
  }
}
