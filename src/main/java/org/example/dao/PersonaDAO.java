package org.example.dao;

import org.example.model.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonaDAO {
    private Connection conexion;

    public PersonaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarPersona(Persona persona) {
        String query = "INSERT INTO PERSONA (nombre, apellidos, email, tipo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, persona.getNombres());
            statement.setString(2, persona.getApellidos());
            statement.setString(3, persona.getEmail());
            statement.setString(4, "PERSONA");
            statement.executeUpdate();

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        persona.setID(keys.getInt(1));
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("Error al guardar persona: " + e.getMessage());
        }
    }

    public Optional<Persona> obtenerPersonaPorId(int id) {
        String query = "SELECT * FROM PERSONA WHERE id_persona = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Persona(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar persona: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Persona> obtenerListaPersonas(){
        List<Persona> personas = new ArrayList<>();
        String query = "SELECT * FROM PERSONA";

        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                personas.add(new Persona(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email")
                ));
            }
        }catch (SQLException e){
            System.err.println("Error al buscar personas: " + e.getMessage());
        }
        return personas;
    }

    public boolean actualizarPersona(Persona persona) {
        String query = "UPDATE PERSONA SET nombre = ?, apellidos = ?, email = ? WHERE id_persona = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, persona.getNombres());
            statement.setString(2, persona.getApellidos());
            statement.setString(3, persona.getEmail());
            statement.setInt(4, persona.getID());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar persona: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPersona(int id) {
        String query = "DELETE FROM PERSONA WHERE id_persona = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar persona: " + e.getMessage());
            return false;
        }
    }

}
