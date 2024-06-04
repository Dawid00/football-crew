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
//                            new FootballerInstance(LocalDate.of(2000, 1, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 2, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 3, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 4, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 5, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 6, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 7, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 8, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 9, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 10, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 11, 12)),
//                            new FootballerInstance(LocalDate.of(2000, 12, 12)),
//                            new FootballerInstance(LocalDate.of(2001, 5, 12)),
//                            new FootballerInstance(LocalDate.of(2004, 5, 12)),
//                            new FootballerInstance(LocalDate.of(2004, 6, 12)),
//                            new FootballerInstance(LocalDate.of(2004, 7, 12)),
//                            new FootballerInstance(LocalDate.of(2005, 8, 12)),
//                            new FootballerInstance(LocalDate.of(2004, 9, 12)),
//                            new FootballerInstance(LocalDate.of(2005, 1, 12)),
//                            new FootballerInstance(LocalDate.of(2004, 4, 12)),
//                            new FootballerInstance(LocalDate.of(2005, 4, 12))
            )
    );
  }
}
