package de.bagehorn.Haushaltsbuch.bootstrap;

import de.bagehorn.Haushaltsbuch.domain.Buchung;
import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.repositories.BuchungRepository;
import de.bagehorn.Haushaltsbuch.repositories.KategorieRepository;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.bagehorn.Haushaltsbuch.services.BuchungService;
import de.bagehorn.Haushaltsbuch.services.BuchungServiceImpl;
import de.bagehorn.Haushaltsbuch.services.KategorieService;
import de.bagehorn.Haushaltsbuch.services.KategorieServiceImpl;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final BuchungRepository buchungRepository;
    private final KategorieRepository kategorieRepository;
    private final BuchungService buchungService;
    private final KategorieService kategorieService;

    public BootstrapData(BuchungRepository buchungRepository, KategorieRepository kategorieRepository, BuchungService buchungService, KategorieService kategorieService) {
        this.buchungRepository = buchungRepository;
        this.kategorieRepository = kategorieRepository;
        this.buchungService = buchungService;
        this.kategorieService = kategorieService;
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

        // Füge Buchungen dazu
        Buchung buchung = new Buchung();
        buchung.setBeschreibung("IBM Salär");
        buchung.setBetrag(15000);
        buchung.setDatum(new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-25"));
        buchung.setKategorie(kategorieService.findByName("Frank"));
        buchungRepository.save(buchung);
        buchung = new Buchung();
        buchung.setBeschreibung("Hypothek");
        buchung.setBetrag(900);
        buchung.setDatum(new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-02"));
        buchung.setKategorie(kategorieService.findByName(""));
        buchungRepository.save(buchung);

        System.out.println("Anzahl Kategorien: " + kategorieRepository.count());
        System.out.println("Anzahl Buchungen: " + buchungRepository.count());
        System.out.println();
    }
}
