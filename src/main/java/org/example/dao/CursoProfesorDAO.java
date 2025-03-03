package org.example.dao;

import org.example.model.Curso;
import org.example.model.CursoProfesor;
import org.example.model.Profesor;

import java.sql.*;
import java.util.Optional;

public class CursoProfesorDAO {
    private Connection conexion;

    public CursoProfesorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public int guardarCursoProfesor(CursoProfesor cursoProfesor) {
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
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    public Optional<CursoProfesor> obtenerCursoProfesorPorId(int id) {
        String query = "SELECT * FROM CURSO_PROFESOR WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new CursoProfesor(
                        resultSet.getInt("id"),
                        new Profesor(resultSet.getInt("id_profesor")),
                        resultSet.getInt("anio"),
                        resultSet.getInt("semestre"),
                        new Curso(resultSet.getInt("id_curso"))
                ));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }
}
