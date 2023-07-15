package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.repositories.KategorieRepository;

import java.util.function.Predicate;

public class KategorieServiceImpl implements KategorieService {

    KategorieRepository kategorieRepository;
    FilterService<Kategorie> filterService;

    public KategorieServiceImpl(KategorieRepository kategorieRepository) {
        this.kategorieRepository = kategorieRepository;
        this.filterService = new FilterServiceImpl<>();
    }

    @Override
    public Iterable<Kategorie> findAll() { return kategorieRepository.findAll(); }

    @Override
    public Kategorie findByName(String name)
    {
        Iterable<Kategorie> gefiltert = filterService.select(findAll(), k -> k.getName().equals(name));
        if (gefiltert.iterator().hasNext()) {
            return gefiltert.iterator().next();
        }
        return null;
    }
}
