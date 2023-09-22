package de.bagehorn.Haushaltsbuch.repositories;

import de.bagehorn.Haushaltsbuch.entities.Buchung;
import de.bagehorn.Haushaltsbuch.entities.Kategorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BuchungRepositoryTest {
    @Autowired
    BuchungRepository buchungRepository;
    @Autowired
    KategorieRepository kategorieRepository;

    Buchung testBuchung;
    Kategorie testKategorie;

    @BeforeEach
    void setUp() throws ParseException {
        testKategorie = kategorieRepository.save(Kategorie.builder()
                .name("Test")
                .beschreibung("Test")
                .position(0)
                .typ("Ausgaben")
                .build());
        testBuchung = buchungRepository.save(Buchung.builder()
                .beschreibung("Test Buchung")
                .betrag(1000)
                .datum(new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-25"))
                .kategorie(testKategorie)
                .build());
    }

    @Test
    void testFindAll() {
        assertThat(buchungRepository.findAll().get(0)).isEqualTo(testBuchung);
    }

    @Test
    void testSaveBuchung() throws ParseException {
        Buchung neueBuchung = buchungRepository.save(Buchung.builder()
                .beschreibung("Test")
                .betrag(1)
                .datum(new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-31"))
                .kategorie(testKategorie)
                .build());
        assertThat(neueBuchung).isNotNull();
        assertThat(neueBuchung.getId()).isNotNull();
        assertThat(buchungRepository.findAll().size()).isEqualTo(2);
    }
}
