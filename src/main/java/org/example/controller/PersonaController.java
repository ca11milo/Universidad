package org.example.controller;

import org.example.model.Persona;
import org.example.service.PersonaService;

import java.sql.SQLException;
import java.util.List;

public class PersonaController {
    private PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    public void guardarPersona(Persona persona) throws SQLException {
        personaService.guardarPersona(persona);
        System.out.println("Persona creada con éxito.");
    }

    public Persona obtenerPersonaPorId(int id) {
        return personaService.obtenerPersonaPorId(id);
    }

    public List<Persona> obtenerListaPersonas() {
        return personaService.obtenerListaPersonas();
    }

    public boolean eliminarPersona(int id) {
        return personaService.eliminarPersona(id);
    }

    public boolean actualizarPersona(Persona persona) {
        return personaService.actualizarPersona(persona);
    }

}