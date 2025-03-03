package org.example.service;

import org.example.dao.PersonaDAO;
import org.example.model.Persona;
import java.sql.SQLException;
import java.util.List;

public class PersonaService {
    private PersonaDAO personaDAO;

    public PersonaService(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    public void registrarPersona(Persona persona) throws SQLException {
        if (persona.getNombres() == null || persona.getNombres().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        int id = personaDAO.guardarPersona(persona);
        persona.setID(id);
    }

    public List<Persona> obtenerPersonas() throws SQLException {
        return personaDAO.obtenerPersonas();
    }

    public Persona obtenerPersonaPorId(int id) throws SQLException {
        return personaDAO.buscarPorId(id);
    }
}
