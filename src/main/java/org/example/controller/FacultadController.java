package org.example.controller;

import org.example.model.Facultad;
import org.example.service.FacultadService;
import java.util.List;

public class FacultadController {
    private FacultadService facultadService;

    public FacultadController(FacultadService facultadService) {
        this.facultadService = facultadService;
    }

    public void crearFacultad(Facultad facultad) {
        facultadService.registrarFacultad(facultad);
        System.out.println("Facultad creada con éxito.");
    }

    public List<Facultad> listarFacultades() {
        return facultadService.obtenerTodasLasFacultades();
    }
}