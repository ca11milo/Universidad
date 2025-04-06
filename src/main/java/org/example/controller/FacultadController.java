package org.example.controller;

import org.example.model.Facultad;
import org.example.patterns.observer.*;
import org.example.service.FacultadService;

import java.util.ArrayList;
import java.util.List;

public class FacultadController implements Observable {
    private FacultadService facultadService;
    private List<Observer> observadores = new ArrayList<>();

    public FacultadController(FacultadService facultadService) {
        this.facultadService = facultadService;
    }

    public void guardarFacultad(Facultad facultad) {
        facultadService.guardarFacultad(facultad);
        System.out.println("Facultad creada con éxito.");
        notificarObservadores();
    }

    public Facultad obtenerFacultadPorId(int id) {
        return facultadService.obtenerFacultadPorId(id);
    }

    public List<Facultad> obtenerListaFaculdades() {
        return facultadService.obtenerListaFacultades();
    }

    public boolean eliminarFacultad(int id) {
        notificarObservadores();
        return facultadService.eliminarFacultad(id);

    }

    public boolean actualizarFacultad(Facultad facultad) {
        notificarObservadores();
        return facultadService.actualizarFacultad(facultad);
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