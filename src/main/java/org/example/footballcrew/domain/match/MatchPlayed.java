//package org.example.footballcrew.domain.match;
//
//import org.example.footballcrew.domain.team.minute.Minute;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//public class MatchPlayed implements Match {
//
//  private UUID id;
//  private Minute minute;
//  private MatchTeam team;
//  private Long gameId;
//  private LocalDateTime at;
//  private MatchType matchType;
//
//  public MatchPlayed(MatchTeam team, Long gameId, Minute minute, LocalDateTime at, MatchType matchType) {
//    this.gameId = gameId;
//    this.id = UUID.randomUUID();
//    this.at = at;
//    this.team = team;
//    this.minute = minute;
//    this.gameId = gameId;
//  }
//
//  @Override
//  public UUID id() {
//    return this.id;
//  }
//
//  @Override
//  public MatchType type() {
//    return matchType;
//  }
//
//  @Override
//  public LocalDateTime at() {
//    return at;
//  }
//
//  @Override
//  public Long gameId() {
//    return gameId;
//  }
//
//  @Override
//  public MatchStatus state() {
//    return null;
//  }
//}
