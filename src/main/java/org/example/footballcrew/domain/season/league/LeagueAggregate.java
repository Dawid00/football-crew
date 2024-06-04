package org.example.footballcrew.domain.season.league;

import lombok.Getter;
import org.example.footballcrew.domain.minute.Minute;
import org.example.footballcrew.domain.season.league.exception.LeagueException;
import org.example.footballcrew.domain.season.league.specifications.YouthFootballerSpecification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.UUID;

import static org.example.footballcrew.domain.match.MatchStatus.PLANNED;
import static org.example.footballcrew.domain.match.MatchStatus.PLAYED;
import static org.example.footballcrew.domain.match.MatchStatus.SUBMITTED;
import static org.example.footballcrew.domain.match.MatchStatus.TEAM_ASSIGNED;

public class LeagueAggregate {

  @Getter
  private UUID id;
  private List<LeagueFootballer> footballers = new ArrayList<>();
  private List<LeagueMatch> games = new ArrayList<>();
  private final YouthFootballerSpecification youthFootballerSpecification;

  public LeagueAggregate(List<LeagueFootballer> footballers, List<LeagueMatch> games, YouthFootballerSpecification youthFootballerSpecification ){
this.youthFootballerSpecification = youthFootballerSpecification;
    this.id = UUID.randomUUID();
    this.footballers.addAll(footballers);
    this.games.addAll(games);
    Collections.sort(this.games, Comparator.comparing(LeagueMatch::at));
  }

  public LeagueAggregate(UUID id, List<LeagueFootballer> footballers, List<LeagueMatch> games, YouthFootballerSpecification youthFootballerSpecification) {
    this.id = id;
    this.footballers.addAll(footballers);
    this.games.addAll(games);
    Collections.sort(this.games, Comparator.comparing(LeagueMatch::at));
    this.youthFootballerSpecification= youthFootballerSpecification;
  }

  public void assignTeamForGame(Long gameId, List<LeagueFootballer> starting, List<LeagueFootballer> subs, List<LeagueFootballer> absence) {
    var match = find(gameId);
    match.assignTeam(starting, subs, absence);
  }

  public void matchPlayed(Long gameId, Minute minute) {
    var match = find(gameId);
    minute.getCards().forEach(cardByPlayer -> {
      var footballer = findFootballer(cardByPlayer.playerId());
      footballer.handle(cardByPlayer.card());
    });
    match.played(minute);
  }

  public void submitMatch(Long gameId) {
    var match = find(gameId);
    validateMatches(gameId);
    match.submit(youthFootballerSpecification);
  }


  public void planMatch(LeagueMatch leagueMatch) {
    this.games.add(leagueMatch);
    Collections.sort(this.games, Comparator.comparing(LeagueMatch::at));
  }

  public Optional<LeagueMatch> findNextMatch() {
    return this.games.stream().filter(m -> m.state().equals(PLANNED)).min(Comparator.comparing(LeagueMatch::at));
  }

  public void cancelMatch(Long gameId) {
    var match = find(gameId);
    match.cancel();
  }

  public void recovered(UUID footballerId) {
    var footballer = findFootballer(footballerId);
    footballer.recovered();
  }

  public void injured(UUID footballerId) {
    var footballer = findFootballer(footballerId);
    footballer.injured();
    this.games.stream().filter(m -> m.state().equals(TEAM_ASSIGNED) || m.state().equals(SUBMITTED)).forEach(m -> m.injury(footballerId));
  }

  public List<LeagueMatch> allMatches() {
    return this.games;
  }

  public LeagueMatch find(Long id) {
    var match = this.games.stream().filter(s -> s.gameId().equals(id)).findFirst();
    if (match.isEmpty()) throw new RuntimeException("TODO NOT FOUND");
    return match.get();
  }

  private LeagueFootballer findFootballer(UUID id) {
    var footballer = this.footballers.stream().filter(s -> s.getId().equals(id)).findFirst();
    return footballer.orElseThrow(() -> new RuntimeException("TODO NOT FOUND"));
  }

  private boolean isFirstGameSeason(Long gameId) {
    return gameId.equals(games.getFirst().gameId());
  }

  private void validateMatches(Long gameId) {
    var prev = findPreviousMatch(gameId);
    if (!isFirstGameSeason(gameId) && !prev.state().equals(PLAYED)) {
      throw new LeagueException("Previous match is not played! Wait for it due to exclusions and injuries");
    }
  }

  private LeagueMatch findPreviousMatch(Long gameId) {
    ListIterator<LeagueMatch> iterator = games.listIterator();
    LeagueMatch result = null;
    while (iterator.hasNext()) {
      if (iterator.next().gameId().equals(gameId)) {
        result = iterator.previous();
        iterator.next();
      }
    }
    return result;
  }

}