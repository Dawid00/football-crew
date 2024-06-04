package org.example.footballcrew.availability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.example.footballcrew.domain.footballer.Card;

import java.util.UUID;
@AllArgsConstructor
@Data
@ToString
public class CardEntity{
  private UUID id;
  private UUID playerId;
  @Setter
  private Card card;

}
