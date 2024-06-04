package org.example.footballcrew.domain.footballer;

import io.vavr.Function2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Minute {
    private UUID id;
    private UUID matchId;
    private List<CardsMinute> cards = new ArrayList<>();
    private List<Injury> injuries = new ArrayList<>();
    private List<MinutePolicies> minutePolicies = MinutePolicies.all();

    void addCard(UUID playerId, CardsMinute card) {
        if (minutePolicies.stream().map(policy -> policy.apply(cards, card)).anyMatch(s -> s.equals(false)))
            throw new RuntimeException("");
        else {
            this.cards.add(card);
        }
    }

    public List<CardsMinute> getCards() {
        return cards.stream().toList();
    }

    public List<Injury> getInjuries() {
        return injuries.stream().toList();
    }

    void addInjury(UUID playerId) {
        this.injuries.add(new Injury(playerId));
    }
}

interface MinutePolicies extends Function2<List<CardsMinute>, CardsMinute, Boolean> {

    static List<MinutePolicies> all() {
        return List.of(new MinuteYellowCardPolicy(), new MinuteRedCardPolicy());
    }
}

final class MinuteYellowCardPolicy implements MinutePolicies {

    @Override
    public Boolean apply(List<CardsMinute> cardsMinutes, CardsMinute card) {
        return cardsMinutes.stream()
                .filter(c -> c.card().equals(card.card()) && c.playerId().equals(card.playerId()))
                .count() < 2;
    }
}

final class MinuteRedCardPolicy implements MinutePolicies {

    @Override
    public Boolean apply(List<CardsMinute> cardsMinutes, CardsMinute cardsMinute) {
        return !cardsMinutes.contains(cardsMinute);
    }
}

