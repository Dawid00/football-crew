package org.example.footballcrew.infrastructure.league;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class LeagueSqlRepo implements LeagueDatabase {

  private final JdbcTemplate jdbcTemplate;

  public LeagueSqlRepo(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

}
