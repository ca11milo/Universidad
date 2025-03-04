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

    public String eliminarFacultad(int id) {
        if(facultadService.eliminarFacultad(id)){
            return ("Facultad "+id+" Eliminada");
        }else{
            return "No se encontró facultad";
        }
    }

    public String actualizarFacultad(Facultad facultad) {
        if(facultadService.actualizarFacultad(facultad)){
            return ("Facultad "+facultad.getID()+" Eliminada");
        }else{
            return "No se encontró la facultad";
        }
    }
}