//package org.example.footballcrew.domain.match;
//
//import org.example.footballcrew.domain.footballer.Footballer;
//import org.example.footballcrew.domain.footballer.policy.FootballerPolicy;
//import org.example.footballcrew.domain.team.minute.Minute;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.List;
//import java.util.UUID;
//
//public class MatchPlanned implements Match {
//
//  private UUID id;
//  private LocalDateTime at;
//  private Long gameId;
//  private MatchTeam team;
//  private final List<FootballerPolicy> policies;
//  private final MatchType matchType;
//
//  public MatchPlanned(UUID id, Long gameId, MatchTeam team, LocalDateTime at, MatchType matchType) {
//    this.team = team;
//    this.at = at;
//    this.id = id;
//    this.matchType = matchType;
//    this.policies = FootballerPolicy.chooseFor(matchType);
//  }
//
//  public MatchPlanned(Long gameId, LocalDateTime at, MatchType matchType) {
//    this.id = UUID.randomUUID();
//    this.matchType = matchType;
//    this.gameId = gameId;
//    this.at = at;
//    this.policies = FootballerPolicy.chooseFor(matchType);
//  }
//
//
//  public void submitTeam(List<Footballer> starting, List<Footballer> subs, List<Footballer> absence) {
//    this.team = new MatchTeam(new HashSet<>(starting), new HashSet<>(subs), new HashSet<>(absence));
//    if (policies.stream().anyMatch(policy -> policy.apply(starting, subs))) {
//      throw new RuntimeException("Can not submit team for match");
//    }
//  }
//
//  public MatchPlayed matchPlayed(Minute minute) {
//    return new MatchPlayed(team, gameId, minute, at);
//  }
//
//  @Override
//  public UUID id() {
//    return this.id;
//  }
//
//  @Override
//  public MatchType type() {
//    return this.matchType;
//  }
//
//  @Override
//  public LocalDateTime at() {
//    return at;
//  }
//
//  @Override
//  public Long gameId() {
//    return this.gameId;
//  }
//}
//
//
////  public MatchPlanned(UUID id, MatchTeam team, TeamStatus teamStatus) {
////    this.team = team;
////    this.teamStatus = teamStatus;
////    this.id = id;
////  }
////  void reject() {
////    this.teamStatus = TeamStatus.DRAFT;
////    if (this.teamStatus != TeamStatus.SUBMITTED)
////      this.teamStatus = TeamStatus.REJECTED;
////  }
////
////  void submit() {
////    if (this.teamStatus != TeamStatus.SUBMITTED) {
////      this.teamStatus = TeamStatus.SUBMITTED;
////    }
////  }
//
////  public MatchPlanned(UUID id, Integer matchId, TeamStatus teamStatus, Set<Footballer> subs, Set<Footballer> starting, Set<Footballer> absence) {
////    this.id = id;
////    this.matchId = matchId;
////    this.status = status;
////    this.subs = subs;
////    this.starting = starting;
////    this.absence = absence;
////  }
//
//
////  public void confirmSquad(Squad squad) {
////    //TODO improve result from policy, FAILURE REASON needed
////    if (policies.stream().anyMatch(policy -> policy.apply(new Squad(starting, subs, absence)))) {
////      team.reject();
////      throw new RuntimeException("Can not submit team for match");
////    }
////    this.subs = squad.getSubs();
////    snapshot();
////    team.submit();
////  }
//
////  private void snapshot() {
////    //TODO save snapshot before match
////  }
//
////}
