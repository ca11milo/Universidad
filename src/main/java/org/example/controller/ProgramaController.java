package org.example.controller;

import org.example.model.Programa;
import org.example.service.ProgramaService;

public class ProgramaController {
    private ProgramaService programaService;

    public ProgramaController(ProgramaService programaService) {
        this.programaService = programaService;
    }

    public void crearPrograma(Programa programa) {
        programaService.registrarPrograma(programa);
        System.out.println("Programa creado con éxito.");
    }
}
