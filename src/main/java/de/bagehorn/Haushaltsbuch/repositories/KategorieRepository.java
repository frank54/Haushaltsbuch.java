package de.bagehorn.Haushaltsbuch.repositories;

import de.bagehorn.Haushaltsbuch.entities.Kategorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorieRepository extends JpaRepository<Kategorie, Long> {
}
