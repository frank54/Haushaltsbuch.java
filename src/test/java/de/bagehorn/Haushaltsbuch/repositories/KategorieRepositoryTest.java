package de.bagehorn.Haushaltsbuch.repositories;

import de.bagehorn.Haushaltsbuch.entities.Kategorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class KategorieRepositoryTest {

    @Autowired
    KategorieRepository kategorieRepository;
    Kategorie testKategorie;

    @BeforeEach
    void setUp() {
        testKategorie = kategorieRepository.save(Kategorie.builder()
                .name("Test")
                .beschreibung("Test")
                .position(0)
                .typ("Ausgaben")
                .build());
    }

    @Test
    void testFindAll() {
        assert testKategorie.equals(kategorieRepository.findAll().get(0));
    }

    @Test
    void testSaveKategorie() {
        Kategorie neueKategorie = kategorieRepository.save(Kategorie.builder()
                .name("Test2")
                .typ("Einnahmen")
                .build());
        assertThat(neueKategorie).isNotNull();
        assertThat(neueKategorie.getId()).isNotNull();
        assertThat(kategorieRepository.findAll().size()).isEqualTo(2);
    }
}