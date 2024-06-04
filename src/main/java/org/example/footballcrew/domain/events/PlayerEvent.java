package org.example.footballcrew.domain.events;


import java.time.LocalDateTime;
import java.util.UUID;


public sealed interface PlayerEvent permits PlayerInjuredEvent, RedCardGivenEvent, YellowCardGivenEvent, MatchFinishedEvent{
  UUID playerId();
  LocalDateTime occuredAt();
}