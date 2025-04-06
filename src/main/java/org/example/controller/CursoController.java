package org.example.controller;

import org.example.model.Curso;
import org.example.patterns.observer.Observable;
import org.example.patterns.observer.Observer;
import org.example.service.CursoService;


import java.util.ArrayList;
import java.util.List;

public class CursoController implements Observable {
    private final CursoService cursoService;
    private List<Observer> observadores = new ArrayList<>();

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public void guardarCurso(Curso curso) {
        cursoService.guardarCurso(curso);
        notificarObservadores();
    }

    public Curso obtenerCursoPorId(int id) {
        return cursoService.obtenerCursoPorId(id);
    }

    public List<Curso> obtenerListaCursos() {
        return cursoService.obtenerListaCursos();
    }

    public boolean eliminarCurso(int id) {
        boolean eliminado = cursoService.eliminarCurso(id);
        notificarObservadores();
        return eliminado;
    }

    public boolean actualizarCurso(Curso curso) {
        boolean actualizado = cursoService.actualizarCurso(curso);
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
