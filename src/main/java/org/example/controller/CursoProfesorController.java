package org.example.controller;

import org.example.model.Curso;
import org.example.model.CursoProfesor;
import org.example.patterns.observer.Observable;
import org.example.patterns.observer.Observer;
import org.example.service.CursoProfesorService;

import java.util.ArrayList;
import java.util.List;

public class CursoProfesorController implements Observable {
    private final CursoProfesorService cursoProfesorService;
    private List<Observer> observadores = new ArrayList<>();

    public CursoProfesorController(CursoProfesorService cursoProfesorService) {
        this.cursoProfesorService = cursoProfesorService;
    }

    public void guardarCursoProfesor(CursoProfesor cursoProfesor) {
        cursoProfesorService.guardarCursoProfesor(cursoProfesor);
        notificarObservadores();
        System.out.println("CursoProfesor creado con éxito.");
    }

    public CursoProfesor obtenerCursoProfesorPorId(int id) {
        return cursoProfesorService.obtenerCursoProfesorPorId(id);
    }

    public List<CursoProfesor> obtenerListaCursosProfesor() {
        return cursoProfesorService.obtenerListaCursosProfesor();
    }

    public boolean eliminarCursoProfesor(int id) {
        notificarObservadores();
        return cursoProfesorService.eliminarCursoProfesor(id);

    }

    public boolean actualizarCursoProfesor(CursoProfesor cursoProfesor) {
        notificarObservadores();
        return cursoProfesorService.actualizarCursoProfesor(cursoProfesor);
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
