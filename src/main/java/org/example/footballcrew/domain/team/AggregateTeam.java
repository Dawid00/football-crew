package org.example.footballcrew.domain.team;

import org.example.footballcrew.domain.footballer.CardsMinute;
import org.example.footballcrew.domain.footballer.Footballer;
import org.example.footballcrew.domain.footballer.Minute;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class AggregateTeam {

  private UUID id;
  private Set<Footballer> footballers = new HashSet<>();

  public AggregateTeam(Set<Footballer> footballers) {
    this.id = UUID.randomUUID();
    this.footballers = footballers;
  }

  private Set<Footballer> allFootballers() {
    return footballers.stream().collect(Collectors.toUnmodifiableSet());
  }

  public void matchHeld(Minute minute) {
    footballers.forEach(Footballer::handleMatch);
    serveMinute(minute);
    //TODO DP emit event to update current team
  }

  private void serveMinute(Minute minute) {
    minute.getCards().forEach(this::handleMinute);
  }

  private Optional<Footballer> findFootballer(UUID id) {
    return this.allFootballers().stream().filter(p -> p.getId().equals(id)).findFirst();
  }

  private void handleMinute(CardsMinute cardsMinute) {
    var player = findFootballer(cardsMinute.playerId());
    if (player.isPresent()) {
      switch (cardsMinute.card().getCardType()) {
        case YELLOW -> handleYellowCard(cardsMinute, player.get());
        case RED -> player.get().handleRedCard();
      }
    }
  }

  private void handleYellowCard(CardsMinute cardsMinute, Footballer player) {
    for (int i = 0; i < cardsMinute.card().getQuantity(); i++)
      player.handleYellowCard();
  }

}
