package org.example.footballcrew.domain.footballer.ports;

import org.example.footballcrew.domain.footballer.Footballer;

import java.util.List;
import java.util.UUID;

public interface FootballerDatabase {
    Footballer getFootballerById(UUID id);
    List<Footballer> getFootballers();
    void save(Footballer footballer);
    void update(Footballer footballer);
}
