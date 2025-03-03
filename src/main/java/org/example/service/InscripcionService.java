package org.example.service;

import org.example.dao.InscripcionDAO;
import org.example.model.Inscripcion;
import java.sql.Connection;

public class InscripcionService {
    private InscripcionDAO inscripcionDAO;

    public InscripcionService(Connection conexion) {
        this.inscripcionDAO = new InscripcionDAO(conexion);
    }

    public void registrarInscripcion(Inscripcion inscripcion) {
        if (inscripcion.getCurso() == null || inscripcion.getEstudiante() == null) {
            throw new IllegalArgumentException("Curso y estudiante son obligatorios para la inscripción");
        }
        inscripcionDAO.guardarInscripcion(inscripcion);
    }
}