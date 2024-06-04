package org.example.footballcrew.domain.season.league;



import org.example.footballcrew.domain.season.league.specifications.YouthFootballerSpecification;

import java.util.List;

public class LeagueFactory {
  //TODO DP konfiguracja kar
  public static LeagueAggregate of(List<LeagueFootballer> footballers, List<LeagueMatch> games) {
    var leagueFootballers = footballers.stream().map(
            f -> new LeagueFootballer(f.getId(), f.getBornDate())
    ).toList();
    return new LeagueAggregate(leagueFootballers, games, new YouthFootballerSpecification(21));
  }
}
