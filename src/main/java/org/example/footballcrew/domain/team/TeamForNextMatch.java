package org.example.footballcrew.domain.team;

import org.example.footballcrew.domain.footballer.Footballer;
import org.example.footballcrew.domain.footballer.Squad;
import org.example.footballcrew.domain.footballer.policy.FootballerPolicy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TeamForNextMatch {

  private UUID id;
  private UUID matchId;
  private TeamStatus status;
  private Set<Footballer> subs = new HashSet<>();
  private Set<Footballer> starting = new HashSet<>();
  private Set<Footballer> absence = new HashSet<>();
  private final List<FootballerPolicy> policies = FootballerPolicy.allCurrentPolicies();

  public TeamForNextMatch(UUID matchId) {
    this.matchId = matchId;
    this.id = UUID.randomUUID();
    this.status = TeamStatus.DRAFT;
  }

  public TeamForNextMatch(UUID id, UUID matchId, TeamStatus status, Set<Footballer> subs, Set<Footballer> starting, Set<Footballer> absence) {
    this.id = id;
    this.matchId = matchId;
    this.status = status;
    this.subs = subs;
    this.starting = starting;
    this.absence = absence;
  }

  enum TeamStatus {
    SUBMITTED, REJECTED, DRAFT, HELD
  }

  void submit(Set<Footballer> starting, Set<Footballer> subs, Set<Footballer> absence) {
    if (policies.stream().anyMatch(policy -> policy.apply(new Squad(starting, subs, absence)))) {
      this.status = TeamStatus.REJECTED;
      throw new RuntimeException("Can not submit team for match");
    }
    this.status = TeamStatus.SUBMITTED;
  }

  public void confirmSquad(Squad squad) {
    //TODO improve result from policy, FAILURE REASON needed
    if (policies.stream().anyMatch(policy -> policy.apply(new Squad(starting, subs, absence)))) {
      this.status = TeamStatus.REJECTED;
      throw new RuntimeException("Can not submit team for match");
    }
    this.subs = squad.getSubs();
    snapshot();
    this.status = TeamStatus.SUBMITTED;
  }

  private void snapshot() {
    //TODO save snapshot before match
  }

}
