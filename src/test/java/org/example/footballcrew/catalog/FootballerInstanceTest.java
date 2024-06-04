package org.example.footballcrew.catalog;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FootballerInstanceTest {

  @Test
  public void shouldReturnTrueWhenInjured() {
    var footballer = new FootballerInstance();
    footballer.getInjured(LocalDate.MAX, "Ankle twisted", "faul");
    assertTrue(footballer.injured());
  }

  @Test
  public void shouldReturnFalseWhenNoInjury() {
    var footballer = new FootballerInstance();
    assertFalse(footballer.injured());
  }

  @Test
  public void shouldReturnFalseAfterRecovery() {
    var footballer = new FootballerInstance();
    footballer.getInjured(LocalDate.MAX, "Ankle twisted", "faul");
    footballer.recovered(footballer.getInjuries().stream().findFirst().get().getId());
    assertFalse(footballer.injured());
  }

}