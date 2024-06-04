package org.example.footballcrew.team;

import lombok.AllArgsConstructor;
import org.example.footballcrew.domain.footballer.Footballer;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TeamMembers {
    private final List<Footballer> players = new ArrayList<>();
    public List<Footballer> getPlayers() {
        return players;
    }
}
