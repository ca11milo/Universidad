package org.example.DAO;

import org.example.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CursoDAO {

    private Connection conexion;

    public CursoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarCurso(Curso curso) {
        String queryBase = "INSERT INTO CURSO (id_curso, nombre, activo, id_programa) VALUES (?, ?, ?, ?)";
        try(PreparedStatement insertCursoStatement = conexion.prepareStatement(queryBase)) {
            insertCursoStatement.setInt(1, curso.getID());
            insertCursoStatement.setString(2, curso.getNombre());
            insertCursoStatement.setBoolean(3, curso.isActivo());
            insertCursoStatement.setDouble(4, curso.getPrograma().getID());

            int filasAfectadas = insertCursoStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Curso guardado correctamente, ID: " + curso.getID());
            }
        }catch (SQLException exception) {
            System.out.println("Error al guardar Curso: " + exception.getMessage());
        }

    }
}
