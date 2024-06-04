package org.example.footballcrew.domain.season.league;

import lombok.Getter;
import lombok.ToString;
import org.example.footballcrew.domain.match.MatchStatus;
import org.example.footballcrew.domain.match.MatchType;
import org.example.footballcrew.domain.minute.Minute;
import org.example.footballcrew.domain.season.league.exception.LeagueException;
import org.example.footballcrew.domain.season.league.specifications.GamesSpecification;
import org.example.footballcrew.domain.season.league.specifications.YouthFootballerSpecification;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ToString
class LeagueMatch {

  private UUID id;
  private Long gameId;
  private LocalDateTime at;
  @Getter
  private List<LeagueFootballer> starting;
  @Getter
  private List<LeagueFootballer> subs;
  private List<LeagueFootballer> absence;
  private final MatchType matchType = MatchType.LEAGUE;
  private MatchStatus status;
  @Getter
  private Minute minute;

  public LeagueMatch(Long gameId, LocalDateTime at) {
    this.id = UUID.randomUUID();
    this.at = at;
    this.gameId = gameId;
    this.status = MatchStatus.PLANNED;
  }

  public LeagueMatch(Long gameId, LocalDateTime at, MatchStatus status) {
    this.id = UUID.randomUUID();
    this.at = at;
    this.gameId = gameId;
    this.status = status;
  }

  void injury(UUID footballerId) {
    if (this.starting.stream().map(LeagueFootballer::getId).toList().contains(footballerId)) {
      this.status = MatchStatus.PLANNED;
      clearSquad();
    }
  }

  void clearSquad() {
    this.starting = Collections.emptyList();
    this.subs = Collections.emptyList();
    this.absence = Collections.emptyList();
  }

  void assignTeam(List<LeagueFootballer> starting, List<LeagueFootballer> subs, List<LeagueFootballer> absence) {
    if (this.status != MatchStatus.PLAYED) {
      this.starting = starting;
      this.subs = subs;
      this.absence = absence;
      this.status = MatchStatus.TEAM_ASSIGNED;
    } else {
      throw new LeagueException("Cannot assigned team to played match!");
    }
  }

  void submit(YouthFootballerSpecification specification) {
    if (this.state() != MatchStatus.TEAM_ASSIGNED) {
      throw new LeagueException("Team not assigned!");//TODO
    }
    validateInjuries();
    var policies = FootballerPolicy.chooseFor(MatchType.LEAGUE, specification);
    var result = policies.stream()
            .filter(footballerPolicy -> footballerPolicy.apply(starting.stream().toList(), subs.stream().toList()))
            .toList();
    if (!result.isEmpty()) {
      throw new LeagueException("Policies are not satisfied!");//TODO
    }
    this.status = MatchStatus.SUBMITTED;
  }

  private void validateInjuries() {
    if (this.starting.stream().anyMatch(LeagueFootballer::injured))
      throw new LeagueException("TODO" + "Injured footballer can not play");
  }

  UUID id() {
    return this.id;
  }

  MatchType type() {
    return this.matchType;
  }

  LocalDateTime at() {
    return at;
  }

  Long gameId() {
    return gameId;
  }

  MatchStatus state() {
    return status;
  }

  void played(Minute minute) {
    if (this.status == MatchStatus.SUBMITTED) {
      this.minute = minute;
      this.status = MatchStatus.PLAYED;
    } else {
      throw new LeagueException("");
    }
  }

  void cancel() {
    if (this.status != MatchStatus.PLAYED) this.status = MatchStatus.CANCELLED;
  }

  void postpone() {
    if (this.status != MatchStatus.PLAYED) this.status = MatchStatus.POSTPONED;
  }

}
