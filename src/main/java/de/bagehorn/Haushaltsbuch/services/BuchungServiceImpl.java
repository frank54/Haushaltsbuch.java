package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Buchung;
import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.exceptions.NotFoundException;
import de.bagehorn.Haushaltsbuch.repositories.BuchungRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class BuchungServiceImpl implements BuchungService {
    private static final Logger log = LoggerFactory.getLogger(BuchungServiceImpl.class);

    private final BuchungRepository buchungRepository;
    private final KategorieService kategorieService;
    private final FilterService<Buchung> filterService;

    @Override
    public Iterable<Buchung> findAll() {
        return buchungRepository.findAll();
    }

    @Override
    public Iterable<Buchung> findByKategorie(String kategorieName) {
        log.info("Suche Katehorie mit Namen {}.", kategorieName);
        Kategorie kategorie = kategorieService.findByName(kategorieName).orElseThrow(NotFoundException::new);
        log.info("Kategorie {}: {}", kategorie.getId(), kategorie.getName());
        return filterService.select(findAll(), b -> !b.getKategorie().equals(kategorie));
    }

    @Override
    public Buchung addBuchung(Buchung neueBuchung) { return buchungRepository.save(neueBuchung); }
}
