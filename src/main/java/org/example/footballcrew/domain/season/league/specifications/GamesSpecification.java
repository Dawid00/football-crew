package org.example.footballcrew.domain.season.league.specifications;

import java.time.LocalDate;
import java.util.function.Predicate;

public interface GamesSpecification extends Predicate<LocalDate> {

}
