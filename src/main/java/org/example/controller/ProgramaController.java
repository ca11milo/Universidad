package org.example.controller;

import org.example.model.Curso;
import org.example.model.Programa;
import org.example.service.ProgramaService;

import java.util.List;

public class ProgramaController {
    private final ProgramaService programaService;

    public ProgramaController(ProgramaService programaService) {
        this.programaService = programaService;
    }

    public void guardarPrograma(Programa programa) {
        programaService.guardarPrograma(programa);
    }

    public Programa obtenerProgramaPorId(int id) {
        return programaService.obtenerProgramaPorId(id);
    }

    public List<Programa> obtenerListaProgramas() {
        return programaService.obtenerListaProgramas();
    }

    public boolean eliminarPrograma(int id) {
        return programaService.eliminarPrograma(id);
    }

    public boolean actualizarPrograma(Programa programa) {
        return programaService.actualizarPrograma(programa);
    }
}
