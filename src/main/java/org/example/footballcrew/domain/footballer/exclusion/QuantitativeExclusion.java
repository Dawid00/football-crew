package org.example.footballcrew.domain.footballer.exclusion;

import java.util.UUID;

public class QuantitativeExclusion extends Exclusion {

  private Integer quantity;

  public QuantitativeExclusion(UUID id, Integer quantity) {
    super(id);
    this.quantity = quantity;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  @Override
  public Boolean active() {
    return this.quantity > 0;
  }

  @Override
  public void updateAfterMatch() {
    this.decrease();
  }

  public void decrease() {
    if (this.quantity > 0) {
      this.quantity = this.quantity - 1;
    }
  }
  @Override
  public ExclusionType type() {
    return ExclusionType.QUANTITATIVE;
  }
}
