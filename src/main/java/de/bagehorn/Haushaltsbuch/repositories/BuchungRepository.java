package de.bagehorn.Haushaltsbuch.repositories;

import de.bagehorn.Haushaltsbuch.entities.Buchung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BuchungRepository extends JpaRepository<Buchung, UUID> {
}
