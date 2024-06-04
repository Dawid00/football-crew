package org.example.footballcrew.domain.season.team;

import lombok.Getter;
import lombok.ToString;
import org.example.footballcrew.catalog.FootballerInstance;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ToString
@Getter
public class Team {

  private final Set<FootballerInstance> footballers;

  public Team(Set<FootballerInstance> footballers) {
    this.footballers = footballers;
  }

  public Optional<FootballerInstance> find(UUID id) {
    return footballers.stream().filter(f -> f.getId().equals(id)).findFirst();
  }
}
