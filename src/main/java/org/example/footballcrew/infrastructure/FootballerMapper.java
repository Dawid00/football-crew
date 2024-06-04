package org.example.footballcrew.infrastructure;

import org.example.footballcrew.availability.CardType;
import org.example.footballcrew.domain.footballer.Card;
import org.example.footballcrew.domain.footballer.Footballer;
import org.example.footballcrew.domain.footballer.exclusion.Exclusion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;


public class FootballerMapper implements RowMapper<Footballer> {
  private final List<Exclusion> exclusions;

  public FootballerMapper(List<Exclusion> exclusions) {
    this.exclusions = exclusions;
  }

  @Override
  public Footballer mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Footballer(
            UUID.fromString(rs.getString("ID")),
            rs.getDate("born_date").toLocalDate().atStartOfDay(),
            new Card(rs.getInt("yellow_cards"), CardType.YELLOW),
            new Card(rs.getInt("red_cards"), CardType.YELLOW),
            exclusions
    );
  }
}

