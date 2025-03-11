package org.example.controller;

import org.example.model.Curso;
import org.example.model.Facultad;
import org.example.service.FacultadService;
import java.util.List;

public class FacultadController {
    private FacultadService facultadService;

    public FacultadController(FacultadService facultadService) {
        this.facultadService = facultadService;
    }

    public void guardarFacultad(Facultad facultad) {
        facultadService.guardarFacultad(facultad);
        System.out.println("Facultad creada con éxito.");
    }

    public Facultad obtenerFacultadPorId(int id) {
        return facultadService.obtenerFacultadPorId(id);
    }

    public List<Facultad> obtenerListaFaculdades() {
        return facultadService.obtenerListaFacultades();
    }

    public boolean eliminarFacultad(int id) {
        return facultadService.eliminarFacultad(id);

    }

    public boolean actualizarFacultad(Facultad facultad) {
        return facultadService.actualizarFacultad(facultad);
    }
}