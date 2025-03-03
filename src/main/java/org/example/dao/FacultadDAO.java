package org.example.dao;

import org.example.model.Facultad;
import org.example.model.Persona;
import org.example.service.PersonaService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadDAO {
    private Connection conexion;
    PersonaService personaService;

    public FacultadDAO(Connection conexion) {
        this.conexion = conexion;
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

    public List<Facultad> obtenerFacultades() {
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
}
