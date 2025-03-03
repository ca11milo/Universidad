package org.example.dao;

import org.example.model.Estudiante;
import org.example.model.Profesor;
import org.example.model.Programa;

import java.sql.*;

public class ProfesorDAO {
    private Connection conexion;

    public ProfesorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public int guardarProfesor(Profesor profesor) throws SQLException {
        String sql = "INSERT INTO PERSONA (nombre, apellidos, email, tipo, tipo_contrato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, profesor.getNombres());
            statement.setString(2, profesor.getApellidos());
            statement.setString(3, profesor.getEmail());
            statement.setString(4, "PROFESOR");
            statement.setString(5, profesor.getTipoContrato());
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }
        return -1;
    }
    public Profesor buscarPorId(int id) {
        String sql = "SELECT * FROM PERSONA WHERE id_persona = ? AND tipo = 'PROFESOR'";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Profesor(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getString("tipo_contrato")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar profesor: " + e.getMessage());
        }
        return null;
    }
}
