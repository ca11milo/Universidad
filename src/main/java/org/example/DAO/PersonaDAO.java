package org.example.DAO;


import org.example.model.Estudiante;
import org.example.model.Persona;
import org.example.model.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonaDAO{

    private Connection conexion;

    public PersonaDAO(Connection connexion) {
        this.conexion = connexion;
    }

    public void guardarPersona(Persona persona) {

        if(persona instanceof Estudiante) {
            guardarEstudiante((Estudiante) persona);
            return;
        } else if (persona instanceof Profesor) {
            guardarProfesor((Profesor) persona);
            return;
        }

        String queryBase = "INSERT INTO PERSONA (id_persona, nombre, apellidos, email, tipo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement insertPersonaStatement = conexion.prepareStatement(queryBase)) {
            insertPersonaStatement.setDouble(1, persona.getID());
            insertPersonaStatement.setString(2, persona.getNombres());
            insertPersonaStatement.setString(3, persona.getApellidos());
            insertPersonaStatement.setString(4, persona.getEmail());
            insertPersonaStatement.setString(5, "PERSONA");

            int filasAfectadas = insertPersonaStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Persona guardada correctamente, ID: " + persona.getID());
            }
        } catch (SQLException exception) {
            System.err.println("Error al guardar persona: " + exception.getMessage());
        }
    }

    public void guardarEstudiante(Estudiante estudiante) {
        String SQLquery = "INSERT INTO PERSONA (id_persona, nombre, apellidos, email, tipo, codigo, id_programa, activo, promedio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertEstudianteStatement = conexion.prepareStatement(SQLquery)) {
            insertEstudianteStatement.setDouble(1, estudiante.getID());
            insertEstudianteStatement.setString(2, estudiante.getNombres());
            insertEstudianteStatement.setString(3, estudiante.getApellidos());
            insertEstudianteStatement.setString(4, estudiante.getEmail());
            insertEstudianteStatement.setString(5, "ESTUDIANTE");
            insertEstudianteStatement.setDouble(6, estudiante.getCodigo());
            insertEstudianteStatement.setDouble(7, estudiante.getPrograma().getID());
            insertEstudianteStatement.setBoolean(8, estudiante.getActivo());
            insertEstudianteStatement.setDouble(9, estudiante.getPromedio());

            int filasAfectadas = insertEstudianteStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Persona guardada correctamente, ID: " + estudiante.getID());
            }
        } catch (SQLException exception) {
            System.err.println("Error al guardar persona: " + exception.getMessage());
        }
    }

    public void guardarProfesor(Profesor profesor) {
        String SQLquery = "INSERT INTO PERSONA (id_persona, nombre, apellidos, email, tipo, tipo_contrato) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertProfesorStatement = conexion.prepareStatement(SQLquery)) {
            insertProfesorStatement.setDouble(1, profesor.getID());
            insertProfesorStatement.setString(2, profesor.getNombres());
            insertProfesorStatement.setString(3, profesor.getApellidos());
            insertProfesorStatement.setString(4, profesor.getEmail());
            insertProfesorStatement.setString(5, "PROFESOR");
            insertProfesorStatement.setString(6, profesor.getTipoContrato());

            int filasAfectadas = insertProfesorStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Persona guardada correctamente, ID: " + profesor.getID());
            }
        } catch (SQLException exception) {
            System.err.println("Error al guardar persona: " + exception.getMessage());
        }
    }

}
