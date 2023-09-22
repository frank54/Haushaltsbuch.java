package de.bagehorn.Haushaltsbuch.controllers;

import de.bagehorn.Haushaltsbuch.domain.Buchung;
import de.bagehorn.Haushaltsbuch.services.BuchungService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/buchungen")
public class BuchungController {
    private static final Logger log = LoggerFactory.getLogger(BuchungController.class);
    private final BuchungService buchungService;

    @GetMapping(value = "{kategorieName}")
    public List<Buchung> getBuchungenByKategorie(@PathVariable("kategorieName") String name) {
        List<Buchung> returnList = new ArrayList<>();
        buchungService.findByKategorie(name).forEach(returnList::add);
        return returnList;
    }

    @GetMapping
//    Methode um eine Webseite mit Thymeleaf zu erzeugen
//    public String getBuchungen(Model model) {
//        model.addAttribute("buchungen", buchungService.findAll());
//        return "buchungen";
//    }
    public ResponseEntity<List<Buchung>> getBuchungen() {
        List<Buchung> returnList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        buchungService.findAll().forEach(returnList::add);
        return new ResponseEntity<>(returnList, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Buchung> addBuchung(@RequestBody Buchung buchung) {
        Buchung neueBuchung = buchungService.addBuchung(buchung);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/buchung/" + buchung.getId().toString());

        return new ResponseEntity<>(buchung, headers, HttpStatus.CREATED);
    }
}
