package org.example.footballcrew.domain.footballer;

import org.example.footballcrew.catalog.injury.Injury;
import org.example.footballcrew.catalog.injury.InjuryStatus;
import org.example.footballcrew.domain.season.league.specifications.YouthFootballerSpecification;

import java.time.LocalDate;
import java.util.UUID;

public interface Footballer {

  LocalDate getBornDate();

  UUID getId();

  boolean injured(LocalDate at);

  boolean injured();

  void getInjured(Injury injury);

  void changeInjury(UUID injuryId, LocalDate till, InjuryStatus status);

  default Boolean isYouth(YouthFootballerSpecification youthFootballerSpecification, LocalDate now) {
    return youthFootballerSpecification.isSatisfiedBy(getBornDate(), now);
  }

}
