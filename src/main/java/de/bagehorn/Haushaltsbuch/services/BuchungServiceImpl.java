package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Buchung;
import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.repositories.BuchungRepository;
import org.springframework.stereotype.Service;

@Service
public class BuchungServiceImpl implements BuchungService {

    private final BuchungRepository buchungRepository;
    private final KategorieService kategorieService;
    private final FilterService<Buchung> filterService;

    public BuchungServiceImpl(BuchungRepository buchungRepository, KategorieService kategorieService, FilterService<Buchung> filterService) {
        this.buchungRepository = buchungRepository;
        this.kategorieService = kategorieService;
        this.filterService = filterService;
    }

    @Override
    public Iterable<Buchung> findAll() {
        return buchungRepository.findAll();
    }

    @Override
    public Iterable<Buchung> findByKategorie(String kategorieName) {
        Kategorie kategorie = kategorieService.findByName(kategorieName);
        return filterService.select(findAll(), b -> !b.getKategorie().equals(kategorie));
    }
}
