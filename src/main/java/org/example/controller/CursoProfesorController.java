package org.example.controller;

import org.example.model.CursoProfesor;
import org.example.service.CursoProfesorService;

import java.util.Optional;

public class CursoProfesorController {
    private CursoProfesorService cursoProfesorService;

    public CursoProfesorController(CursoProfesorService cursoProfesorService) {
        this.cursoProfesorService = cursoProfesorService;
    }

    public CursoProfesor crearCursoProfesor(CursoProfesor cursoProfesor) {
        return cursoProfesorService.guardarCursoProfesor(cursoProfesor);
    }

    public Optional<CursoProfesor> obtenerCursoProfesor(int id) {
        return cursoProfesorService.obtenerCursoProfesorPorId(id);
    }
}
