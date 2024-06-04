package org.example.footballcrew.domain.exclusion;

import java.time.LocalDateTime;
import java.util.UUID;

public class TimeExclusion extends Exclusion {

  private LocalDateTime till;

  public TimeExclusion(UUID id, LocalDateTime till) {
    super(id);
    this.till = till;
  }

  @Override
  public Boolean active() {
    return LocalDateTime.now().isAfter(till);
  }


  public LocalDateTime getTill() {
    return till;
  }

  @Override
  public void updateAfterMatch() {

  }

  @Override
  public ExclusionType type() {
    return ExclusionType.TIME;
  }
}
