package org.example.footballcrew.domain.minute;

import org.example.footballcrew.domain.cards.CardType;
import org.example.footballcrew.domain.cards.Cards;

import java.util.Objects;
import java.util.UUID;

public record CardForPlayer(UUID playerId, Cards card) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CardForPlayer that = (CardForPlayer) o;
    return Objects.equals(card, that.card) && Objects.equals(playerId, that.playerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, card);
  }

  public void validate() {

    if (card.getCardType().equals(CardType.RED)) validateRed(card);
    if (card.getCardType().equals(CardType.YELLOW)) validateYellow(card);

  }

  private void validateRed(Cards card) {
    if (card.getCardType().equals(CardType.RED)) {
      if (card.getQuantity() > 1) {
        throw new RuntimeException("");
      }
    }
  }

  private void validateYellow(Cards card) {
    if (card.getCardType().equals(CardType.YELLOW)) {
      if (card.getQuantity() > 2) {
        throw new RuntimeException("");
      }
    }
  }
}
