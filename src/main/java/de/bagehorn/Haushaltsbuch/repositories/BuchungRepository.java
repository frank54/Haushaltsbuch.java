package de.bagehorn.Haushaltsbuch.repositories;

import de.bagehorn.Haushaltsbuch.entities.Buchung;
import org.springframework.data.repository.CrudRepository;

public interface BuchungRepository extends CrudRepository<Buchung, Long> {
}
