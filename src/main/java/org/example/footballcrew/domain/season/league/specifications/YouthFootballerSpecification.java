package org.example.footballcrew.domain.season.league.specifications;

import java.time.LocalDate;
import java.time.Period;

public class YouthFootballerSpecification  {

  public YouthFootballerSpecification(Integer youthAge) {
    this.youthAge = youthAge;
  }

  private final Integer youthAge;

  public boolean isSatisfiedBy(LocalDate bornDate, LocalDate now) {
    Period p = Period.between(bornDate, now);
    return p.getYears() <= youthAge;
  }
}
