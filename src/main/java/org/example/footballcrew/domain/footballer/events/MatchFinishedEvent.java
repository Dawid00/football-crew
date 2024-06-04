package org.example.footballcrew.domain.footballer.events;


import java.time.LocalDateTime;
import java.util.UUID;

public record MatchFinishedEvent(UUID playerId, LocalDateTime occurredAt) implements PlayerEvent {

  public MatchFinishedEvent(UUID playerId, LocalDateTime occurredAt) {
    this.playerId = playerId;
    this.occurredAt = occurredAt;
  }

  public MatchFinishedEvent(UUID playerId) {
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
