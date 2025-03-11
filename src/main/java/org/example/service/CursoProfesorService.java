package org.example.service;

import org.example.dao.CursoProfesorDAO;
import org.example.model.Curso;
import org.example.model.CursoProfesor;
import org.example.model.Profesor;

import java.util.List;
import java.util.Optional;

public class CursoProfesorService {
    private CursoProfesorDAO cursoProfesorDAO;
    private ProfesorService profesorService;
    private CursoService cursoService;

    public CursoProfesorService(CursoProfesorDAO cursoProfesorDAO) {

        this.cursoProfesorDAO = cursoProfesorDAO;
    }

    public CursoProfesorService(CursoProfesorDAO cursoProfesorDAO, ProfesorService profesorService, CursoService cursoService) {
        this.cursoProfesorDAO = cursoProfesorDAO;
        this.profesorService = profesorService;
        this.cursoService = cursoService;
    }

    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public void setProfesorService(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    public void guardarCursoProfesor(CursoProfesor cursoProfesor) {
        cursoProfesorDAO.guardarCursoProfesor(cursoProfesor);
    }

    public CursoProfesor obtenerCursoProfesorPorId(int id) {
        Optional<CursoProfesor> optionalCursoProfesor = cursoProfesorDAO.obtenerCursoProfesorPorId(id);
        if (optionalCursoProfesor.isPresent()) {
            CursoProfesor cursoProfesor = optionalCursoProfesor.get();
            Profesor profesor = profesorService.obtenerProfesorPorId(cursoProfesor.getProfesor().getID());
            Curso curso = cursoService.obtenerCursoPorId(cursoProfesor.getCurso().getID());
            cursoProfesor.setProfesor(profesor);
            cursoProfesor.setCurso(curso);
            return cursoProfesor;
        }else{
            System.out.println("Profesor "+ id +" no encontrado");
            return null;
        }
    }

    public List<CursoProfesor> obtenerListaCursosProfesor() {
        return cursoProfesorDAO.obtenerCursosProfesoresPorId();
    }

    public boolean eliminarCursoProfesor(int id) {
        return cursoProfesorDAO.eliminarCursoProfesor(id);
    }

    public boolean actualizarCursoProfesor(CursoProfesor cursoProfesor) {
        return cursoProfesorDAO.actualizarCursoProfesor(cursoProfesor);
    }

}