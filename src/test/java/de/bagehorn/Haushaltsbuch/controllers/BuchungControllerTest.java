package de.bagehorn.Haushaltsbuch.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.bagehorn.Haushaltsbuch.domain.Buchung;
import de.bagehorn.Haushaltsbuch.domain.Kategorie;
import de.bagehorn.Haushaltsbuch.exceptions.NotFoundException;
import de.bagehorn.Haushaltsbuch.services.BuchungService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BuchungController.class)
public class BuchungControllerTest {
    @Autowired
    MockMvc mockmvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BuchungService buchungService;

    private Kategorie testKat;
    private Buchung testBuchung;
    private Buchung[] testBuchungen;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        String testKatStr = "{\"id\": 1, \"name\": \"Test\", \"position\": 1, \"typ\": \"Ausgaben\", \"beschreibung\": \"\"}";
        testKat = objectMapper.readValue(testKatStr, Kategorie.class);
        String testBuchungStr = "{\"id\": 1, \"betrag\": 666.65, \"datum\": \"2023-04-05\", \"beschreibung\": \"\", " +
                "\"kategorie\": " + objectMapper.writeValueAsString(testKat) + "}";
        testBuchung = objectMapper.readValue(testBuchungStr, Buchung.class);
        testBuchungen = new Buchung[]{testBuchung};
    }

    @Test
    void testGetBuchungen() throws Exception {
        given(buchungService.findAll()).willReturn(Arrays.asList(testBuchungen));
        mockmvc.perform(get("/api/v1/buchungen")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetBuchungByKategorie() throws Exception {
        given(buchungService.findByKategorie(testKat.getName())).willReturn(Arrays.asList(testBuchungen));
        mockmvc.perform(get("/api/v1/buchungen/" + testKat.getName())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetBuchungByKategorieKategorieNotFound() throws Exception {
        given(buchungService.findByKategorie(testKat.getName())).willThrow(NotFoundException.class);
        mockmvc.perform(get("/api/v1/buchungen/" + testKat.getName())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
