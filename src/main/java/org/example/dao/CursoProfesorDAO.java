package org.example.dao;

import org.example.model.Curso;
import org.example.model.CursoProfesor;
import org.example.model.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoProfesorDAO {
    private Connection conexion;

    public CursoProfesorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarCursoProfesor(CursoProfesor cursoProfesor) {
        String query = "INSERT INTO CURSO_PROFESOR (id_profesor, anio, semestre, id_curso) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, cursoProfesor.getProfesor().getID());
            statement.setInt(2, cursoProfesor.getAño());
            statement.setInt(3, cursoProfesor.getSemestre());
            statement.setInt(4, cursoProfesor.getCurso().getID());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cursoProfesor.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException exception) {
            System.out.println("Error al guardar el profesor: " + exception.getMessage());
        }
    }

    public Optional<CursoProfesor> obtenerCursoProfesorPorId(int id) {
        String query = "SELECT * FROM CURSO_PROFESOR WHERE id_curso_profesor = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new CursoProfesor(
                        resultSet.getInt("id_curso_profesor"),
                        new Profesor(resultSet.getInt("id_profesor")),
                        resultSet.getInt("anio"),
                        resultSet.getInt("semestre"),
                        new Curso(resultSet.getInt("id_curso"))
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error al obtener el profesor: " + exception.getMessage());
        }
        return Optional.empty();
    }

    public List<CursoProfesor> obtenerCursosProfesoresPorId() {
        List<CursoProfesor> listaCursoProfesor = new ArrayList<>();
        String query = "SELECT * FROM CURSO_PROFESOR";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                listaCursoProfesor.add(new CursoProfesor(
                        resultSet.getInt("id_curso_profesor"),
                        new Profesor(resultSet.getInt("id_curso")),
                        resultSet.getInt("semestre"),
                        resultSet.getInt("anio"),
                        new Curso(resultSet.getInt("id_curso"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cursos_profesor: " + e.getMessage());
        }
        return listaCursoProfesor;
    }

    public boolean actualizarCursoProfesor(CursoProfesor cursoProfesor) {
        String query = "UPDATE CURSO_PROFESOR SET id_profesor = ?, anio = ?, semestre = ?, id_curso = ? WHERE id_curso_profesor = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, cursoProfesor.getProfesor().getID());
            statement.setInt(2, cursoProfesor.getAño());
            statement.setInt(3, cursoProfesor.getSemestre());
            statement.setInt(4, cursoProfesor.getCurso().getID());
            statement.setInt(5, cursoProfesor.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar curso_profesor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCursoProfesor(int id) {
        String query = "DELETE FROM CURSO_PROFESOR WHERE id_curso_profesor = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar curso_profesor: " + e.getMessage());
            return false;
        }
    }
}
