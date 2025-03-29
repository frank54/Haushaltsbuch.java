package de.bagehorn.Haushaltsbuch.bootstrap;

import de.bagehorn.Haushaltsbuch.domain.Buchung;
import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.exceptions.NotFoundException;
import de.bagehorn.Haushaltsbuch.repositories.BuchungRepository;
import de.bagehorn.Haushaltsbuch.repositories.KategorieRepository;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import de.bagehorn.Haushaltsbuch.services.KategorieService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.json.JSONTokener;
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
        JSONTokener tokener = new JSONTokener(inputFile);
        JSONObject kategorien = new JSONObject(tokener);
        for (int index = 1; index <= 21; index++) {
            String key = String.valueOf(index);
            JSONObject kategorie = kategorien.getJSONObject(key);
            Kategorie neueKategorie = Kategorie.builder()
                    .position(index)
                    .name(kategorie.getString("Name"))
                    .beschreibung(kategorie.getString("Beschreibung"))
                    .typ(kategorie.getString("Typ"))
                    .build();
            kategorieRepository.save(neueKategorie);
        }

        // Füge Buchungen dazu
        Kategorie kategorie = kategorieService.findByName("Frank").orElseThrow(NotFoundException::new);
        Buchung buchung = Buchung.builder()
                .beschreibung("Salär")
                .betrag(5000)
                .datum(new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-25"))
                .kategorie(kategorie)
                .build();
        buchungRepository.save(buchung);
        kategorie = kategorieService.findByName("Wohnen / Haus / Garten").orElseThrow(NotFoundException::new);
        buchung = Buchung.builder()
                .beschreibung("Hypothek")
                .betrag(500)
                .datum(new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-02"))
                .kategorie(kategorie)
                .build();
        buchungRepository.save(buchung);

        System.out.println("Anzahl Kategorien: " + kategorieRepository.count());
        System.out.println("Anzahl Buchungen: " + buchungRepository.count());
    }
}
