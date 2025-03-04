package org.example.service;

import org.example.dao.FacultadDAO;
import org.example.model.Facultad;
import org.example.model.Persona;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class FacultadService {
    private final FacultadDAO facultadDAO;
    private final PersonaService personaService;


    public FacultadService(FacultadDAO facultadDAO, PersonaService personaService) {
        this.facultadDAO = facultadDAO;
        this.personaService = personaService;
    }

    public void guardarFacultad(Facultad facultad) {
        facultadDAO.guardarFacultad(facultad);
    }

    public Facultad obtenerFacultadPorId(int id) {
        Optional<Facultad> optionalFacultad = facultadDAO.obtenerFacultadPorId(id);
        if (optionalFacultad.isPresent()) {
            Facultad facultad = optionalFacultad.get();
            Persona decano = personaService.obtenerPersonaPorId(facultad.getDecano().getID());
            facultad.setDecano(decano);
            return facultad;
        }else{
            System.out.println("Facultad "+ id +" no encontrado");
            return null;
        }
    }
    public List<Facultad> obtenerListaFacultades() {
        return facultadDAO.obtenerListaFacultades();
    }

    public boolean eliminarFacultad(int id) {
        return facultadDAO.eliminarFacultad(id);
    }

    public boolean actualizarFacultad(Facultad facultad) {
        return facultadDAO.actualizarFacultad(facultad);
    }
}