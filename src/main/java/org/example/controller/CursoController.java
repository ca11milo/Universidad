package org.example.controller;

import org.example.model.Curso;
import org.example.service.CursoService;

import java.util.List;

public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public void guardarCurso(Curso curso) {
        cursoService.guardarCurso(curso);
    }

    public Curso obtenerCursoPorId(int id) {
        return cursoService.obtenerCursoPorId(id);
    }

    public List<Curso> obtenerListaCursos() {
        return cursoService.obtenerListaCursos();
    }

    public boolean eliminarCurso(int id) {
        return cursoService.eliminarCurso(id);

    }

    public boolean actualizarCurso(Curso curso) {
        return cursoService.actualizarCurso(curso);
    }
}
