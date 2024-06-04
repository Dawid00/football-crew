package org.example.footballcrew.domain.footballer.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record RedCardGivenEvent(
        UUID playerId,
        LocalDateTime occurredAt
) implements PlayerEvent {

  public RedCardGivenEvent(UUID playerId, LocalDateTime occurredAt) {
    this.playerId = playerId;
    this.occurredAt = occurredAt;
  }

  public RedCardGivenEvent(UUID playerId) {
    this(playerId, LocalDateTime.now());
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
