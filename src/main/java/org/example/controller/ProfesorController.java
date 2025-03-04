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

    public String eliminarProfesor(int id) {
        if(profesorService.eliminarProfesor(id)){
            return ("Profesor "+id+" Eliminado");
        }else{
            return "No se encontró el Profesor";
        }
    }

    public String actualizarProfesor(Profesor profesor) {
        if(profesorService.actualizarProfesor(profesor)){
            return ("Profesor "+profesor.getID()+" Eliminado");
        }else{
            return "No se encontró el Profesor";
        }
    }

}