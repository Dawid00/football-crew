package org.example.footballcrew.domain.minute;

import java.util.List;
import java.util.Set;

public class Minute {

  private Set<CardForPlayer> cards;

  public Minute(Set<CardForPlayer> cards) {
    cards.forEach(CardForPlayer::validate);
    this.cards = cards;
  }

  public List<CardForPlayer> getCards() {
    return cards.stream().toList();
  }
}
