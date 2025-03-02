package org.example.DAO;

import org.example.model.CursoProfesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CursoProfesorDAO {

    private Connection conexion;

    public CursoProfesorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarCursoProfesor(CursoProfesor cursoProfesor) {

        String queryBase = "INSERT INTO CURSO_PROFESOR (id_profesor, anio, semestre, id_curso) VALUES (?, ?, ?, ?)";
        try(PreparedStatement insertCursoProfesorStatement = conexion.prepareStatement(queryBase)) {
            insertCursoProfesorStatement.setDouble(1, cursoProfesor.getProfesor().getID());
            insertCursoProfesorStatement.setInt(2, cursoProfesor.getAño());
            insertCursoProfesorStatement.setInt(3, cursoProfesor.getSemestre());
            insertCursoProfesorStatement.setInt(4, cursoProfesor.getCurso().getID());

            int filasAfectadas = insertCursoProfesorStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Curso-Profesor guardado correctamente");
            }

        }catch (SQLException exception) {
            System.out.println("Error al guardar Curso-Profesor: " + exception.getMessage());
        }
    }

}
