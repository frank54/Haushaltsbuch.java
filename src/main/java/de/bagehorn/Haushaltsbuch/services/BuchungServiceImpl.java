package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Buchung;
import de.bagehorn.Haushaltsbuch.repositories.BuchungRepository;
import org.springframework.stereotype.Service;

@Service
public class BuchungServiceImpl implements BuchungService {

    BuchungRepository buchungRepository;

    public BuchungServiceImpl(BuchungRepository buchungRepository) {
        this.buchungRepository = buchungRepository;
    }

    @Override
    public Iterable<Buchung> findAll() {
        return buchungRepository.findAll();
    }
}
