package org.example.footballcrew.app;

import lombok.AllArgsConstructor;
import org.example.footballcrew.domain.footballer.Minute;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FootballerApplicationService {
    private final TeamDatabase teamDatabase;

  void handle(UUID id) {
    var team = teamDatabase.getTeamById(id);
    team.matchHeld(new Minute());
  }
}
