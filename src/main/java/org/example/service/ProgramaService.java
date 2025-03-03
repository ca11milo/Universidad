package org.example.service;

import org.example.dao.ProgramaDAO;
import org.example.model.Programa;
import java.sql.Connection;

public class ProgramaService {
    private ProgramaDAO programaDAO;

    public ProgramaService(Connection conexion) {
        this.programaDAO = new ProgramaDAO(conexion);
    }

    public void registrarPrograma(Programa programa) {
        if (programa.getNombre() == null || programa.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del programa no puede estar vacío");
        }
        programaDAO.guardarPrograma(programa);
    }
}
