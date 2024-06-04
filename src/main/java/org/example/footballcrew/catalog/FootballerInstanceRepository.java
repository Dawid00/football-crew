package org.example.footballcrew.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FootballerInstanceRepository extends JpaRepository< FootballerInstance, UUID> {
}
