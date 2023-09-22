package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Kategorie;

import java.util.Optional;

public interface KategorieService {

    public Iterable<Kategorie> findAll();
    public Optional<Kategorie> findByName(String name);

}
