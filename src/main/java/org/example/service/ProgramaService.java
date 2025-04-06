package org.example.service;

import org.example.dao.ProgramaDAO;
import org.example.model.Curso;
import org.example.model.Facultad;
import org.example.model.Programa;

import java.util.List;
import java.util.Optional;

public class ProgramaService {
    private final ProgramaDAO programaDAO;
    private final FacultadService facultadService;

    public ProgramaService(ProgramaDAO programaDAO, FacultadService facultadService) {
        this.programaDAO = programaDAO;
        this.facultadService = facultadService;
    }

    public void guardarPrograma(Programa programa) {
        programaDAO.guardarPrograma(programa);
    }

    public Programa obtenerProgramaPorId(int id) {
        Optional<Programa> optionalPrograma = programaDAO.obtenerProgramaPorId(id);
        if (optionalPrograma.isPresent()) {
            Programa programa = optionalPrograma.get();
            Facultad facultad = facultadService.obtenerFacultadPorId(programa.getFacultad().getID());
            programa.setFacultad(facultad);
            return programa;
        }else{
            System.out.println("Programa "+ id +" no encontrado");
            return null;
        }
    }

    public List<Programa> obtenerListaProgramas() {
        List<Programa> listaPrograma = programaDAO.obtenerListaProgramas();
        for(Programa programa : listaPrograma) {
            Facultad facultad = facultadService.obtenerFacultadPorId(programa.getFacultad().getID());
            programa.setFacultad(facultad);
        }
        return listaPrograma;
    }

    public boolean eliminarPrograma(int id) {
        return programaDAO.eliminarPrograma(id);
    }

    public boolean actualizarPrograma(Programa programa) {
        return programaDAO.actualizarPrograma(programa);
    }
}
