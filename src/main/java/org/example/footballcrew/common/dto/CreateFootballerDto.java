package org.example.footballcrew.common.dto;

import java.time.LocalDate;

public record CreateFootballerDto(
        LocalDate bornDate
) {
}
