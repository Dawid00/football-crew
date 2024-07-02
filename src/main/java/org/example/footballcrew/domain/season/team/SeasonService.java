package org.example.footballcrew.domain.season.team;

import lombok.AllArgsConstructor;
import org.example.footballcrew.catalog.FootballerInstance;

import java.util.Set;

@AllArgsConstructor
public class SeasonService {


  public TeamInSeason createTeamInSeason() {
    return new TeamInSeason(

            Set.of(
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance(),
                    new FootballerInstance()
            )
    );
  }
}
