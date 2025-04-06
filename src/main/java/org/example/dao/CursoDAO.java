package org.example.dao;

import org.example.model.Curso;
import org.example.model.Programa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO {
    private final Connection conexion;

    public CursoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarCurso(Curso curso) {
        String query = "INSERT INTO CURSO (nombre, activo, id_programa) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, curso.getNombre());
            statement.setBoolean(2, curso.isActivo());
            statement.setInt(3, curso.getPrograma().getID());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        curso.setID(keys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar Curso: " + e.getMessage());
        }
    }

    public Optional<Curso> obtenerCursoPorId(int id) {
        String query = "SELECT * FROM CURSO WHERE id_curso = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of( new Curso(
                        resultSet.getInt("id_curso"),
                        resultSet.getString("nombre"),
                        new Programa(resultSet.getInt("id_programa")),
                        resultSet.getBoolean("activo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener curso: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Curso> obtenerListaCursos() {
        List<Curso> cursos = new ArrayList<>();
        String query = "SELECT * FROM CURSO";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                cursos.add(new Curso(
                        resultSet.getInt("id_curso"),
                        resultSet.getString("nombre"),
                        new Programa(resultSet.getInt("id_programa")),
                        resultSet.getBoolean("activo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cursos: " + e.getMessage());
        }
        return cursos;
    }

    public boolean actualizarCurso(Curso curso) {
        String query = "UPDATE CURSO SET nombre = ?, activo = ?, id_programa = ? WHERE id_curso = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, curso.getNombre());
            statement.setBoolean(2, curso.isActivo());
            statement.setInt(3, curso.getPrograma().getID());
            statement.setInt(4, curso.getID());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar curso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCurso(int id) {
        String query = "DELETE FROM CURSO WHERE id_curso = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.err.println("No se puede eliminar el curso: está relacionado con otros registros.");
            } else {
                System.err.println("Error al eliminar curso: " + e.getMessage());
            }
            return false;
        }
    }

}
