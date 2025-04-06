package org.example.controller;

import org.example.model.Estudiante;
import org.example.service.EstudianteService;

import java.sql.SQLException;
import java.util.List;

public class EstudianteController {
    private EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {

        this.estudianteService = estudianteService;
    }

    public void guardarEstudiante(Estudiante estudiante) throws SQLException {
        estudianteService.guardarEstudiante(estudiante);
        System.out.println("Estudiante creado con éxito.");
    }
    public Estudiante obtenerEstudiantePorId(int id) {
        return estudianteService.obtenerEstudiantePorId(id);
    }

    public Estudiante obtenerEstudiantePorCodigo(Double codigo) {
        return estudianteService.obtenerEstudiantePorCodigo(codigo);
    }

    public List<Estudiante> obtenerListaEstudiantes() {
        return estudianteService.obtenerListaEstudiantes();
    }

    public boolean eliminarEstudiante(int id) {
        return estudianteService.eliminarEstudiante(id);
    }

    public boolean actualizarEstudiante(Estudiante estudiante) {
        return estudianteService.actualizarEstudiante(estudiante);
    }
}