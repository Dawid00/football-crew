package org.example.footballcrew.infrastructure.season;

import org.example.footballcrew.domain.season.team.SeasonDatabase;
import org.example.footballcrew.domain.season.team.SeasonService;
import org.example.footballcrew.domain.season.team.TeamInSeason;
import org.springframework.stereotype.Service;

@Service
public class SeasonServiceImpl {

  private final SeasonDatabase seasonDatabase;
  private final SeasonService seasonService = new SeasonService();

  public SeasonServiceImpl(SeasonDatabase seasonDatabase) {
    this.seasonDatabase = seasonDatabase;
  }
  public TeamInSeason getSeason(){
    return seasonDatabase.find();

  }
  public void createTeamInSeason() {
    seasonDatabase.save(seasonService.createTeamInSeason());
  }
}
