package org.example.controller;

import org.example.model.Curso;
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

    public String eliminarPersona(int id) {
        if(personaService.eliminarPersona(id)){
            return ("Persona "+id+" eliminada");
        }else{
            return "No se encontró la Persona";
        }
    }

    public String actualizarPersona(Persona persona) {
        if(personaService.actualizarPersona(persona)){
            return ("Persona "+persona.getID()+" eliminada");
        }else{
            return "No se encontró la Persona";
        }
    }

}