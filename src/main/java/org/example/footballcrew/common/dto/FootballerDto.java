package org.example.footballcrew.common.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record FootballerDto(UUID id, LocalDateTime bornDate, Integer yellowCards, Integer redCards, Boolean isYouth, Boolean isExcluded, List<ExclusionDto> exclusions){}
