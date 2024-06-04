package org.example.footballcrew.infrastructure;

import org.example.footballcrew.domain.footballer.exclusion.Exclusion;
import org.example.footballcrew.domain.footballer.exclusion.ExclusionType;
import org.example.footballcrew.domain.footballer.exclusion.QuantitativeExclusion;
import org.example.footballcrew.domain.footballer.exclusion.StateExclusion;
import org.example.footballcrew.domain.footballer.exclusion.TimeExclusion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ExclusionMapper implements RowMapper<Exclusion> {

  @Override
  public Exclusion mapRow(ResultSet rs, int rowNum) throws SQLException {
    var type = ExclusionType.valueOf(rs.getString("type"));
    switch (type) {
      case ExclusionType.QUANTITATIVE -> {
        return new QuantitativeExclusion(UUID.fromString(rs.getString("id")), rs.getInt("quantity"));
      }
      case ExclusionType.TIME -> {
        return new TimeExclusion(UUID.fromString(rs.getString("id")),rs.getDate("till").toLocalDate().atStartOfDay());
      }
      case ExclusionType.STATE -> {
        return new StateExclusion(UUID.fromString(rs.getString("id")),rs.getBoolean("active"));
      }
      default -> throw new RuntimeException("TODO ");
    }
  }
}