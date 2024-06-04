//package org.example.footballcrew.domain.match;
//
//import org.example.footballcrew.domain.footballer.Footballer;
//import org.example.footballcrew.domain.season.league.LeagueFootballer;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public record MatchTeamRequest(Set<LeagueFootballer> starting,
//                               Set<LeagueFootballer> subs, Set<LeagueFootballer> absence
//) {
//  public Set<LeagueFootballer> getSubsAndStarting() {
//    return Stream.concat(starting.stream(), subs.stream()).collect(Collectors.toSet());
//  }
//
//}
