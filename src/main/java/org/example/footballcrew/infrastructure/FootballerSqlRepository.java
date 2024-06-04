package org.example.footballcrew.infrastructure;

import lombok.AllArgsConstructor;
import org.example.footballcrew.availability.CardType;
import org.example.footballcrew.domain.footballer.Card;
import org.example.footballcrew.domain.footballer.Footballer;
import org.example.footballcrew.domain.footballer.exclusion.Exclusion;
import org.example.footballcrew.domain.footballer.exclusion.ExclusionType;
import org.example.footballcrew.domain.footballer.exclusion.QuantitativeExclusion;
import org.example.footballcrew.domain.footballer.exclusion.StateExclusion;
import org.example.footballcrew.domain.footballer.exclusion.TimeExclusion;
import org.example.footballcrew.domain.footballer.ports.FootballerDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class FootballerSqlRepository implements FootballerDatabase {

  private final JdbcTemplate jdbcTemplate;
  private final ExclusionSqlRepository exclusionSqlRepository;
  private final ExclusionMapper exclusionMapper = new ExclusionMapper();
  private final FootballerMapper footballerMapper = new FootballerMapper(Collections.emptyList());

  @Override
  public Footballer getFootballerById(UUID id) {
    var exclusions = exclusionSqlRepository.getExclusionById(id);
    return jdbcTemplate.queryForObject("SELECT * FROM footballers WHERE id = ?", new FootballerMapper(exclusions), id.toString());
  }

  @Override
  public List<Footballer> getFootballers() {
    Map<UUID, List<Exclusion>> exclusionsByFootballerId = new HashMap<>();
    List<Footballer> footballers = new ArrayList<>();
    jdbcTemplate.query(
    "SELECT f.*, e.* " +
     " FROM footballers as f LEFT JOIN exclusions as e ON f.id = e.footballer_id", rs -> {
      do {
        footballers.add(new Footballer(
                UUID.fromString(rs.getString("id")),
                rs.getDate("born_date").toLocalDate().atStartOfDay(),
                new Card(rs.getInt("yellow_cards"), CardType.YELLOW),
                new Card(rs.getInt("red_cards"), CardType.YELLOW),
                Collections.emptyList()
        ));
        var id = UUID.fromString(rs.getString("id"));
        if (exclusionsByFootballerId.containsKey(id)) {
          exclusionsByFootballerId.get(id).add(mapExclusion(rs));
        } else {
          List<Exclusion> list = new ArrayList<>();
          list.add(mapExclusion(rs));
          exclusionsByFootballerId.put(id, list);
        }
      } while (rs.next());
    });
    return footballers.stream().map(f ->{
    if(exclusionsByFootballerId.get(f.getId()) != null){
      return f.withExclusions(exclusionsByFootballerId.get(f.getId()));
      }
      else{
      return f;
    }

    } ).toList();
  }

  private Exclusion mapExclusion(ResultSet rs) throws SQLException {
    var type = ExclusionType.valueOf(rs.getString("type"));
    switch (type) {
      case ExclusionType.QUANTITATIVE -> {
        return new QuantitativeExclusion(UUID.fromString(rs.getString(5)), rs.getInt("quantity"));
      }
      case ExclusionType.TIME -> {
        return new TimeExclusion(UUID.fromString(rs.getString(5)), rs.getDate("till").toLocalDate().atStartOfDay());
      }
      case ExclusionType.STATE -> {
        return new StateExclusion(UUID.fromString(rs.getString(5)), rs.getBoolean("active"));
      }
      default -> throw new RuntimeException("TODO ");
    }
  }

  @Override
  public void save(Footballer footballer) {
    jdbcTemplate.update(
            "INSERT INTO footballers(id, born_date, yellow_cards, red_cards) VALUES(?,?,?,?)",
            footballer.getId().toString(), footballer.getBornDate(), footballer.getYellowCardsQuantity(), footballer.getRedCardsQuantity()
    );
  }

  @Override
  public void update(Footballer footballer) {
    jdbcTemplate.update(
            "UPDATE footballers SET yellow_cards = ?, red_cards = ? WHERE id = ?",
            footballer.getYellowCardsQuantity(), footballer.getRedCardsQuantity(), footballer.getId().toString()
    );
    footballer.getExclusions().forEach(ex -> {
      exclusionSqlRepository.update(ex);

    });
  }
}
