// DAO: ProgramaDAO.java
package org.example.dao;

import org.example.model.Programa;
import java.sql.*;

public class ProgramaDAO {
    private Connection conexion;

    public ProgramaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarPrograma(Programa programa) {
        String query = "INSERT INTO PROGRAMA (nombre, duracion, registro, id_facultad) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, programa.getNombre());
            statement.setDouble(2, programa.getDuracion());
            statement.setDate(3, new java.sql.Date(programa.getRegistro().getTime()));
            statement.setInt(4, programa.getFacultad().getID());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        programa.setID(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al guardar programa: " + e.getMessage());
        }
    }
}