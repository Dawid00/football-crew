package org.example.footballcrew.catalog;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.ToString;
import org.example.footballcrew.catalog.injury.Injury;
import org.example.footballcrew.catalog.injury.InjuryStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class FootballerInstance {

  @Id
  @Getter
  private UUID id;
  @Getter
  @OneToMany
  private List<Injury> injuries;

  public FootballerInstance(UUID id, List<Injury> injuries) {
    this.id = id;
    this.injuries = injuries;
  }

  public FootballerInstance() {
    this.id = UUID.randomUUID();
    this.injuries = new ArrayList<>();
  }

  public FootballerInstance(UUID id) {
    this.id = id;
    this.injuries = new ArrayList<>();
  }

  public boolean injured() {
    return injuries.stream().anyMatch(injury -> injury.getStatus().equals(InjuryStatus.CURRENT));
  }

  public boolean injured(LocalDate at) {
    return injuries.stream().anyMatch(s -> s.getTill().isAfter(at));
  }

  public void getInjured(LocalDate till, String name, String reason) {
    this.injuries.add(new Injury(UUID.randomUUID(), till, name, reason, InjuryStatus.CURRENT));
  }

  public void recovered(UUID injuryId) {
    var injury = this.injuries.stream().filter(i -> i.getId().equals(injuryId)).findFirst();
    injury.ifPresent(Injury::recover);
  }
}
