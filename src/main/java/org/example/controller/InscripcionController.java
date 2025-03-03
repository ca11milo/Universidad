package org.example.controller;

import org.example.model.Inscripcion;
import org.example.service.InscripcionService;

public class InscripcionController {
    private InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    public void crearInscripcion(Inscripcion inscripcion) {
        inscripcionService.registrarInscripcion(inscripcion);
        System.out.println("Inscripción creada con éxito.");
    }
}
