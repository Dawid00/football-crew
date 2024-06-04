package org.example.footballcrew.domain.footballer.events;


import java.time.LocalDateTime;
import java.util.UUID;

public record YellowCardGivenEvent(
        UUID playerId,
        LocalDateTime occurredAt
) implements PlayerEvent {

  public YellowCardGivenEvent(UUID playerId, LocalDateTime occurredAt) {
    this.playerId = playerId;
    this.occurredAt = occurredAt;
  }

  public YellowCardGivenEvent(UUID playerId) {
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
