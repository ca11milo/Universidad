package org.example.DAO;

import org.example.model.Inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscripcionDAO {

    private Connection conexion;
    public InscripcionDAO(Connection conexion) {
        this.conexion = conexion;
    }
    public void guardarInscripcion(Inscripcion inscripcion) {

        String queryBase = "INSERT INTO INSCRIPCION (id_curso, anio, semestre, id_estudiante) VALUES (?, ?, ?, ?)";

        try(PreparedStatement insertInscripcionStatement = conexion.prepareStatement(queryBase)) {
            insertInscripcionStatement.setInt(1, inscripcion.getCurso().getID());
            insertInscripcionStatement.setInt(2, inscripcion.getAño());
            insertInscripcionStatement.setInt(3, inscripcion.getSemestre());
            insertInscripcionStatement.setDouble(4, inscripcion.getEstudiante().getID());

            int filasAfectadas = insertInscripcionStatement.executeUpdate();
            if(filasAfectadas > 0) {
                System.out.println("Inscripcion guardada exitosamente");
            }
        }catch (SQLException exception) {
            System.out.println("Error al guardar inscripcion: " + exception.getMessage());
        }
    }
}
