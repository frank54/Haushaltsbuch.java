package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Buchung;
import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.repositories.BuchungRepository;
import de.bagehorn.Haushaltsbuch.repositories.KategorieRepository;
import org.springframework.stereotype.Service;

@Service
public class BuchungServiceImpl implements BuchungService {

    BuchungRepository buchungRepository;
    KategorieRepository kategorieRepository;
    KategorieService kategorieService;
    FilterService<Buchung> filterService;

    public BuchungServiceImpl(BuchungRepository buchungRepository, KategorieRepository kategorieRepository) {
        this.buchungRepository = buchungRepository;
        this.kategorieRepository = kategorieRepository;
        this.kategorieService = new KategorieServiceImpl(this.kategorieRepository);
        this.filterService = new FilterServiceImpl<>();
    }

    @Override
    public Iterable<Buchung> findAll() {
        return buchungRepository.findAll();
    }

    @Override
    public Iterable<Buchung> findByKategorie(String kategorieName) {
        Kategorie kategorie = kategorieService.findByName(kategorieName);
        return filterService.select(findAll(), b -> b.getKategorie().equals(kategorie));
    }
}
