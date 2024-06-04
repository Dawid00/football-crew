package org.example.footballcrew.app;

import lombok.AllArgsConstructor;
import org.example.footballcrew.domain.minute.Minute;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FootballerApplicationService {
    private final TeamDatabase teamDatabase;
}
