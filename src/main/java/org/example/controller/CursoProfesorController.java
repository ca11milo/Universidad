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

    public boolean eliminarCursoProfesor(int id) {
        return cursoProfesorService.eliminarCursoProfesor(id);

    }

    public boolean actualizarCursoProfesor(CursoProfesor cursoProfesor) {
        return cursoProfesorService.actualizarCursoProfesor(cursoProfesor);
    }
}
