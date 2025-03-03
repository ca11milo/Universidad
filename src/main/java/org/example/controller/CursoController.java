package org.example.controller;

import org.example.model.Curso;
import org.example.service.CursoService;

import java.util.List;

public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public void crearCurso(Curso curso) {
        cursoService.agregarCurso(curso);
    }

    public Curso obtenerCurso(int id) {
        return cursoService.obtenerCurso(id);
    }

    public List<Curso> listarCursos() {
        return cursoService.obtenerTodosLosCursos();
    }
}
