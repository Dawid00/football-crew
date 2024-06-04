package org.example.footballcrew.catalog;

import java.time.LocalDate;
import java.util.UUID;

public record InjuryReportDto(UUID footballerId,  LocalDate till,
         String name,
         String reason) {
}
