package de.bagehorn.Haushaltsbuch.controllers;

import de.bagehorn.Haushaltsbuch.services.BuchungService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuchungController {
    private final BuchungService buchungService;

    public BuchungController(BuchungService buchungService) {
        this.buchungService = buchungService;
    }

    @RequestMapping("/buchungen")
    public String getBuchungen(Model model) {
        model.addAttribute("buchungen", buchungService.findAll());
        return "buchungen";
    }
}
