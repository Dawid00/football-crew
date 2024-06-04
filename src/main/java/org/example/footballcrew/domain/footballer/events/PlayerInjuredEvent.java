package org.example.footballcrew.domain.footballer.events;

import java.time.LocalDateTime;
import java.util.UUID;


public record PlayerInjuredEvent(
        UUID playerId,
        LocalDateTime occurredAt,
        LocalDateTime till
) implements PlayerEvent {

  public PlayerInjuredEvent(UUID playerId, LocalDateTime occurredAt, LocalDateTime till) {
    this.occurredAt = occurredAt;
    this.playerId = playerId;
    this.till = till;
  }

  public PlayerInjuredEvent(UUID playerId, LocalDateTime till) {
    this(playerId, till, LocalDateTime.now());
  }


  @Override
  public UUID playerId() {
    return playerId;
  }

  @Override
  public LocalDateTime occuredAt() {
    return occurredAt;
  }
}
