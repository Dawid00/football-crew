package org.example.footballcrew.domain.season.team;

public interface SeasonDatabase {
  void save(TeamInSeason teamInSeason);

  TeamInSeason find();
}
