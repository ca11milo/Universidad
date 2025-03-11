package org.example.dao;

import org.example.model.Estudiante;
import org.example.model.Profesor;
import org.example.model.Programa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesorDAO {
    private Connection conexion;

    public ProfesorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarProfesor(Profesor profesor){
        String query = "INSERT INTO PERSONA (nombre, apellidos, email, tipo, tipo_contrato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, profesor.getNombres());
            statement.setString(2, profesor.getApellidos());
            statement.setString(3, profesor.getEmail());
            statement.setString(4, "PROFESOR");
            statement.setString(5, profesor.getTipoContrato());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        profesor.setID(keys.getInt(1));
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("Error al guardar profesor: " + e.getMessage());
        }
    }
    public Optional<Profesor> obtenerProfesorPorId(int id) {
        String sql = "SELECT * FROM PERSONA WHERE id_persona = ? AND tipo = 'PROFESOR'";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new Profesor(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getString("tipo_contrato")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar profesor: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Profesor> obtenerListaProfesores(){
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM PERSONA WHERE tipo = 'PROFESOR'";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                profesores.add(new Profesor(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getString("tipo_contrato")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener profesores: " + e.getMessage());
        }
        return profesores;
    }

    public boolean actualizarProfesor(Profesor profesor) {
        String query = "UPDATE PERSONA SET nombre = ?, apellidos = ?, email = ?, tipo_contrato = ? WHERE id_persona = ? AND tipo = 'PROFESOR'";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, profesor.getNombres());
            statement.setString(2, profesor.getApellidos());
            statement.setString(3, profesor.getEmail());
            statement.setString(4, profesor.getTipoContrato());
            statement.setInt(5, profesor.getID());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar profesor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProfesor(int id) {
        String query = "DELETE FROM PERSONA WHERE id_persona = ? AND tipo = 'PROFESOR'";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar profesor: " + e.getMessage());
            return false;
        }
    }

}