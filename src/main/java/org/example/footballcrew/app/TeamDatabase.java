package org.example.footballcrew.app;

import org.example.footballcrew.domain.season.team.Team;

import java.util.UUID;

public interface TeamDatabase {
  Team getTeamById(UUID id);
}
