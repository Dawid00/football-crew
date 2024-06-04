package org.example.footballcrew.domain.footballer;

import java.util.List;
import java.util.UUID;

public interface FootballerDatabase {
    Footballer findFootballerById(UUID id);
    List<Footballer> findFootballers();
    void save(Footballer footballer);
    void update(Footballer footballer);
}
