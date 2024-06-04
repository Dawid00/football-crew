package org.example.footballcrew.match;

import lombok.AllArgsConstructor;
import org.example.footballcrew.domain.footballer.Footballer;
import org.example.footballcrew.team.TeamMembers;

import java.util.List;

@AllArgsConstructor
public class BeforeMatchTeamReport {

    private final TeamMembers teamMembers;

    public List<Footballer> getAvailablePlayersForNextMatch() {
        return teamMembers.getPlayers().stream().filter(player -> !player.isExcluded()).toList();
    }

    public List<Footballer> getExcludedPlayersForNextMatch() {
        return teamMembers.getPlayers().stream().filter(Footballer::isExcluded).toList();
    }

}
