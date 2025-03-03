package org.example.service;

import org.example.dao.EstudianteDAO;
import org.example.model.Estudiante;
import java.sql.SQLException;

public class EstudianteService {
    private EstudianteDAO estudianteDAO;

    public EstudianteService(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    public void registrarEstudiante(Estudiante estudiante) throws SQLException {
        if (estudiante.getPromedio() < 0 || estudiante.getPromedio() > 5) {
            throw new IllegalArgumentException("El promedio debe estar entre 0 y 5");
        }
        int id = estudianteDAO.guardarEstudiante(estudiante);
        estudiante.setID(id);
    }
}
