package org.example.dao;

import org.example.model.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private Connection conexion;

    public PersonaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public int guardarPersona(Persona persona) throws SQLException {
        String sql = "INSERT INTO PERSONA (nombre, apellidos, email, tipo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, persona.getNombres());
            statement.setString(2, persona.getApellidos());
            statement.setString(3, persona.getEmail());
            statement.setString(4, "PERSONA");
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<Persona> obtenerPersonas() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM PERSONA";

        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                personas.add(new Persona(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email")
                ));
            }
        }
        return personas;
    }

    public Persona buscarPorId(int id) {
        String sql = "SELECT * FROM PERSONA WHERE id_persona = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Persona(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar persona: " + e.getMessage());
        }
        return null;
    }
}
