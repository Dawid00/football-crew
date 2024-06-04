package org.example.footballcrew.domain.season.team;

import lombok.Getter;
import lombok.ToString;
import org.example.footballcrew.catalog.FootballerInstance;
import org.example.footballcrew.domain.season.events.FootballerInjured;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ToString
public class TeamInSeason {

  @Getter
  private UUID id;
  @Getter
  private Set<FootballerInstance> footballers;
  private UUID league;
  private UUID friendly;
  private UUID tournament;

  public TeamInSeason(UUID id, Set<FootballerInstance> footballers) {
    this.footballers = footballers;
    this.id = id;
  }

  public TeamInSeason(Set<FootballerInstance> footballers) {
    this.footballers = footballers;
    this.id = UUID.randomUUID();
  }

  public Optional<FootballerInstance> find(UUID id) {
    return footballers.stream().filter(f -> f.getId().equals(id)).findFirst();
  }


  public void handleInjury(FootballerInjured footballerInjured) {
    var footballer = find(footballerInjured.footballerId());
    footballer.ifPresent(value -> value.getInjured(LocalDate.MAX, "twisted ankle", "faul"));
  }

  public void recovered(UUID footballerId, UUID injuryId) {
    var footballer = find(footballerId);
    footballer.ifPresent(value -> value.recovered(injuryId));
  }

  public boolean isInjured(UUID footballerId) {
    var footballer = find(footballerId);
    return footballer.map(FootballerInstance::injured).orElseThrow(() -> new RuntimeException("Not found footballer"));
  }

}