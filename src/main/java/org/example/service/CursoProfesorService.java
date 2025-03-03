package org.example.service;

import org.example.dao.CursoProfesorDAO;
import org.example.model.CursoProfesor;

import java.util.Optional;

public class CursoProfesorService {
    private CursoProfesorDAO cursoProfesorDAO;

    public CursoProfesorService(CursoProfesorDAO cursoProfesorDAO) {
        this.cursoProfesorDAO = cursoProfesorDAO;
    }

    public CursoProfesor guardarCursoProfesor(CursoProfesor cursoProfesor) {
        int id = cursoProfesorDAO.guardarCursoProfesor(cursoProfesor);
        if (id > 0) {
            cursoProfesor.setId(id);
            return cursoProfesor;
        }
        throw new RuntimeException("Error al guardar Curso-Profesor");
    }

    public Optional<CursoProfesor> obtenerCursoProfesorPorId(int id) {
        return cursoProfesorDAO.obtenerCursoProfesorPorId(id);
    }
}