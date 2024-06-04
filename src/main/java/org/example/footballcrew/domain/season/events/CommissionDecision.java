package org.example.footballcrew.domain.season.events;

import java.util.UUID;

public record CommissionDecision(UUID playerId, Integer quantity) {
}
