package org.example.DAO;

import org.example.model.Facultad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacultadDAO {

    private Connection conexion;

    public FacultadDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarFacultad(Facultad facultad) {

        String queryBase = "INSERT INTO FACULTAD (id_facultad, nombre, id_decano) VALUES (?, ?, ?)";
        try(PreparedStatement insertFacultadStatement = conexion.prepareStatement(queryBase)) {

            insertFacultadStatement.setDouble(1, facultad.getID());
            insertFacultadStatement.setString(2, facultad.getNombre());
            insertFacultadStatement.setDouble(3, facultad.getDecano().getID());

            int filasAfectadas = insertFacultadStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Facultad guardada correctamente, ID: " + facultad.getID());
            }

        }catch (SQLException exception) {
            System.err.println("Error al guardar facultad: " + exception.getMessage());
        }
    }

}
