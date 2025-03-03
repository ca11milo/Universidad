package org.example.dao;

import org.example.model.Estudiante;
import org.example.model.Programa;

import java.sql.*;

public class EstudianteDAO {
    private Connection conexion;

    public EstudianteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public int guardarEstudiante(Estudiante estudiante) throws SQLException {
        String sql = "INSERT INTO PERSONA (nombre, apellidos, email, tipo, codigo, id_programa, activo, promedio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, estudiante.getNombres());
            statement.setString(2, estudiante.getApellidos());
            statement.setString(3, estudiante.getEmail());
            statement.setString(4, "ESTUDIANTE");
            statement.setDouble(5, estudiante.getCodigo());
            statement.setInt(6, estudiante.getPrograma().getID());
            statement.setBoolean(7, estudiante.getActivo());
            statement.setDouble(8, estudiante.getPromedio());
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }
        return -1;
    }

    public Estudiante buscarPorId(int id) {
        String sql = "SELECT * FROM PERSONA WHERE id_persona = ? AND tipo = 'ESTUDIANTE'";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Estudiante(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getDouble("codigo"),
                        new Programa(resultSet.getInt("id_programa")),
                        resultSet.getBoolean("activo"),
                        resultSet.getDouble("promedio")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiante: " + e.getMessage());
        }
        return null;
    }
}
