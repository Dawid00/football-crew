package org.example.footballcrew.infrastructure.season;

import lombok.AllArgsConstructor;
import org.example.footballcrew.catalog.FootballerInstance;
import org.example.footballcrew.catalog.injury.Injury;
import org.example.footballcrew.catalog.injury.InjuryStatus;
import org.example.footballcrew.domain.season.team.SeasonDatabase;
import org.example.footballcrew.domain.season.team.TeamInSeason;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


@AllArgsConstructor
@Service
public class SeasonSqlRepository implements SeasonDatabase {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(TeamInSeason teamInSeason) {
        jdbcTemplate.update("INSERT INTO team_in_season VALUES ?", teamInSeason.getId().toString());
        teamInSeason.getFootballers().forEach(f ->
                jdbcTemplate.update("INSERT INTO teams_footballers VALUES (?, ?) ", teamInSeason.getId().toString(), f.getId()));
    }

    /*
    id name team_id footbaler_instance_id injury_id till          reason name        footballer_id
    1   unia 1       11                      111     2024-05-25  'faul' 'skrecenie'    11
    1   unia 1       11                      222     2024-06-25  'faul' 'nos'          11
    1   unia 1       12                      null          null    null   null         null
    1   unia 1       13                      null          null    null   null         null
    1   unia 1       14                      null          null    null   null         null
    1   unia 1       15                      null          null    null   null         null
     */
    @Override
    public TeamInSeason find() {
        List<FootballerInstance> footballers = new ArrayList<>();
        AtomicReference<Optional<UUID>> teamInSeasonId = new AtomicReference<>(Optional.empty());
        jdbcTemplate.query("SELECT * FROM teams t JOIN teams_footballers tf ON t.id = tf.team_id left JOIN injuries inj on inj.footballer_id = tf.footballer_instance_id ",
                rs -> {
                    do {
                        if (teamInSeasonId.get().isEmpty()) {
                            teamInSeasonId.set(Optional.of(UUID.fromString(rs.getString(1))));
                        }

                        if (Objects.nonNull(rs.getString(4))) {
                            var footballerInstance = new FootballerInstance(
                                    UUID.fromString(rs.getString(4)), List.of(
                                    new Injury(UUID.fromString(rs.getString(4)),
                                            rs.getDate("till").toLocalDate(),
                                            rs.getString("name"),
                                            rs.getString("reason"),
                                            InjuryStatus.valueOf(rs.getString("status"))
                                    )
                            ));
                            footballers.add(footballerInstance);
                        } else {
                            footballers.add(new FootballerInstance(UUID.fromString(rs.getString(4)), Collections.emptyList()));
                        }
                    }
                    while (rs.next());
                });
        return new TeamInSeason(teamInSeasonId.get().get(), new HashSet<>(footballers));
    }

    ;

}
