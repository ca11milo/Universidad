package org.example.service;

import org.example.dao.CursoDAO;
import org.example.model.Curso;

import java.util.List;

public class CursoService {
    private final CursoDAO cursoDAO;

    public CursoService(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public void agregarCurso(Curso curso) {
        cursoDAO.guardarCurso(curso);
    }

    public Curso obtenerCurso(int id) {
        return cursoDAO.obtenerCursoPorId(id);
    }

    public List<Curso> obtenerTodosLosCursos() {
        return cursoDAO.obtenerCursos();
    }
}
