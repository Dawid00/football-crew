package org.example.footballcrew.common.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExclusionDto(UUID footballerId, LocalDateTime till, Boolean active, Integer quantity, String type){}
