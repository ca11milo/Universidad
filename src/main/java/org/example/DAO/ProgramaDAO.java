package org.example.DAO;

import org.example.model.Programa;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProgramaDAO {

    private Connection conexion;
    public ProgramaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarPrograma(Programa programa) {

        String queryBase = "INSERT INTO PROGRAMA (id_programa, nombre, duracion, registro, id_facultad) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement insertProgramaStatement = conexion.prepareStatement(queryBase)) {
            insertProgramaStatement.setDouble(1, programa.getID());
            insertProgramaStatement.setString(2, programa.getNombre());
            insertProgramaStatement.setDouble(3, programa.getDuracion());

            java.sql.Date registro = new java.sql.Date(programa.getRegistro().getTime());
            insertProgramaStatement.setDate(4, registro);

            insertProgramaStatement.setDouble(5, programa.getFacultad().getID());

            int filasAfectadas = insertProgramaStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Programa guardado correctamente, ID: " + programa.getID());
            }
        } catch (Exception exception) {
            System.out.println("Error al guardar programa: " + exception.getMessage());
        }


    }
}
