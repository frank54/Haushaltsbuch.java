package de.bagehorn.Haushaltsbuch.bootstrap;

import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.repositories.BuchungRepository;
import de.bagehorn.Haushaltsbuch.repositories.KategorieRepository;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private BuchungRepository buchungRepository;
    private KategorieRepository kategorieRepository;

    public BootstrapData(BuchungRepository buchungRepository, KategorieRepository kategorieRepository) {
        this.buchungRepository = buchungRepository;
        this.kategorieRepository = kategorieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Dateninitialisierung...");

        // Lese Kategorien aus JSON Datei
        InputStream inputFile = BootstrapData.class.getResourceAsStream("/kategorien.json");
        JSONTokener tokener = new JSONTokener(inputFile);
        JSONObject kategorien = new JSONObject(tokener);
        for (String key : kategorien.keySet()) {
            JSONObject kategorie = kategorien.getJSONObject(key);
            Kategorie neueKategorie = new Kategorie();
            neueKategorie.setPosition(Integer.parseInt(key));
            neueKategorie.setName(kategorie.getString("Name"));
            neueKategorie.setBeschreibung(kategorie.getString("Beschreibung"));
            neueKategorie.setTyp(kategorie.getString("Typ"));
            kategorieRepository.save(neueKategorie);
        }

        System.out.println("Anzahl Kategorien: " + kategorieRepository.count());
        System.out.println("Anzahl Buchungen: " + buchungRepository.count());
    }
}
