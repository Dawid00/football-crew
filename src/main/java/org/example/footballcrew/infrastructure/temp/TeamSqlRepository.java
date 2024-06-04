package org.example.footballcrew.infrastructure.temp;

import lombok.AllArgsConstructor;
import org.example.footballcrew.app.TeamDatabase;
import org.example.footballcrew.domain.season.team.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TeamSqlRepository implements TeamDatabase {


  private final JdbcTemplate jdbcTemplate;

  @Override
  public Team getTeamById(UUID id) {
    return null;
  }


//  @Override
//  public Team getTeamById(UUID id) {
//    List<Footballer> footballers = new ArrayList<>();
//    return jdbcTemplate.query(
//            "SELECT * FROM teams t JOIN teams_footballers tf ON t.id=tf.team_id JOIN footballers fs ON fs.id = tf.footballer_id WHERE t.id = ? ",
//            rs -> {
//              do {
//                footballers.add(new Footballer(
//                        UUID.fromString(rs.getString("id")),
//                        rs.getDate("born_date").toLocalDate().atStartOfDay(),
//                        new Card(rs.getInt("yellow_cards"), CardType.YELLOW),
//                        new Card(rs.getInt("red_cards"), CardType.YELLOW),
//                        Collections.emptyList()
//                ));
//                var id = UUID.fromString(rs.getString("id"));
//                if (exclusionsByFootballerId.containsKey(id)) {
//                  exclusionsByFootballerId.get(id).add(mapExclusion(rs));
//                } else {
//                  List<Exclusion> list = new ArrayList<>();
//                  list.add(mapExclusion(rs));
//                  exclusionsByFootballerId.put(id, list);
//                }
//              } while (rs.next());
//            });
//    return footballers.stream().map(f -> {
//      if (exclusionsByFootballerId.get(f.getId()) != null) {
//        return f.withExclusions(exclusionsByFootballerId.get(f.getId()));
//      } else {
//        return f;
//      }
//
//    }).toList();
//  }

//  id);
}

