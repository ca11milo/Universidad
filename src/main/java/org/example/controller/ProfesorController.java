package org.example.controller;

import org.example.model.Profesor;
import org.example.patterns.observer.Observable;
import org.example.patterns.observer.Observer;
import org.example.service.ProfesorService;

import java.util.ArrayList;
import java.util.List;

public class ProfesorController implements Observable {
    private final ProfesorService profesorService;
    List<Observer> observadores = new ArrayList<>();

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    public void guardarProfesor(Profesor profesor){
        profesorService.guardarProfesor(profesor);
        System.out.println("Profesor creado con éxito.");
        notificarObservadores();
    }

    public Profesor obtenerProfesorPorId(int id){
        return profesorService.obtenerProfesorPorId(id);
    }

    public List<Profesor> obtenerListaProfesores(){
        return profesorService.obtenerListaProfesores();
    }

    public boolean eliminarProfesor(int id) {
        boolean eliminado = profesorService.eliminarProfesor(id);
        notificarObservadores();
        return eliminado;
    }

    public boolean actualizarProfesor(Profesor profesor) {
        boolean actualizado = profesorService.actualizarProfesor(profesor);
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
        for (Observer o : observadores) {
            o.actualizar();
        }
    }
}