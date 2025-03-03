package org.example.controller;

import org.example.model.Estudiante;
import org.example.service.EstudianteService;

import java.sql.SQLException;

public class EstudianteController {
    private EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    public void crearEstudiante(Estudiante estudiante) throws SQLException {
        estudianteService.registrarEstudiante(estudiante);
        System.out.println("Estudiante creado con éxito.");
    }

}