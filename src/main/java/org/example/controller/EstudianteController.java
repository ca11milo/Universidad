package org.example.controller;

import org.example.model.Estudiante;
import org.example.patterns.observer.Observable;
import org.example.patterns.observer.Observer;
import org.example.service.EstudianteService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteController implements Observable {
    private EstudianteService estudianteService;
    private List<Observer> observadores = new ArrayList<>();

    public EstudianteController(EstudianteService estudianteService) {

        this.estudianteService = estudianteService;
    }

    public void guardarEstudiante(Estudiante estudiante) throws SQLException {
        estudianteService.guardarEstudiante(estudiante);
        notificarObservadores();
        System.out.println("Estudiante creado con éxito.");
    }
    public Estudiante obtenerEstudiantePorId(int id) {
        return estudianteService.obtenerEstudiantePorId(id);
    }

    public Estudiante obtenerEstudiantePorCodigo(Double codigo) {
        return estudianteService.obtenerEstudiantePorCodigo(codigo);
    }

    public List<Estudiante> obtenerListaEstudiantes() {
        return estudianteService.obtenerListaEstudiantes();
    }

    public boolean eliminarEstudiante(int id) {
        boolean eliminado = estudianteService.eliminarEstudiante(id);
        notificarObservadores();
        return eliminado;
    }

    public boolean actualizarEstudiante(Estudiante estudiante) {

        boolean actualizado = estudianteService.actualizarEstudiante(estudiante);
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