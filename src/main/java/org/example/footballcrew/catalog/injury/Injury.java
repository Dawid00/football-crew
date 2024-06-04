package org.example.footballcrew.catalog.injury;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Injury {
  @Id
  @Getter
  private UUID id;
  @Getter
  private LocalDate till;
  private String name;
  private String reason;
  @Getter
  private InjuryStatus status;

  public Injury(LocalDate till, String name, String reason) {
    this.till = till;
    this.id = UUID.randomUUID();
    this.name = name;
    this.reason = reason;
    this.status = InjuryStatus.CURRENT;
  }

  public Injury(UUID id, LocalDate till, String name, String reason, InjuryStatus status) {
    this.id = id;
    this.till = till;
    this.name = name;
    this.reason = reason;
    this.status = status;
  }

  public Injury() {

  }

  public void changeTill(LocalDate till) {
    this.till = till;
  }

  public void recover() {
    this.status = InjuryStatus.RECOVERED;
  }

}