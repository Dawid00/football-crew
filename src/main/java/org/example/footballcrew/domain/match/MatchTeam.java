//package org.example.footballcrew.domain.match;
//
//import org.example.footballcrew.domain.footballer.Footballer;
//import org.example.footballcrew.domain.season.league.LeagueFootballer;
//
//import java.util.Optional;
//import java.util.Set;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public final class MatchTeam {
//  private final Set<LeagueFootballer> subs;
//  private final Set<LeagueFootballer> starting;
//  private final Set<LeagueFootballer> absence;
//
//  public MatchTeam(Set<LeagueFootballer> starting, Set<LeagueFootballer> subs, Set<LeagueFootballer> absence) {
//    this.starting = starting;
//    this.subs = subs;
//    this.absence = absence;
//  }
//
//  public String position(UUID footballerId) {
//    if (starting.stream().map(LeagueFootballer::getId).toList().contains(footballerId)) return "STARTING";
//    if (subs.stream().map(LeagueFootballer::getId).toList().contains(footballerId)) return "SUBS";
//    if (absence.stream().map(LeagueFootballer::getId).toList().contains(footballerId)) return "ABSENCE";
//    return "";
//  }
//
//  public Optional<LeagueFootballer> find(UUID id) {
//    return starting.stream().filter(f -> f.getId().equals(id)).findAny();
//  }
//
//  public Set<LeagueFootballer> getSubsAndStarting() {
//    return Stream.concat(starting.stream(), subs.stream()).collect(Collectors.toSet());
//  }
//
//  public Set<LeagueFootballer> getSubs() {
//    return subs.stream().collect(Collectors.toUnmodifiableSet());
//  }
//
//  public Set<LeagueFootballer> getStarting() {
//    return starting.stream().collect(Collectors.toUnmodifiableSet());
//  }
//
//  public Set<LeagueFootballer> getAbsence() {
//    return absence.stream().collect(Collectors.toUnmodifiableSet());
//  }
//}