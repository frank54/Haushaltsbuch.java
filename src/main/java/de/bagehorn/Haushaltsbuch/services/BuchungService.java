package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Buchung;

public interface BuchungService {

    Iterable<Buchung> findAll();

}
