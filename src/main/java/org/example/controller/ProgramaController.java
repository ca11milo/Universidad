package org.example.controller;

import org.example.model.Programa;
import org.example.patterns.observer.Observable;
import org.example.patterns.observer.Observer;
import org.example.service.ProgramaService;

import java.util.ArrayList;
import java.util.List;

public class ProgramaController implements Observable {
    private final ProgramaService programaService;
    private List<Observer> observadores = new ArrayList<>();

    public ProgramaController(ProgramaService programaService) {
        this.programaService = programaService;
    }

    public void guardarPrograma(Programa programa) {
        programaService.guardarPrograma(programa);
        notificarObservadores();
    }

    public Programa obtenerProgramaPorId(int id) {
        return programaService.obtenerProgramaPorId(id);
    }

    public List<Programa> obtenerListaProgramas() {
        return programaService.obtenerListaProgramas();
    }

    public boolean eliminarPrograma(int id) {
        notificarObservadores();
        return programaService.eliminarPrograma(id);
    }

    public boolean actualizarPrograma(Programa programa) {
        notificarObservadores();
        return programaService.actualizarPrograma(programa);
    }

    @Override
    public void agregarObservador(Observer o) {
        observadores.add(o);
    }

    @Override
    public void eliminarObservador(Observer o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores() {
        for(Observer o : observadores){
            o.actualizar();
        }
    }
}
