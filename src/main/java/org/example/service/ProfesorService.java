package org.example.service;

import org.example.dao.EstudianteDAO;
import org.example.dao.ProfesorDAO;
import org.example.model.Estudiante;
import org.example.model.Profesor;

import java.sql.SQLException;

public class ProfesorService {
    private ProfesorDAO profesorDAO;

    public ProfesorService(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }

    public void registrarProfesor(Profesor profesor) throws SQLException {

        int id = profesorDAO.guardarProfesor(profesor);
        profesor.setID(id);
    }
}
