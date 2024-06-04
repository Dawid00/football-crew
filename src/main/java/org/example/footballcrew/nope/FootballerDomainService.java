package org.example.footballcrew.nope;

import lombok.AllArgsConstructor;
import org.example.footballcrew.domain.footballer.Footballer;


@AllArgsConstructor
public class FootballerDomainService {

    private final DomainEventPublisher publisher;

    public void yellowCard(Footballer player) {
        player.handleYellowCard();
    }

    public void redCard(Footballer player) {
        player.handleRedCard();
    }


}
