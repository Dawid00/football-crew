package org.example.footballcrew.player;

import org.example.footballcrew.domain.footballer.Footballer;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FootballerTest {

  @Test
  public void shouldNotExcludedAfter1stYellowCard() {
    Footballer player = new Footballer(UUID.randomUUID(), LocalDateTime.of(2000,1,1,21,1));
    player.handleYellowCard();

    assertFalse(player.isExcluded());
  }

  @Test
  public void shouldExcludedAfterRedCard() {
    Footballer player = new Footballer(UUID.randomUUID(), LocalDateTime.of(2000,1,1,21,1));
    player.handleRedCard();

    assertTrue(player.isExcluded());
  }

  @Test
  public void shouldExcludedAfter4thYellowCard() {
    Footballer player = new Footballer(UUID.randomUUID(), LocalDateTime.of(2000,1,1,21,1));
    player.handleYellowCard();
    player.handleYellowCard();
    player.handleYellowCard();
    player.handleYellowCard();

    assertTrue(player.isExcluded());
  }


  @Test
  public void shouldNotBeExcludedAfterMatch() {
    Footballer player = new Footballer(UUID.randomUUID(), LocalDateTime.of(2000,1,1,21,1));
    player.handleYellowCard();
    player.handleYellowCard();
    player.handleYellowCard();
    player.handleYellowCard();
    player.handleMatch();

    assertFalse(player.isExcluded());
  }

  @Test
  public void shouldBeExcludedWhenOneMatchLeft() {
    Footballer player = new Footballer(UUID.randomUUID(), LocalDateTime.of(2000,1,1,21,1));
    player.handleRedCard();
    player.handleMatch();

    assertTrue(player.isExcluded());
  }

  @Test
  public void shouldBeNotBeExcludedAfterTwoMatches() {
    Footballer player = new Footballer(UUID.randomUUID(), LocalDateTime.of(2000,1,1,21,1));
    player.handleRedCard();
    player.handleMatch();
    player.handleMatch();

    assertFalse(player.isExcluded());
  }

}