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

    public String eliminarCurso(int id) {
        if(cursoService.eliminarCurso(id)){
            return ("Curso "+id+" Eliminado");
        }else{
            return "No se encontró el curso";
        }
    }

    public String actualizarCurso(Curso curso) {
        if(cursoService.actualizarCurso(curso)){
            return ("Curso "+curso.getID()+" Eliminado");
        }else{
            return "No se encontró el curso";
        }
    }
}
