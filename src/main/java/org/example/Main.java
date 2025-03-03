package org.example;

import org.example.dao.*;
import org.example.config.DatabaseConnection;
import org.example.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /**

        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        CursosProfesores cursosProfesores = new CursosProfesores();
        CursosInscritos cursosInscritos1 = new CursosInscritos();


        Persona persona1 = new Persona("Juan", "juan@example.com", "Burgos", 1.0);
        Persona decano = new Persona("Elvis", "elvis@gmail.com", "Perez", 123);

        Facultad fcbi = new Facultad(16000, "Ciencias Basicas e Ingenieria", decano);

        Programa sistemas = new Programa(1112, "ingenieria de sistemas", new Date(125, 1,23), 10, fcbi);

        Estudiante persona2 = new Estudiante("camilo", "camilo@example.com", "Londoño", 2,123, sistemas, 3.9, true );

        Curso tecnologiasAvanzadas = new Curso(1, "matematicas", sistemas, true);

        Inscripcion inscripcion1 = new Inscripcion(tecnologiasAvanzadas, 2025, 2, persona2);

        Profesor roger = new Profesor("Roger", "roger@gmail.com", "Calderon", 8765, "Planta");
        CursoProfesor cursoProfesor1 = new CursoProfesor(roger, 1, 2025, tecnologiasAvanzadas);

        cursosProfesores.inscribir(cursoProfesor1);
        cursosProfesores.imprimirListado();
        cursosProfesores.guardarInformacion(cursoProfesor1);

        cursosInscritos1.inscribirCurso(inscripcion1);
        cursosInscritos1.guardarInformacion(inscripcion1);


        inscripcionesPersonas.inscribir(persona1);
        inscripcionesPersonas.inscribir(persona2);
        
        Persona personaActualizada = new Persona("Cristian", "cristian@example.com", "rojas", 1);
        //inscripcionesPersonas.eliminar(persona2);
        inscripcionesPersonas.actualizar(personaActualizada);

        
        System.out.println("\nLista ya actualizada:");
        for (Persona p : inscripcionesPersonas.getListado()) {
            System.out.println(p);
        }

        inscripcionesPersonas.guardarInformacion(persona1);

    inscripcionesPersonas.cargarDatos();

        System.out.println("Lista:");
        for (Persona p : inscripcionesPersonas.getListado()) {
            System.out.println(p);
        }

    cursosProfesores.cargarDatos();
        cursosProfesores.imprimirListado();

    cursosInscritos1.cargarDatos();
    cursosInscritos1.imprimirListado();
         **/

    //CONEXION A LA BASE DE DATOS

        try(Connection conexion = DatabaseConnection.getConnection()) {
            PersonaDAO personaDAO = new PersonaDAO(conexion);
            FacultadDAO facultadDAO = new FacultadDAO(conexion);
            ProgramaDAO programaDAO = new ProgramaDAO(conexion);
            InscripcionDAO inscripcionDAO = new InscripcionDAO(conexion);
            CursoDAO cursoDAO = new CursoDAO(conexion);
            CursoProfesorDAO cursoProfesorDAO = new CursoProfesorDAO(conexion);


            Persona persona1 = new Persona("Juan", "Burgos", "juan@example.com" );
            Persona decano = new Persona("Elvis", "Perez", "elvis@gmail.com");

            Facultad fcbi = new Facultad("Ciencias Basicas e Ingenieria", decano);

            Programa sistemas = new Programa("ingenieria de sistemas", new Date(125, 1,23), 10, fcbi);

            Estudiante persona2 = new Estudiante("camilo",  "Londoño","camilo@example.com",123, sistemas, true,3.9);

            Curso tecnologiasAvanzadas = new Curso( "matematicas", sistemas, true);

            Inscripcion inscripcion1 = new Inscripcion(tecnologiasAvanzadas, 2025, 2, persona2);

            Profesor roger = new Profesor("Roger", "Calderon","roger@gmail.com","Planta");
            CursoProfesor cursoProfesor1 = new CursoProfesor(roger, 1, 2025, tecnologiasAvanzadas);

           personaDAO.guardarPersona(persona1);
           personaDAO.guardarPersona(decano);

            facultadDAO.guardarFacultad(fcbi);

            programaDAO.guardarPrograma(sistemas);

            personaDAO.guardarPersona(persona2);

            cursoDAO.guardarCurso(tecnologiasAvanzadas);

            inscripcionDAO.guardarInscripcion(inscripcion1);

            personaDAO.guardarPersona(roger);

            cursoProfesorDAO.guardarCursoProfesor(cursoProfesor1);

            List<Persona> listadoPersonas = personaDAO.obtenerPersonas();

            for(Persona persona : listadoPersonas) {
                System.out.println(persona);
            }
            

        }catch (SQLException exception){
            System.out.println("Error al conectar con la base de datos: "+ exception.getMessage());
        }
    }
}