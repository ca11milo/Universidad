package org.example.dao;

import org.example.model.Curso;
import org.example.model.Programa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private final Connection conexion;

    public CursoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarCurso(Curso curso) {
        String query = "INSERT INTO CURSO (nombre, activo, id_programa) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNombre());
            stmt.setBoolean(2, curso.isActivo());
            stmt.setInt(3, curso.getPrograma().getID());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        curso.setID(keys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar Curso: " + e.getMessage());
        }
    }

    public Curso obtenerCursoPorId(int id) {
        String sql = "SELECT * FROM CURSO WHERE id_curso = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                        rs.getInt("id_curso"),
                        rs.getString("nombre"),
                        new Programa(rs.getInt("id_programa")),
                        rs.getBoolean("activo")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener curso: " + e.getMessage());
        }
        return null;
    }

    public List<Curso> obtenerCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM CURSO";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getInt("id_curso"),
                        rs.getString("nombre"),
                        new Programa(rs.getInt("id_programa")),
                        rs.getBoolean("activo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cursos: " + e.getMessage());
        }
        return cursos;
    }
}
