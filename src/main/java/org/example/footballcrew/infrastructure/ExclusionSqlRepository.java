package org.example.footballcrew.infrastructure;

import lombok.AllArgsConstructor;
import org.example.footballcrew.domain.footballer.exclusion.Exclusion;
import org.example.footballcrew.domain.footballer.exclusion.QuantitativeExclusion;
import org.example.footballcrew.domain.footballer.exclusion.StateExclusion;
import org.example.footballcrew.domain.footballer.exclusion.TimeExclusion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ExclusionSqlRepository {//} implements ExclusionDatabase {

  private final JdbcTemplate jdbcTemplate;


  public List<Exclusion> getExclusionById(UUID footballerId) {
    String sql = "SELECT * FROM exclusions WHERE footballer_id = ?";

    return jdbcTemplate.query(sql, new ExclusionMapper(),footballerId);
  }


  public List<Exclusion> getExclusions() {
    return jdbcTemplate.query("SELECT * FROM exclusions", new ExclusionMapper());
  }
  public void update(Exclusion exclusion){

    switch (exclusion) {
       case TimeExclusion timeExclusion -> update(timeExclusion);
       case StateExclusion stateExclusion -> update(stateExclusion);
       case QuantitativeExclusion quantitativeExclusion -> update(quantitativeExclusion);
      default -> throw new IllegalStateException("Unexpected value: " + exclusion);
    }
  }
  private  void update(StateExclusion exclusion) {
    jdbcTemplate.update(
            "UPDATE exclusions SET active = ? WHERE id = ?",
            exclusion.active(), exclusion.getId().toString()
    );
  }
  private void update(TimeExclusion exclusion) {
    jdbcTemplate.update(
            "UPDATE exclusions SET till = ? WHERE id = ?",
            exclusion.getTill(), exclusion.getId().toString()
    );
  }
  private void update(QuantitativeExclusion exclusion) {
    jdbcTemplate.update(
            "UPDATE exclusions SET quantity = ? WHERE id = ?",
            exclusion.getQuantity(), exclusion.getId().toString()
    );
  }

  public void save(StateExclusion exclusion, UUID footballerId) {
    jdbcTemplate.update(
            "INSERT INTO exclusions(id, footballer_id, type, quantity, active,till) VALUES(?,?,?,?,?,?)",
            UUID.randomUUID().toString(), footballerId.toString(), exclusion.type(), null, exclusion.active(), null
    );
  }

  public void save(TimeExclusion exclusion, UUID footballerId) {
    jdbcTemplate.update(
            "INSERT INTO exclusions(id, footballer_id, type, quantity, active,till) VALUES(?,?,?,?,?,?)",
            UUID.randomUUID().toString(), footballerId.toString(), exclusion.type(), null, null, exclusion.getTill()
    );
  }

  public void save(QuantitativeExclusion exclusion, UUID footballerId) {
    jdbcTemplate.update(
            "INSERT INTO exclusions(id, footballer_id, type, quantity, active,till) VALUES(?,?,?,?,?,?)",
            UUID.randomUUID().toString(), footballerId.toString(), exclusion.type(), exclusion.type(), null, null
    );
  }
}
