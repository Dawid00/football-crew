package org.example.footballcrew.domain.exclusion;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExclusionRepository {

  public ExclusionRepository() {
    add(new ExclusionEntity(UUID.randomUUID(), UUID.randomUUID(), 2, "Red Card"));
  }

  private Map<UUID, ExclusionEntity> exclusionMap = new HashMap<>();

  public void add(ExclusionEntity exclusion) {
    exclusionMap.put(exclusion.getId(), exclusion);
  }

  public Optional<ExclusionEntity> getByPlayerId(UUID playerId) {
    return exclusionMap.values().stream()
            .filter(exclusion -> exclusion.getPlayerId().equals(playerId))
            .findFirst();
  }

  public void removeByPlayerId(UUID playerId) {
    var id = getByPlayerId(playerId);
    id.map(i -> exclusionMap.remove(i.getId()));
  }
}

