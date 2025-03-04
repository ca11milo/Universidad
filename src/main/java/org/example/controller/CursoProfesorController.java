package org.example.controller;

import org.example.model.Curso;
import org.example.model.CursoProfesor;
import org.example.service.CursoProfesorService;

import java.util.List;

public class CursoProfesorController {
    private final CursoProfesorService cursoProfesorService;

    public CursoProfesorController(CursoProfesorService cursoProfesorService) {
        this.cursoProfesorService = cursoProfesorService;
    }

    public void guardarCursoProfesor(CursoProfesor cursoProfesor) {
        cursoProfesorService.guardarCursoProfesor(cursoProfesor);
        System.out.println("CursoProfesor creado con éxito.");
    }

    public CursoProfesor obtenerCursoProfesorPorId(int id) {
        return cursoProfesorService.obtenerCursoProfesorPorId(id);
    }

    public List<CursoProfesor> obtenerListaCursosProfesor() {
        return cursoProfesorService.obtenerListaCursosProfesor();
    }

    public String eliminarCursoProfesor(int id) {
        if(cursoProfesorService.eliminarCursoProfesor(id)){
            return ("Curso-Profesor "+id+" Eliminado");
        }else{
            return "No se encontró el curso-profesor";
        }
    }

    public String actualizarCursoProfesor(CursoProfesor cursoProfesor) {
        if(cursoProfesorService.actualizarCursoProfesor(cursoProfesor)){
            return ("Curso Profesor: "+cursoProfesor.getId()+" Eliminado");
        }else{
            return "No se encontró el curso-profesor";
        }
    }
}
