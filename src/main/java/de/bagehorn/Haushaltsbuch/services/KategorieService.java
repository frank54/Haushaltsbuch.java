package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Kategorie;

public interface KategorieService {

    public Iterable<Kategorie> findAll();
    public Kategorie findByName(String name);

}
