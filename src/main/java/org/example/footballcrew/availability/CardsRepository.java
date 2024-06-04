package org.example.footballcrew.availability;

import org.example.footballcrew.common.Util;
import org.example.footballcrew.domain.footballer.Card;
import org.example.footballcrew.domain.footballer.Footballer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import static org.example.footballcrew.availability.CardType.YELLOW;

@Service
public class CardsRepository {

    private HashMap<UUID, CardEntity> cards = new HashMap<>();
    private HashMap<UUID, Footballer> players = new HashMap<>();

    public CardsRepository() {
        var player1 = new Footballer(Util.player1Id, LocalDateTime.of(2000, 2, 12, 20, 0, 0));
        var player2 = new Footballer(Util.player2Id, LocalDateTime.of(2000, 2, 12, 20, 0, 0));
        var player3 = new Footballer(Util.player3Id, LocalDateTime.of(2000, 2, 12, 20, 0, 0));
        players.put(player1.getId(), player1);
        players.put(player2.getId(), player2);
        players.put(player3.getId(), player3);
        var first = new CardEntity(Util.card1Id, player1.getId(), new Card(1, CardType.RED));
        var second = new CardEntity(Util.card2Id, player2.getId(), new Card(2, YELLOW));
        var third = new CardEntity(Util.card3Id, player3.getId(), new Card(3, YELLOW));
        this.cards.put(first.getId(), first);
        this.cards.put(second.getId(), second);
        this.cards.put(third.getId(), third);
    }
//  private final DSLContext database;
//
//
//  public CardsRepository(DataSource dataSource) {
//    database = DSL.using(dataSource.getConnection(), SQLDialect.H2,
//            new Settings().withExecuteWithOptimisticLocking(true));
//  }

//  public void addCard(UUID playerId, Card card) {
//    var cardEntity = getCardEntity(playerId, card);
//    if (cardEntity.isPresent()) {
//      var quantity = cardEntity.get().getCard().getQuantity() + card.getQuantity();
//      cardEntity.get().setCard(new Card(card.getCardType(), quantity));
//      cards.put(cardEntity.get().getId(), cardEntity.get());
//    } else {
//      var newEntity = new CardEntity(UUID.randomUUID(), playerId, card);
//      cards.put(newEntity.getId(), newEntity);
//    }
//  }
//
//  public Optional<CardEntity> getCardEntity(UUID playerId, Card card) {
//    return cards.values().stream()
//            .filter(c -> c.getCard().getCardType().equals(card.getCardType()))
//            .filter(ce -> ce.getFootballerId().equals(playerId))
//            .findFirst();
//  }
//    database.insertInto(
//                    CARDS,
//                    CARDS.PLAYER_ID,
//                    CARDS.QUANTITY,
//                    CARDS.COLOR,
//                    CARDS.VERSION)
//            .values(
//                    playerId,
//                    card.getQuantity(),
//                    card.getCardType(),
//                    0).
//            execute();
//  }

}
