package org.example.controller;

import org.example.model.Persona;
import org.example.patterns.observer.*;
import org.example.service.PersonaService;

import java.util.ArrayList;
import java.util.List;

public class PersonaController implements Observable {
    private PersonaService personaService;
    private List<Observer> observadores = new ArrayList<>();

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    public void guardarPersona(Persona persona){
        personaService.guardarPersona(persona);
        System.out.println("Persona creada con éxito.");
        notificarObservadores();
    }

    public Persona obtenerPersonaPorId(int id) {
        return personaService.obtenerPersonaPorId(id);
    }

    public List<Persona> obtenerListaPersonas() {
        return personaService.obtenerListaPersonas();
    }

    public boolean eliminarPersona(int id) {
        boolean eliminado = personaService.eliminarPersona(id);
        notificarObservadores();
        return eliminado;
    }

    public boolean actualizarPersona(Persona persona) {
        boolean actualizado = personaService.actualizarPersona(persona);
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