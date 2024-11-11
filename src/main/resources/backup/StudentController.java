package com.example.gestionscolarite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EtudiantController {

    @GetMapping("/etudiant")
    public String etudiantPage(Model model) {
        // Ajouter des données au modèle si nécessaire
        return "etudiant"; // Correspond à etudiant.jsp
    }
}
