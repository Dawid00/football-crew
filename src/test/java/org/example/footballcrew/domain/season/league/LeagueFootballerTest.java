package org.example.footballcrew.domain.season.league;

import org.example.footballcrew.domain.cards.RedCards;
import org.example.footballcrew.domain.cards.YellowCards;
import org.example.footballcrew.domain.season.league.LeagueFootballer;
import org.example.footballcrew.domain.season.league.specifications.YouthFootballerSpecification;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeagueFootballerTest {

  private static final LocalDate NOW = LocalDate.of(2024, 6, 11);
  private static final YouthFootballerSpecification YOUTH_SPEC_21_YEARS_OLD = new YouthFootballerSpecification(21);

  @Test
  public void shouldNotExcludedAfter1stYellowCard() {
    var leagueFootballer = new LeagueFootballer(UUID.randomUUID(), LocalDate.of(2000, 5, 25));
    leagueFootballer.handle(new YellowCards(1));
    assertFalse(leagueFootballer.isExcluded());
  }

  @Test
  public void shouldExcludedAfter4thYellowCard() {
    var leagueFootballer = new LeagueFootballer(UUID.randomUUID(), LocalDate.of(2000, 5, 25));

    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    assertEquals(1, leagueFootballer.getExclusions().size());
    assertTrue(leagueFootballer.isExcluded());
  }

  @Test
  public void shouldNotExcludedAfter4thYellowCardsAnd1MatchHeld() {
    var leagueFootballer = new LeagueFootballer(UUID.randomUUID(), LocalDate.of(2000, 5, 25));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));

    assertEquals(1, leagueFootballer.getExclusions().size());
    assertTrue(leagueFootballer.isExcluded());

    leagueFootballer.handleMatch();

    assertFalse(leagueFootballer.isExcluded());
  }

  @Test
  public void shouldExcludedAfter8thYellowCards() {
    var leagueFootballer = new LeagueFootballer(UUID.randomUUID(), LocalDate.of(2000, 5, 25));

    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    assertEquals(1, leagueFootballer.getExclusions().size());
    assertTrue(leagueFootballer.isExcluded());

    leagueFootballer.handleMatch();
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));

    assertTrue(leagueFootballer.isExcluded());
  }


  @Test
  public void shouldNotBeExcludedAfter2MatchHeld() {
    var leagueFootballer = new LeagueFootballer(UUID.randomUUID(), LocalDate.of(2000, 5, 25));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));

    assertEquals(1, leagueFootballer.getExclusions().size());
    assertTrue(leagueFootballer.isExcluded());

    leagueFootballer.handleMatch();
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    leagueFootballer.handle(new YellowCards(1));
    assertTrue(leagueFootballer.isExcluded());

    leagueFootballer.handleMatch();
    assertTrue(leagueFootballer.isExcluded());

    leagueFootballer.handleMatch();
    assertFalse(leagueFootballer.isExcluded());
  }

  @Test
  public void shouldBeNotBeExcludedAfterTwoMatches() {
    var leagueFootballer = new LeagueFootballer(UUID.randomUUID(), LocalDate.of(2000, 5, 25));
    leagueFootballer.handle(new RedCards(1));
    assertTrue(leagueFootballer.isExcluded());
    leagueFootballer.handleMatch();
    assertTrue(leagueFootballer.isExcluded());
    leagueFootballer.handleMatch();
    assertFalse(leagueFootballer.isExcluded());
  }

  @Test
  public void shouldExcludedAfterRedCard() {
    var leagueFootballer = new LeagueFootballer(UUID.randomUUID(), LocalDate.of(2000, 5, 25));
    leagueFootballer.handle(new RedCards(1));
    assertTrue(leagueFootballer.isExcluded());
    leagueFootballer.handleMatch();
    assertTrue(leagueFootballer.isExcluded());
    leagueFootballer.handleMatch();
    assertFalse(leagueFootballer.isExcluded());
  }

  @Test
  public void shouldReturnTrueWhen21YearsOld() {
    var leagueFootballer = new LeagueFootballer(UUID.randomUUID(), LocalDate.of(2003, 5, 25));
    assertTrue(leagueFootballer.isYouth(YOUTH_SPEC_21_YEARS_OLD, NOW));
  }

  @Test
  public void shouldReturnTrueWhen22YearsOld() {
    var leagueFootballer = new LeagueFootballer(UUID.randomUUID(), LocalDate.of(2002, 5, 25));
    assertFalse(leagueFootballer.isYouth(YOUTH_SPEC_21_YEARS_OLD, NOW));
  }
}
