package org.example.footballcrew.domain.footballer;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.function.Predicate;

@AllArgsConstructor
public class YouthFootballerSpecification implements Predicate<LocalDateTime> {
    private final Integer youthAge;

    public boolean isSatisfiedBy(Footballer footballer) {
        return test(footballer.getBornDate());
    }

    @Override
    public boolean test(LocalDateTime date) {
        Period p = Period.between(LocalDate.from(date), LocalDate.now());
        return p.getYears() <= youthAge;
    }
}
