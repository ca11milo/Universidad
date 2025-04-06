package org.example.service;

import org.example.dao.CursoDAO;
import org.example.model.Curso;
import org.example.model.Programa;

import java.util.List;
import java.util.Optional;

public class CursoService {
    private final CursoDAO cursoDAO;
    private final ProgramaService programaService;

    public CursoService(CursoDAO cursoDAO, ProgramaService programaService) {
        this.cursoDAO = cursoDAO;
        this.programaService = programaService;
    }

    public void guardarCurso(Curso curso) {
        cursoDAO.guardarCurso(curso);
    }

    public Curso obtenerCursoPorId(int id) {
        Optional<Curso> optionalCurso = cursoDAO.obtenerCursoPorId(id);
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            Programa programa = programaService.obtenerProgramaPorId(curso.getPrograma().getID());
            curso.setPrograma(programa);
            return curso;
        }else{
            System.out.println("Curso "+ id +" no encontrado");
            return null;
        }
    }

    public List<Curso> obtenerListaCursos() {

        List<Curso> listaCursos = cursoDAO.obtenerListaCursos();
        for(Curso curso : listaCursos) {
            Programa programa = programaService.obtenerProgramaPorId(curso.getPrograma().getID());
            curso.setPrograma(programa);
        }
        return listaCursos;
    }

    public boolean eliminarCurso(int id) {
        return cursoDAO.eliminarCurso(id);
    }

    public boolean actualizarCurso(Curso curso) {
        return cursoDAO.actualizarCurso(curso);
    }
}
