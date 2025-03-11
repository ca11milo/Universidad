package org.example.controller;

import org.example.model.Curso;
import org.example.model.Profesor;
import org.example.service.ProfesorService;

import java.util.List;

public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    public void guardarProfesor(Profesor profesor){
        profesorService.guardarProfesor(profesor);
        System.out.println("Profesor creado con éxito.");
    }

    public Profesor obtenerProfesorPorId(int id){
        return profesorService.obtenerProfesorPorId(id);
    }

    public List<Profesor> obtenerListaProfesores(){
        return profesorService.obtenerListaProfesores();
    }

    public boolean eliminarProfesor(int id) {
        return profesorService.eliminarProfesor(id);
    }

    public boolean actualizarProfesor(Profesor profesor) {
        return profesorService.actualizarProfesor(profesor);
    }

}