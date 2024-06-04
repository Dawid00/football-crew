package org.example.footballcrew.app;

import org.example.footballcrew.domain.team.AggregateTeam;

import java.util.UUID;

public interface TeamDatabase {
  AggregateTeam getTeamById(UUID id);
}
