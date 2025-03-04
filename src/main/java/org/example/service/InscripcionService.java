package org.example.service;

import org.example.dao.InscripcionDAO;
import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Inscripcion;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class InscripcionService {
    private final InscripcionDAO inscripcionDAO;
    private final CursoService cursoService;
    private final EstudianteService estudianteService;

    public InscripcionService(InscripcionDAO inscripcionDAO, CursoService cursoService, EstudianteService estudianteService) {
        this.inscripcionDAO = inscripcionDAO;
        this.cursoService = cursoService;
        this.estudianteService = estudianteService;
    }

    public void guardarInscripcion(Inscripcion inscripcion) {
        inscripcionDAO.guardarInscripcion(inscripcion);
    }

    public Inscripcion obtenerInscripcionPorId(int id) {
        Optional<Inscripcion> optionalInscripcion = inscripcionDAO.obtenerInscripcionPorId(id);
        if (optionalInscripcion.isPresent()) {
            Inscripcion inscripcion = optionalInscripcion.get();
            Estudiante estudiante = estudianteService.obtenerEstudiantePorId(inscripcion.getEstudiante().getID());
            Curso curso = cursoService.obtenerCursoPorId(inscripcion.getCurso().getID());
            inscripcion.setEstudiante(estudiante);
            inscripcion.setCurso(curso);
            return inscripcion;
        }else{
            System.out.println("Inscripcion "+ id +" no encontrado");
            return null;
        }
    }

    public List<Inscripcion> obtenerListaInscripciones() {
        return inscripcionDAO.obtenerListaInscripciones();
    }

    public boolean eliminarInscripcion(int id) {
        return inscripcionDAO.eliminarInscripcion(id);
    }

    public boolean actualizarInscripcion(Inscripcion inscripcion) {
        return inscripcionDAO.actualizarInscripcion(inscripcion);
    }
}