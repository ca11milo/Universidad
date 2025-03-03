package org.example.controller;

import org.example.model.Persona;
import org.example.service.PersonaService;

import java.sql.SQLException;

public class PersonaController {
    private PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    public void crearPersona(Persona persona) throws SQLException {
        personaService.registrarPersona(persona);
        System.out.println("Persona creada con éxito.");
    }

}