package org.example.controller;

import org.example.model.Curso;
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

    public List<Estudiante> obtenerListaEstudiantes() {
        return estudianteService.obtenerListaEstudiantes();
    }

    public String eliminarEstudiante(int id) {
        if(estudianteService.eliminarEstudiante(id)){
            return ("Estudiante "+id+" Eliminado");
        }else{
            return "No se encontró el esudiante";
        }
    }

    public String actualizarEstudiante(Estudiante estudiante) {
        if(estudianteService.actualizarEstudiante(estudiante)){
            return ("Estudiante "+estudiante.getID()+" Actualizado");
        }else{
            return "No se encontró el curso";
        }
    }
}