package org.example.dao;

import org.example.model.Facultad;
import org.example.model.Persona;
import org.example.service.PersonaService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacultadDAO {
    private Connection conexion;
    PersonaService personaService;

    public FacultadDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
    }

    public void guardarFacultad(Facultad facultad) {
        String query = "INSERT INTO FACULTAD (nombre, id_decano) VALUES (?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, facultad.getNombre());
            statement.setInt(2, facultad.getDecano().getID());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        facultad.setID(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar facultad: " + e.getMessage());
        }
    }

    public Optional<Facultad> obtenerFacultadPorId(int id) {
        String query = "SELECT * FROM FACULTAD WHERE id_facultad = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idDecano = resultSet.getInt("id_decano");
                Persona decano = personaService.obtenerPersonaPorId(idDecano);
                return Optional.of(new Facultad(resultSet.getInt("id_facultad"), resultSet.getString("nombre"), decano));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener facultad: " + e.getMessage());
        }
        return Optional.empty();
    }


    public List<Facultad> obtenerListaFacultades() {
        List<Facultad> facultades = new ArrayList<>();
        String query = "SELECT * FROM FACULTAD";
        try (Statement statement = conexion.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id_facultad");
                String nombre = resultSet.getString("nombre");
                int id_decano = resultSet.getInt("id_decano");

                Persona decano = personaService.obtenerPersonaPorId(id_decano);

                facultades.add(new Facultad(id, nombre, decano));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener facultades: " + e.getMessage());
        }
        return facultades;
    }

    public boolean actualizarFacultad(Facultad facultad) {
        String query = "UPDATE FACULTAD SET nombre = ?, id_decano = ? WHERE id_facultad = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, facultad.getNombre());
            statement.setInt(2, facultad.getDecano().getID());
            statement.setInt(3, facultad.getID());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar facultad: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarFacultad(int id) {
        String query = "DELETE FROM FACULTAD WHERE id_facultad = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.err.println("No se puede eliminar la facultad: está relacionado con otros registros.");
            } else {
                System.err.println("Error al eliminar facultad: " + e.getMessage());
            }
            return false;
        }
    }
}
