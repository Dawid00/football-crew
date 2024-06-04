package org.example.footballcrew.domain.footballer.exclusion;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ExclusionEntity {
  private UUID id;
  private UUID playerId;
  private Integer matches;
  private String reason;
}
