package org.example.footballcrew.infrastructure.league;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/league")
public class LeagueController {

  private final LeagueDatabase leagueDatabase;

  public LeagueController(LeagueDatabase leagueDatabase) {
    this.leagueDatabase = leagueDatabase;
  }

  public ResponseEntity<?> planMatch(@RequestBody PlanMatchRequest planMatchRequest) {
//    this.leagueDatabase.save();
    return ResponseEntity.ok(201);
  }
}
