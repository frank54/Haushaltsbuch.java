package de.bagehorn.Haushaltsbuch.repositories;

import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import org.springframework.data.repository.CrudRepository;

public interface KategorieRepository extends CrudRepository<Kategorie, Long> {
}
