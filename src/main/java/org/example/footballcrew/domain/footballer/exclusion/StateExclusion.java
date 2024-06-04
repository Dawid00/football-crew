package org.example.footballcrew.domain.footballer.exclusion;

import java.util.UUID;

public class StateExclusion extends Exclusion {
  private Boolean active;

  public StateExclusion(UUID id) {
    super(id);
    this.active = true;
  }

  public StateExclusion(UUID id, Boolean active) {
    super(id);
    this.active = active;
  }

  @Override
  public Boolean active() {
    return this.active;
  }

  @Override
  public void updateAfterMatch() {

  }

  @Override
  public ExclusionType type() {
    return ExclusionType.STATE;
  }

  public void toggle() {
    this.active = !this.active;
  }
}
