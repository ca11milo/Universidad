package org.example.service;

import org.example.dao.EstudianteDAO;
import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Programa;

import java.util.List;
import java.util.Optional;

public class EstudianteService {
    private final EstudianteDAO estudianteDAO;
    private final ProgramaService programaService;

    public EstudianteService(EstudianteDAO estudianteDAO, ProgramaService programaService) {
        this.estudianteDAO = estudianteDAO;
        this.programaService = programaService;
    }

    public void guardarEstudiante(Estudiante estudiante){
       estudianteDAO.guardarEstudiante(estudiante);
    }

    public Estudiante obtenerEstudiantePorId(int id) {
        Optional<Estudiante> optionalEstudiante = estudianteDAO.obtenerEstudiantePorId(id);
        if (optionalEstudiante.isPresent()) {
            Estudiante estudiante = optionalEstudiante.get();
            Programa programa = programaService.obtenerProgramaPorId(estudiante.getPrograma().getID());
            estudiante.setPrograma(programa);
            return estudiante;
        }else{
            System.out.println("Estudiante "+ id +" no encontrado");
            return null;
        }
    }

    public Estudiante obtenerEstudiantePorCodigo(Double codigo) {
        Optional<Estudiante> optionalEstudiante = estudianteDAO.obtenerEstudiantePorCodigo(codigo);
        if (optionalEstudiante.isPresent()) {
            Estudiante estudiante = optionalEstudiante.get();
            Programa programa = programaService.obtenerProgramaPorId(estudiante.getPrograma().getID());
            estudiante.setPrograma(programa);
            return estudiante;
        }else{
            System.out.println("Estudiante de codigo "+ codigo +" no encontrado");
            return null;
        }
    }

    public List<Estudiante> obtenerListaEstudiantes() {

        List<Estudiante> listaEstudiante = estudianteDAO.obtenerListaEstudiantes();
        for(Estudiante estudiante : listaEstudiante) {
            Programa programa = programaService.obtenerProgramaPorId(estudiante.getPrograma().getID());
            estudiante.setPrograma(programa);
        }
        return listaEstudiante;
    }

    public boolean eliminarEstudiante(int id) {
        return estudianteDAO.eliminarEstudiante(id);
    }

    public boolean actualizarEstudiante(Estudiante estudiante) {
        return estudianteDAO.actualizarEstudiante(estudiante);
    }


}
