package org.example.footballcrew.domain.footballer.policy;

import io.vavr.Function1;
import org.example.footballcrew.domain.footballer.Footballer;
import org.example.footballcrew.domain.footballer.Squad;
import org.example.footballcrew.domain.footballer.YouthFootballerSpecification;

import java.util.List;
import java.util.stream.Stream;

public interface FootballerPolicy
        extends Function1<Squad, Boolean> {

    FootballerPolicy exclusionPolicy = (Squad squad) ->
            Stream.concat(squad.getSubs().stream(), squad.getStarting().stream()).anyMatch(Footballer::isExcluded);
    FootballerPolicy youthPolicy = (Squad squad) -> squad.getStarting().stream().filter(player -> player.isYouth(new YouthFootballerSpecification(21))).count() < 2;
    FootballerPolicy countPolicy = (Squad squad) -> squad.getStarting().size() < 7 || squad.getStarting().size() > 11;
    FootballerPolicy subsPolicy = (Squad squad) -> squad.getSubs().size() > 7;

    static List<FootballerPolicy> allCurrentPolicies() {
        return List.of(exclusionPolicy, youthPolicy, countPolicy, subsPolicy);
    }
}

class ExclusionResult {
}