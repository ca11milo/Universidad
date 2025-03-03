
package org.example.dao;

import org.example.model.Inscripcion;
import java.sql.*;

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
}
