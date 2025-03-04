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

    public String eliminarPrograma(int id) {
        if(programaService.eliminarPrograma(id)){
            return ("Programa "+id+" Eliminado");
        }else{
            return "No se encontró el Programa";
        }
    }

    public String actualizarPrograma(Programa programa) {
        if(programaService.actualizarPrograma(programa)){
            return ("Programa "+programa.getID()+" Eliminado");
        }else{
            return "No se encontró el Programa";
        }
    }
}
