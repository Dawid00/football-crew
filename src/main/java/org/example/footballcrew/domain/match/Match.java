package org.example.footballcrew.domain.match;


import org.example.footballcrew.domain.minute.Minute;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Match {
  UUID id();

  MatchType type();

  LocalDateTime at();

  Long gameId();

  MatchStatus state();

  void played(Minute minute);

  void cancel();

  void submit();

  void postpone();

}
