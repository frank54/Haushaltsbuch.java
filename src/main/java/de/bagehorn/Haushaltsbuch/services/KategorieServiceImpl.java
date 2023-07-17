package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.repositories.KategorieRepository;
import org.springframework.stereotype.Service;

@Service
public class KategorieServiceImpl implements KategorieService {

    private final KategorieRepository kategorieRepository;
    private final FilterService<Kategorie> filterService;

    public KategorieServiceImpl(KategorieRepository kategorieRepository, FilterService<Kategorie> filterService) {
        this.kategorieRepository = kategorieRepository;
        this.filterService = filterService;
    }

    @Override
    public Iterable<Kategorie> findAll() { return kategorieRepository.findAll(); }

    @Override
    public Kategorie findByName(String name)
    {
        Iterable<Kategorie> gefiltert = filterService.select(findAll(), k -> !k.getName().equals(name));
        if (gefiltert.iterator().hasNext()) {
            return gefiltert.iterator().next();
        }
        return null;
    }
}
