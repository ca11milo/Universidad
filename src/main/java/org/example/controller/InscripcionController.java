package org.example.controller;

import org.example.model.Curso;
import org.example.model.Inscripcion;
import org.example.patterns.observer.Observable;
import org.example.patterns.observer.Observer;
import org.example.service.InscripcionService;

import java.util.ArrayList;
import java.util.List;

public class InscripcionController implements Observable {
    private InscripcionService inscripcionService;
    List<Observer> observadores = new ArrayList<>();

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    public void guardarInscripcion(Inscripcion inscripcion) {
        inscripcionService.guardarInscripcion(inscripcion);
        System.out.println("Inscripción creada con éxito.");
        notificarObservadores();
    }

    public Inscripcion obtenerInscripcionPorId(int id) {
        return inscripcionService.obtenerInscripcionPorId(id);
    }

    public List<Inscripcion> obtenerListaInscripciones() {
        return inscripcionService.obtenerListaInscripciones();
    }

    public boolean eliminarInscripcion(int id) {
        boolean eliminado = inscripcionService.eliminarInscripcion(id);
        notificarObservadores();
        return eliminado;
    }

    public boolean actualizarInscripcion(Inscripcion inscripcion) {
        boolean actualizado = inscripcionService.actualizarInscripcion(inscripcion);
        notificarObservadores();
        return actualizado;
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
