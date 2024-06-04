package org.example.footballcrew.domain.footballer.exclusion;

import java.util.UUID;

public abstract class Exclusion {
  public Exclusion(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  private UUID id;

  public abstract Boolean active();

  public abstract void updateAfterMatch();

  abstract ExclusionType type();
}