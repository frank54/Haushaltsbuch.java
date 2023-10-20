package de.bagehorn.Haushaltsbuch.bootstrap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.bagehorn.Haushaltsbuch.entities.Buchung;
import de.bagehorn.Haushaltsbuch.entities.Kategorie;
import de.bagehorn.Haushaltsbuch.exceptions.NotFoundException;
import de.bagehorn.Haushaltsbuch.repositories.BuchungRepository;
import de.bagehorn.Haushaltsbuch.repositories.KategorieRepository;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

import de.bagehorn.Haushaltsbuch.services.KategorieService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BootstrapData implements CommandLineRunner {
    private final BuchungRepository buchungRepository;
    private final KategorieRepository kategorieRepository;
    private final KategorieService kategorieService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Dateninitialisierung...");

        // Lese Kategorien aus JSON Datei
        InputStream inputFile = BootstrapData.class.getResourceAsStream("/kategorien.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, String>> kategorien = objectMapper.readValue(inputFile, new TypeReference<>(){});
        for (int index = 1; index <= 21; index++) {
            String key = String.valueOf(index);
            Map<String, String> kategorie = kategorien.get(key);
            Kategorie neueKategorie = Kategorie.builder()
                    .position(index)
                    .name(kategorie.get("Name"))
                    .beschreibung(kategorie.get("Beschreibung"))
                    .typ(kategorie.get("Typ"))
                    .build();
            kategorieRepository.save(neueKategorie);
        }

        // Füge Buchungen dazu
        Kategorie kategorie = kategorieService.findByName("Frank").orElseThrow(NotFoundException::new);
        Buchung buchung = Buchung.builder()
                .beschreibung("IBM Salär")
                .betrag(15000)
                .datum(new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-25"))
                .kategorie(kategorie)
                .build();
        buchungRepository.save(buchung);
        kategorie = kategorieService.findByName("Wohnen / Haus / Garten").orElseThrow(NotFoundException::new);
        buchung = Buchung.builder()
                .beschreibung("Hypothek")
                .betrag(900)
                .datum(new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-02"))
                .kategorie(kategorie)
                .build();
        buchungRepository.save(buchung);

        System.out.println("Anzahl Kategorien: " + kategorieRepository.count());
        System.out.println("Anzahl Buchungen: " + buchungRepository.count());
    }
}
