package org.example.footballcrew.domain.footballer;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public final class Squad {

    private final Set<Footballer> starting;
    private final Set<Footballer> subs;
    private final Set<Footballer> absence;


    public Set<Footballer> getStarting() {
        return new HashSet<>(starting);
    }

    public Set<Footballer> getSubs() {
        return new HashSet<>(subs);
    }

    public Set<Footballer> getAbsence() {
        return new HashSet<>(absence);
    }
}
