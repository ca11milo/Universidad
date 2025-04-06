
package org.example.dao;

import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Inscripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InscripcionDAO {
    private Connection conexion;

    public InscripcionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarInscripcion(Inscripcion inscripcion) {
        String query = "INSERT INTO INSCRIPCION (id_curso, anio, semestre, id_estudiante) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, inscripcion.getCurso().getID());
            statement.setInt(2, inscripcion.getAño());
            statement.setInt(3, inscripcion.getSemestre());
            statement.setInt(4, inscripcion.getEstudiante().getID());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        inscripcion.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar inscripcion: " + e.getMessage());
        }
    }

    public Optional<Inscripcion> obtenerInscripcionPorId(int id) {
        String query = "SELECT * FROM INSCRIPCION WHERE id_inscripcion = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Inscripcion(
                        resultSet.getInt("id_inscripcion"),
                        new Curso(resultSet.getInt("id_curso")),
                        resultSet.getInt("anio"),
                        resultSet.getInt("semestre"),
                        new Estudiante(resultSet.getInt("id_estudiante"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener inscripción: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Inscripcion> obtenerListaInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String query = "SELECT * FROM INSCRIPCION";
        try (Statement statement = conexion.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                inscripciones.add(new Inscripcion(
                        resultSet.getInt("id_inscripcion"),
                        new Curso(resultSet.getInt("id_curso")),
                        resultSet.getInt("anio"),
                        resultSet.getInt("semestre"),
                        new Estudiante(resultSet.getInt("id_estudiante"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las inscripciones: " + e.getMessage());
        }
        return inscripciones;
    }

    public boolean actualizarInscripcion(Inscripcion inscripcion) {
        String query = "UPDATE INSCRIPCION SET id_curso = ?, anio = ?, semestre = ?, id_estudiante = ? WHERE id_inscripcion = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, inscripcion.getCurso().getID());
            statement.setInt(2, inscripcion.getAño());
            statement.setInt(3, inscripcion.getSemestre());
            statement.setInt(4, inscripcion.getEstudiante().getID());
            statement.setInt(5, inscripcion.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar inscripción: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarInscripcion(int id) {
        String query = "DELETE FROM INSCRIPCION WHERE id_inscripcion = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.err.println("No se puede eliminar la inscripcion: está relacionado con otros registros.");
            } else {
                System.err.println("Error al eliminar inscripcion: " + e.getMessage());
            }
            return false;
        }
    }
}
