//package org.example.footballcrew.domain.player;
//
//import org.example.footballcrew.domain.player.policy.PlayersPolicy;
//
//import java.util.List;
//import java.util.UUID;
//
//public class Match {
//  private UUID id;
//  private Squad squad;
//  private final List<PlayersPolicy> policies = PlayersPolicy.allCurrentPolicies();
//
//  public Match(Minute minute) {
//    this.minute = minute;
//  }
//
//  public void confirmSquad(Squad squad) {
//    //TODO improve result from policy, FAILURE REASON needed
//    if (policies.stream().anyMatch(policy -> policy.apply(squad))) {
//      throw new RuntimeException("Can not submit team for match");
//    }
//    this.squad = squad;
//  }
//}
//
//
