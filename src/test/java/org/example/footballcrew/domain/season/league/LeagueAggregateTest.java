package org.example.footballcrew.domain.season.league;

import org.example.footballcrew.domain.cards.RedCards;
import org.example.footballcrew.domain.cards.YellowCards;
import org.example.footballcrew.domain.minute.CardForPlayer;
import org.example.footballcrew.domain.minute.Minute;
import org.example.footballcrew.domain.season.league.exception.LeagueException;
import org.example.footballcrew.domain.season.league.specifications.YouthFootballerSpecification;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.example.footballcrew.domain.match.MatchStatus.CANCELLED;
import static org.example.footballcrew.domain.match.MatchStatus.PLANNED;
import static org.example.footballcrew.domain.match.MatchStatus.PLAYED;
import static org.example.footballcrew.domain.match.MatchStatus.SUBMITTED;
import static org.example.footballcrew.domain.match.MatchStatus.TEAM_ASSIGNED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertNull;

public class LeagueAggregateTest {
  private static final LocalDateTime NOW = LocalDateTime.of(2024, 5, 25, 20, 0, 0);

  @Test
  public void shouldReturnAssignedTeam() {
    LeagueAggregate league = league();

    league.assignTeamForGame(1L, starting(), subs(), Collections.emptyList());

    assertEquals(TEAM_ASSIGNED, league.allMatches().getFirst().state());
    assertTrue(new HashSet<>(starting()).containsAll(league.allMatches().get(0).getStarting()));
    assertTrue(new HashSet<>(subs()).containsAll(league.allMatches().get(0).getSubs()));
  }

  @Test
  public void shouldReturnPlannedWhenAssignedTeamAgain() {
    var league = league();

    league.assignTeamForGame(1L, starting(), subs(), Collections.emptyList());
    league.submitMatch(1L);
    league.assignTeamForGame(1L, starting(), subs(), Collections.emptyList());

    assertEquals(TEAM_ASSIGNED, (league.find(1L).state()));
    assertTrue(new HashSet<>(starting()).containsAll(league.find(1L).getStarting()));
  }

  @Test
  public void shouldReturnSubmitted() {
    var league = league();
    league.assignTeamForGame(1L, starting(), subs(), Collections.emptyList());
    league.submitMatch(1L);

    assertEquals(SUBMITTED, (league.find(1L).state()));
    assertTrue(new HashSet<>(starting()).containsAll(league.find(1L).getStarting()));
  }

  @Test
  public void shouldReturnSubmitted2() {
    var league = league();
    league.planMatch(new LeagueMatch(2L, NOW.plusDays(10)));
    league.assignTeamForGame(1L, starting(), subs(), Collections.emptyList());
    league.submitMatch(1L);
    league.assignTeamForGame(1L, starting(), subs(), Collections.emptyList());
    league.assignTeamForGame(2L, starting(), subs(), Collections.emptyList());

    assertThrows(LeagueException.class, () -> league.submitMatch(2L));
  }

  @Test
  public void shouldReturnPlayedAndSetMinuteWhenPlayed() {
    var minute = new Minute(Set.of(new CardForPlayer(UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ba"), new YellowCards(1))));
    var league = league();

    league.assignTeamForGame(1L, starting(), subs(), Collections.emptyList());
    var leagueMatch = league.allMatches().get(0);
    leagueMatch.submit(new YouthFootballerSpecification(21));
    leagueMatch.played(minute);

    assertEquals(PLAYED, leagueMatch.state());
    assertEquals(minute, leagueMatch.getMinute());
  }

  @Test
  public void shouldThrowExceptionWhenMatchNotSubmitted() {
    var minute = new Minute(Set.of(new CardForPlayer(UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ba"), new YellowCards(1))));
    var league = league();
    league.assignTeamForGame(1L, starting(), subs(), Collections.emptyList());

    assertThrows(RuntimeException.class, () -> league.matchPlayed(1L, minute));
    assertNull("", league.allMatches().get(0).getMinute());
  }

  @Test
  public void shouldReturnPlanAnd2Matches() {
    var league = league();
    assertEquals(league.allMatches().size(), 1);

    league.planMatch(new LeagueMatch(2L, NOW.plusDays(10)));

    assertEquals(league.allMatches().size(), 2);
    assertEquals(league.find(2L).at(), NOW.plusDays(10));
    assertEquals(league.find(2L).state(), PLANNED);
  }

  @Test
  public void findNextMatch() {
    var league = league();
    league.planMatch(new LeagueMatch(2L, NOW.plusDays(10)));
    league.planMatch(new LeagueMatch(3L, NOW.plusDays(12)));

    var result = league.findNextMatch();

    assertEquals(NOW, result.get().at());
    assertEquals(1L, result.get().gameId());
    assertEquals(PLANNED, result.get().state());
  }

  @Test
  public void cancelMatch() {
    var league = league();
    assertEquals(league.allMatches().size(), 1);

    league.cancelMatch(1L);

    assertEquals(league.find(1L).state(), CANCELLED);
  }

  @Test
  public void injured() {
    var league = league();
    league.assignTeamForGame(1L, starting(), subs(), Collections.emptyList());
    league.submitMatch(1L);
    league.injured(UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ba"));

    assertEquals(league.find(1L).state(), PLANNED);
  }

  @Test
  public void recovered() {
    var league = league();
    league.recovered(UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ba"));
  }

  private LeagueAggregate league() {
    return LeagueFactory.of(all(), List.of(new LeagueMatch(1L, NOW, PLANNED)));
  }

  private List<LeagueFootballer> all() {
    List<LeagueFootballer> all = new ArrayList<>();
    all.addAll(starting());
    all.addAll(subs());
    return all;
  }


  private List<LeagueFootballer> starting() {
    List<LeagueFootballer> starting = new ArrayList<>();
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ba"), LocalDate.of(2006, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82bb"), LocalDate.of(2006, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82bc"), LocalDate.of(2006, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82bc"), LocalDate.of(2006, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82bd"), LocalDate.of(2000, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82be"), LocalDate.of(2000, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82bf"), LocalDate.of(2000, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ab"), LocalDate.of(2000, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82cb"), LocalDate.of(2000, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82eb"), LocalDate.of(2000, 5, 25)));
    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82fb"), LocalDate.of(2000, 5, 25)));
    return starting;
  }

  private List<LeagueFootballer> subs() {
    List<LeagueFootballer> subs = new ArrayList<>();
    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82aa"), LocalDate.of(2006, 5, 25)));
    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a81bb"), LocalDate.of(2000, 5, 25)));
    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82cc"), LocalDate.of(2000, 5, 25)));
    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82dd"), LocalDate.of(2000, 5, 25)));
    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ee"), LocalDate.of(2000, 5, 25)));
    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ff"), LocalDate.of(2000, 5, 25)));
    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a80bb"), LocalDate.of(2000, 5, 25)));
    return subs;
  }

}
