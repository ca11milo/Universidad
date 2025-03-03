package org.example.controller;

import org.example.model.Estudiante;
import org.example.model.Profesor;
import org.example.service.EstudianteService;
import org.example.service.ProfesorService;

import java.sql.SQLException;

public class ProfesorController {
    private ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    public void crearProfesor(Profesor profesor) throws SQLException {
        profesorService.registrarProfesor(profesor);
        System.out.println("Profesor creada con éxito.");
    }

}