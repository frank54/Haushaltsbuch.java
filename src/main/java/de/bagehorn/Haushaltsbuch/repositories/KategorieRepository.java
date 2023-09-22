package de.bagehorn.Haushaltsbuch.repositories;

import de.bagehorn.Haushaltsbuch.entities.Kategorie;
import org.springframework.data.repository.CrudRepository;

public interface KategorieRepository extends CrudRepository<Kategorie, Long> {
}
