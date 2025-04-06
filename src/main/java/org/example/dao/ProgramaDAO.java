
package org.example.dao;

import org.example.model.Facultad;
import org.example.model.Programa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Programa> obtenerProgramaPorId(int id){
        String query = "SELECT * FROM PROGRAMA WHERE id_programa = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of( new Programa(
                        resultSet.getInt("id_programa"),
                        resultSet.getString("nombre"),
                        resultSet.getDate("registro"),
                        resultSet.getDouble("duracion"),
                        new Facultad(resultSet.getInt("id_facultad"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el programa: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Programa> obtenerListaProgramas() {
        List<Programa> programas = new ArrayList<>();
        String query = "SELECT * FROM PROGRAMA";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                programas.add(new Programa(
                        resultSet.getInt("id_programa"),
                        resultSet.getString("nombre"),
                        resultSet.getDate("registro"),
                        resultSet.getDouble("duracion"),
                        new Facultad(resultSet.getInt("id_facultad"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el programa: " + e.getMessage());
        }
        return programas;
    }

    public boolean actualizarPrograma(Programa programa) {
        String query = "UPDATE PROGRAMA SET nombre = ?, duracion = ?, registro = ?, id_facultad = ? WHERE id_programa = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, programa.getNombre());
            statement.setDouble(2, programa.getDuracion());
            statement.setDate(3, new java.sql.Date(programa.getRegistro().getTime()));
            statement.setInt(4, programa.getFacultad().getID());
            statement.setInt(5, programa.getID());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar programa: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPrograma(int id) {
        String query = "DELETE FROM PROGRAMA WHERE id_programa = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.err.println("No se puede eliminar el programa: está relacionado con otros registros.");
            } else {
                System.err.println("Error al eliminar programa: " + e.getMessage());
            }
            return false;
        }
    }

}