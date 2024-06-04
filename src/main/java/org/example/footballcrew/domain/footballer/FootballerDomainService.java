package org.example.footballcrew.domain.footballer;


import org.example.footballcrew.nope.DomainEventPublisher;

import java.time.LocalDateTime;

public class FootballerDomainService {

    private final DomainEventPublisher eventPublisher;

    public FootballerDomainService(final DomainEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void nextMatchFinished(final Footballer target) {
        target.handleMatch();
    }

    public void yellowCard(final Footballer target) {
        target.handleYellowCard();
    }

    public void redCard(final Footballer target) {
        target.handleRedCard();
    }

    public void injured(final Footballer target, LocalDateTime till) {
        target.handleInjury(till);
    }


}
