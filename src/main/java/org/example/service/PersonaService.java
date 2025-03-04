package org.example.service;

import org.example.dao.PersonaDAO;
import org.example.model.Persona;
import java.util.List;
import java.util.Optional;

public class PersonaService {
    private PersonaDAO personaDAO;

    public PersonaService(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    public void guardarPersona(Persona persona){
        personaDAO.guardarPersona(persona);
    }

    public Persona obtenerPersonaPorId(int id) {
        Optional<Persona> persona = personaDAO.obtenerPersonaPorId(id);
        if (persona.isPresent()) {
            return persona.get();
        }else{
            System.out.println("Persona "+ id +" no encontrado");
            return null;
        }
    }

    public List<Persona> obtenerListaPersonas(){
        return personaDAO.obtenerListaPersonas();
    }

    public boolean eliminarPersona(int id) {
        return personaDAO.eliminarPersona(id);
    }

    public boolean actualizarPersona(Persona persona) {
        return personaDAO.actualizarPersona(persona);
    }
}
