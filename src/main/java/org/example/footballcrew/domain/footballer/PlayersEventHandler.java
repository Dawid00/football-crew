package org.example.footballcrew.domain.footballer;

import lombok.AllArgsConstructor;
import org.example.footballcrew.domain.footballer.events.MatchFinishedEvent;
import org.example.footballcrew.domain.footballer.events.PlayerInjuredEvent;
import org.example.footballcrew.domain.footballer.events.RedCardGivenEvent;
import org.example.footballcrew.domain.footballer.events.YellowCardGivenEvent;
import org.example.footballcrew.domain.footballer.ports.FootballerDatabase;

@AllArgsConstructor
public class PlayersEventHandler {

  private final FootballerDatabase database;

  public void handle(YellowCardGivenEvent event) {
    var player = database.getFootballerById(event.playerId());
    player.handleYellowCard();
  }

  public void handle(RedCardGivenEvent event) {
    var player = database.getFootballerById(event.playerId());
    player.handleRedCard();
  }

  public void handle(MatchFinishedEvent event) {
    var player = database.getFootballerById(event.playerId());
    player.handleMatch();
  }

  public void handle(PlayerInjuredEvent event) {
    var player = database.getFootballerById(event.playerId());
    player.handleInjury(event.till());
  }

}
