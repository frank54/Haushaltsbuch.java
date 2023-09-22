package de.bagehorn.Haushaltsbuch.services;

import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.repositories.KategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class KategorieServiceImpl implements KategorieService {

    private final KategorieRepository kategorieRepository;
    private final FilterService<Kategorie> filterService;

    @Override
    public Iterable<Kategorie> findAll() { return kategorieRepository.findAll(); }

    @Override
    public Optional<Kategorie> findByName(String name)
    {
        Iterable<Kategorie> gefiltert = filterService.select(findAll(), k -> !k.getName().equals(name));
        return Optional.of(gefiltert.iterator().next());
    }
}
