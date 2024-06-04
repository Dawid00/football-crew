package org.example.footballcrew.domain.season.league;

import io.vavr.Function2;
import org.example.footballcrew.domain.match.MatchType;
import org.example.footballcrew.domain.season.league.specifications.YouthFootballerSpecification;

import java.time.LocalDate;
import java.util.List;

public interface FootballerPolicy
        extends Function2<List<LeagueFootballer>, List<LeagueFootballer>, Boolean> {

  FootballerPolicy countPolicy = (starting, subs) -> starting.size() < 7 || starting.size() > 11;
  FootballerPolicy subsPolicy = (starting, subs) -> subs.size() > 7;

  static List<FootballerPolicy> chooseFor(MatchType matchType, YouthFootballerSpecification youthSpecification) {
    FootballerPolicy youthPolicy = (starting, subs) -> starting.stream().filter(player -> player.isYouth(youthSpecification, LocalDate.now())).count() < 2;
    return switch (matchType) {
      case FRIENDLY -> List.of(countPolicy);
      case TOURNAMENT -> List.of(subsPolicy, countPolicy);
      case LEAGUE -> List.of(youthPolicy, countPolicy, subsPolicy);
    };
  }
}