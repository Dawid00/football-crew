package org.example.footballcrew.infrastructure.season;

import lombok.AllArgsConstructor;
import org.example.footballcrew.domain.season.team.TeamInSeason;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/season")
public class SeasonController {

  private final SeasonServiceImpl seasonService;

  @PostMapping
  public ResponseEntity<?> createSeason() {
    seasonService.createTeamInSeason();
    return ResponseEntity.status(201).build();
  }

  @GetMapping
  public ResponseEntity<TeamInSeason> getSeason() {
    return ResponseEntity.ok(seasonService.getSeason());
  }


}
