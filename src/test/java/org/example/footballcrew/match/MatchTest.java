package org.example.footballcrew.match;

import org.example.footballcrew.domain.footballer.Footballer;
import org.example.footballcrew.domain.footballer.Squad;
import org.example.footballcrew.domain.team.CurrentTeam;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchTest {

    @Test
    public void shouldConfirmMatch() {
        Squad squad = new Squad(
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 21, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 21, 0))
                ),
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0))
                ),
                Set.of()
        );
        CurrentTeam currentTeam = new CurrentTeam();
        currentTeam.confirmSquad(squad);
    }

    @Test
    public void shouldNotConfirmMatchWhenMoreThan7Subs() {
        Squad squad = new Squad(
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 21, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 21, 0))
                ),
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0))
                ),
                Set.of()
        );
        CurrentTeam currentTeam = new CurrentTeam();
        currentTeam.confirmSquad(squad);
        assertThrows(RuntimeException.class, () -> currentTeam.confirmSquad(squad));
    }

    @Test
    public void shouldNotConfirmMatchWhenMoreLessThan7OnThePitch() {
        Squad squad = new Squad(
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 21, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 21, 0))
                ),
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0))
                ),
                Set.of()
        );
        CurrentTeam currentTeam = new CurrentTeam();
        currentTeam.confirmSquad(squad);
        assertThrows(RuntimeException.class, () -> currentTeam.confirmSquad(squad));
    }

    @Test
    public void shouldNotConfirmMatchWhenMoreThan11OnThePitch() {
        Squad squad = new Squad(
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 21, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 21, 0))
                ),
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0))
                ),
                Set.of()
        );
        CurrentTeam currentTeam = new CurrentTeam();
        currentTeam.confirmSquad(squad);
        assertThrows(RuntimeException.class, () -> currentTeam.confirmSquad(squad));
    }

    @Test
    public void shouldNotConfirmMatchWhenLessThan2Youth() {
        Squad squad = new Squad(
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0))
                ),
                Set.of(
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0)),
                        new Footballer(UUID.randomUUID(), LocalDateTime.of(2004, 5, 12, 23, 0))
                ),
                Set.of()
        );
        CurrentTeam currentTeam = new CurrentTeam();
        currentTeam.confirmSquad(squad);
        assertThrows(RuntimeException.class, () -> currentTeam.confirmSquad(squad));
    }
}