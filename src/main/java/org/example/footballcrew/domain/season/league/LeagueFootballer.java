package org.example.footballcrew.domain.season.league;

import lombok.Getter;
import lombok.ToString;
import org.example.footballcrew.domain.cards.Cards;
import org.example.footballcrew.catalog.injury.Injury;
import org.example.footballcrew.catalog.injury.InjuryStatus;
import org.example.footballcrew.domain.exclusion.Exclusion;
import org.example.footballcrew.domain.exclusion.QuantitativeExclusion;
import org.example.footballcrew.domain.cards.RedCards;
import org.example.footballcrew.domain.cards.YellowCards;
import org.example.footballcrew.domain.season.league.specifications.YouthFootballerSpecification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@ToString
class LeagueFootballer {//implements Footballer {

  private UUID footballerId;
  private List<Exclusion> exclusions = new ArrayList<>();
  private Cards yellowCards;
  private Cards redCards;
  private boolean injured;
  private LocalDate bornDate;

  public LeagueFootballer(UUID footballerId, LocalDate bornDate ) {
    validateAge(bornDate);
    this.footballerId = footballerId;
    this.bornDate = bornDate;
    this.yellowCards = new YellowCards(0);
    this.redCards = new RedCards(0);
  }

  public LeagueFootballer(List<Exclusion> exclusions, YellowCards yellowCards, RedCards redCards, UUID footballerId, LocalDate bornDate) {
    this.exclusions = exclusions;
    this.yellowCards = yellowCards;
    this.redCards = redCards;
    this.footballerId = footballerId;
    this.bornDate = bornDate;
  }

  Boolean isYouth(YouthFootballerSpecification youthFootballerSpecification, LocalDate now) {
    return youthFootballerSpecification.isSatisfiedBy(this.bornDate, now);
  }

  //  @Override
  UUID getId() {
    return this.footballerId;
  }

  //  @Override
  boolean injured(LocalDate at) {
    return injured;
  }

  //  @Override
  boolean injured() {
    return injured;
  }

  //  @Override
  void getInjured(Injury injury) {
    throw new RuntimeException();//TODO
  }

  //  @Override
  void changeInjury(UUID injuryId, LocalDate till, InjuryStatus status) {
    throw new RuntimeException(); ///TODO
  }

  void recovered() {
    this.injured = false;
  }


  void handle(Exclusion exclusion) {
    this.exclusions.add(exclusion);
  }

  void handle(Cards card) {
    switch (card.getCardType()) {
      case RED -> handleRedCard();
      case YELLOW -> handleYellowCard();
    }
  }

  void handleMatch() {
    if (this.exclusions != null && !exclusions.isEmpty()) {
      exclusions.forEach(Exclusion::updateAfterMatch);
    }
  }

  Integer getYellowCardsQuantity() {
    return this.yellowCards.getQuantity();
  }

  Integer getRedCardsQuantity() {
    return this.redCards.getQuantity();
  }

  boolean isExcluded() {
    return this.exclusions.stream().anyMatch(Exclusion::active);
  }

  private void handleYellowCard() {
    this.yellowCards = yellowCards.increase();
    if (this.yellowCards.getQuantity() > 0 && this.yellowCards.getQuantity() % 4 == 0) {
      handleExclude(new QuantitativeExclusion(1));
    }
    if (this.yellowCards.getQuantity() >= 8 && this.yellowCards.getQuantity() % 4 == 0) {
      handleExclude(new QuantitativeExclusion(2));
    }
  }

  private void changeExclusion(UUID exclusionId, Integer quantity) {
    var ex = this.exclusions.stream().filter(e -> e.getId().equals(exclusionId)).findFirst();
    //TODO DP
  }

  private void handleRedCard() {
    this.redCards = redCards.increase();
    handleExclude(new QuantitativeExclusion(2));
  }

  private static void validateAge(LocalDate bornDate) {
    if (LocalDate.now().getYear() - bornDate.getYear() < 15) {
      throw new RuntimeException("Footballer must be minimum 15 years old.");
    }
  }

  private void handleExclude(QuantitativeExclusion quantitativeExclusion) {
    this.exclusions.add(quantitativeExclusion);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LeagueFootballer that = (LeagueFootballer) o;
    return Objects.equals(footballerId, that.footballerId) && Objects.equals(bornDate, that.bornDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(footballerId, bornDate);
  }
}
