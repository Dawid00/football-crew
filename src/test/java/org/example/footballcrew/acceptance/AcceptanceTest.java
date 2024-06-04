//package org.example.footballcrew.acceptance;
//
//
//import org.example.footballcrew.domain.cards.RedCards;
//import org.example.footballcrew.domain.cards.YellowCards;
//import org.example.footballcrew.catalog.FootballerInstance;
//import org.example.footballcrew.domain.season.events.FootballerInjured;
//import org.example.footballcrew.domain.season.league.LeagueAggregate;
//import org.example.footballcrew.domain.season.league.LeagueFactory;
//import org.example.footballcrew.domain.season.league.LeagueFootballer;
//import org.example.footballcrew.domain.season.team.TeamInSeason;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//import java.util.UUID;
//
//import static org.example.footballcrew.domain.match.MatchStatus.PLANNED;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class AcceptanceTest {
//
//  private static final LocalDateTime NOW = LocalDateTime.of(2024, 5, 25, 20, 0, 0);
//
//  @Test
//  public void shouldReturnTrueAfterInjured() {
//    var team =
//            Set.of(
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance()
//            );
//    var teamInSeason = new TeamInSeason(team);
//    teamInSeason.handleInjury(new FootballerInjured(UUID.fromString("")));
//    assertTrue(teamInSeason.isInjured(UUID.fromString("")));
//  }
//
//  public void shouldReturnFalseAfterRecovered() {
//    var team =
//            Set.of(
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance(),
//                    new FootballerInstance()
//            );
//    var teamInSeason = new TeamInSeason(team);
//    teamInSeason.handleInjury(new FootballerInjured(UUID.fromString("")));
//    teamInSeason.recovered(UUID.fromString(""), UUID.fromString(""));
//    assertFalse(teamInSeason.isInjured(UUID.fromString("")));
//  }
//
//
//  private LeagueAggregate league() {
//    return LeagueFactory.of(all(), List.of(new LeagueMatch(1L, NOW, PLANNED)));
//  }
//
//  private List<LeagueFootballer> all() {
//    List<LeagueFootballer> all = new ArrayList<>();
//    all.addAll(starting());
//    all.addAll(subs());
//    return all;
//  }
//
//
//  private List<LeagueFootballer> starting() {
//    List<LeagueFootballer> starting = new ArrayList<>();
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ba"), LocalDate.of(2006, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82bb"), LocalDate.of(2006, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82bc"), LocalDate.of(2006, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82bd"), LocalDate.of(2000, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82be"), LocalDate.of(2000, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82bf"), LocalDate.of(2000, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ab"), LocalDate.of(2000, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82cb"), LocalDate.of(2000, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82db"), LocalDate.of(2000, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82eb"), LocalDate.of(2000, 5, 25)));
//    starting.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82fb"), LocalDate.of(2000, 5, 25)));
//    return starting;
//  }
//
//  private List<LeagueFootballer> subs() {
//    List<LeagueFootballer> subs = new ArrayList<>();
//    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82aa"), LocalDate.of(2006, 5, 25)));
//    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a81bb"), LocalDate.of(2000, 5, 25)));
//    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82cc"), LocalDate.of(2000, 5, 25)));
//    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82dd"), LocalDate.of(2000, 5, 25)));
//    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ee"), LocalDate.of(2000, 5, 25)));
//    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a82ff"), LocalDate.of(2000, 5, 25)));
//    subs.add(new LeagueFootballer(Collections.emptyList(), new YellowCards(0), new RedCards(0), UUID.fromString("b794d422-271a-4d1e-ae30-ec9ce85a80bb"), LocalDate.of(2000, 5, 25)));
//    return subs;
//  }
//}
