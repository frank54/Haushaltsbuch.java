package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Kategorie;

import java.util.Optional;

public interface KategorieService {

    Iterable<Kategorie> findAll();
    Optional<Kategorie> findByName(String name);

}
