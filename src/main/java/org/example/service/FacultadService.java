package org.example.service;

import org.example.dao.FacultadDAO;
import org.example.model.Facultad;
import org.example.model.Persona;

import java.sql.Connection;
import java.util.List;

public class FacultadService {
    private FacultadDAO facultadDAO;

    public FacultadService(Connection conexion) {
        this.facultadDAO = new FacultadDAO(conexion);
    }

    public void registrarFacultad(Facultad facultad) {
        if (facultad.getNombre() == null || facultad.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la facultad no puede estar vacío");
        }
        facultadDAO.guardarFacultad(facultad);
    }

    public List<Facultad> obtenerTodasLasFacultades() {
        return facultadDAO.obtenerFacultades();
    }
}