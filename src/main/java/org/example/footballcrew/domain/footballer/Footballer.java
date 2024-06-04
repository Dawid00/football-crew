package org.example.footballcrew.domain.footballer;

import lombok.ToString;
import org.example.footballcrew.availability.CardType;
import org.example.footballcrew.domain.footballer.exclusion.Exclusion;
import org.example.footballcrew.domain.footballer.exclusion.TimeExclusion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ToString
public class Footballer {

  private UUID id;
  private Card yellowCards;
  private Card redCards;
  private List<Exclusion> exclusions = new ArrayList<>();
  private LocalDateTime bornDate;

  public List<Exclusion> getExclusions() {
    return this.exclusions;
  }

  public Footballer(UUID id, LocalDateTime bornDate) {
    this.bornDate = bornDate;
    this.id = id;
    this.redCards = new Card(0, CardType.RED);
    this.yellowCards = new Card(0, CardType.YELLOW);
  }

  public Footballer withExclusions(List<Exclusion> exclusions) {
    if(exclusions==null){
    throw new RuntimeException("TODO");
    }
    return new Footballer(this.id, this.bornDate, this.yellowCards, this.redCards, exclusions);
  }

  public Footballer(UUID id, LocalDateTime bornDate, Card yellowCards, Card redCards, List<Exclusion> exclusions) {
    this.bornDate = bornDate;
    this.id = id;
    this.yellowCards = yellowCards;
    this.redCards = redCards;
    this.exclusions = exclusions;
  }

  public Footballer(UUID id, LocalDateTime bornDate, Card yellowCards, Card redCards) {
    this.bornDate = bornDate;
    this.id = id;
    this.yellowCards = yellowCards;
    this.redCards = redCards;
  }

  public UUID getId() {
    return id;
  }

  public void handleYellowCard() {
    this.yellowCards = yellowCards.increase();
    handleExclude(CardExclusionResolver.resolveExclusion(yellowCards));
  }

  public void handleRedCard() {
    this.redCards = redCards.increase();
    handleExclude(CardExclusionResolver.resolveExclusion(redCards));
  }

  public void handleMatch() {
    if (this.exclusions != null && !exclusions.isEmpty()) {
      exclusions.forEach(Exclusion::updateAfterMatch);
//    exclusions = exclusions.stream().filter(Exclusion::active).collect(Collectors.toList());
    }
  }

  public Integer getYellowCardsQuantity() {
    return this.yellowCards.getQuantity();
  }

  public Integer getRedCardsQuantity() {
    return this.redCards.getQuantity();
  }

  public void handleExclude(Optional<Exclusion> exclusion) {
    exclusion.ifPresent(value -> this.exclusions.add(value));
  }

  public boolean isExcluded() {
    return this.exclusions.stream().anyMatch(Exclusion::active);
  }


  public boolean isYouth(YouthFootballerSpecification youthFootballerSpecification) {
    return youthFootballerSpecification.isSatisfiedBy(this);
  }

  public void handleInjury(LocalDateTime injuredTill) {
    handleExclude(Optional.of(new TimeExclusion(UUID.randomUUID(), injuredTill)));
  }

  public LocalDateTime getBornDate() {
    return this.bornDate;
  }

//  public void setExclusions(List<Exclusion> exclusions) {
//    this.exclusions = exclusions;
//  }
}

