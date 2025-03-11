package org.example.controller;

import org.example.model.Curso;
import org.example.model.Inscripcion;
import org.example.service.InscripcionService;

import java.util.List;

public class InscripcionController {
    private InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    public void guardarInscripcion(Inscripcion inscripcion) {
        inscripcionService.guardarInscripcion(inscripcion);
        System.out.println("Inscripción creada con éxito.");
    }

    public Inscripcion obtenerInscripcionPorId(int id) {
        return inscripcionService.obtenerInscripcionPorId(id);
    }

    public List<Inscripcion> obtenerListaInscripciones() {
        return inscripcionService.obtenerListaInscripciones();
    }

    public boolean eliminarInscripcion(int id) {
        return inscripcionService.eliminarInscripcion(id);
    }

    public boolean actualizarInscripcion(Inscripcion inscripcion) {
        return inscripcionService.actualizarInscripcion(inscripcion);
    }
}
