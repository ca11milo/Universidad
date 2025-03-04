package org.example;

import org.example.controller.*;
import org.example.dao.*;
import org.example.config.DatabaseConnection;
import org.example.model.*;
import org.example.service.*;
import org.example.view.VentanaPrincipal;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try(Connection conexion = DatabaseConnection.getConnection()) {

            CursoDAO cursoDAO = new CursoDAO(conexion);
            CursoProfesorDAO cursoProfesorDAO = new CursoProfesorDAO(conexion);
            EstudianteDAO estudianteDAO = new EstudianteDAO(conexion);
            FacultadDAO facultadDAO = new FacultadDAO(conexion);
            InscripcionDAO inscripcionDAO = new InscripcionDAO(conexion);
            PersonaDAO personaDAO = new PersonaDAO(conexion);
            ProfesorDAO profesorDAO = new ProfesorDAO(conexion);
            ProgramaDAO programaDAO = new ProgramaDAO(conexion);

            PersonaService personaService = new PersonaService(personaDAO);
            ProfesorService profesorService = new ProfesorService(profesorDAO);
            FacultadService facultadService = new FacultadService(facultadDAO, personaService);
            ProgramaService programaService = new ProgramaService(programaDAO, facultadService);
            CursoService cursoService = new CursoService(cursoDAO, programaService);
            CursoProfesorService cursoProfesorService = new CursoProfesorService(cursoProfesorDAO);
            EstudianteService estudianteService = new EstudianteService(estudianteDAO, programaService);
            InscripcionService inscripcionService = new InscripcionService(inscripcionDAO, cursoService, estudianteService);

            PersonaController personaController = new PersonaController(personaService);
            ProfesorController profesorController = new ProfesorController(profesorService);
            FacultadController facultadController = new FacultadController(facultadService);
            CursoController cursoController = new CursoController(cursoService);
            CursoProfesorController cursoProfesorController = new CursoProfesorController(cursoProfesorService);
            InscripcionController inscripcionController = new InscripcionController(inscripcionService);
            EstudianteController estudianteController = new EstudianteController(estudianteService);
            ProgramaController programaController = new ProgramaController(programaService);


            Persona persona1 = new Persona("Juan", "Burgos", "juan@example.com" );
            Persona decano = new Persona("Elvis", "Perez", "elvis@gmail.com");

            Facultad fcbi = new Facultad("Ciencias Basicas e Ingenieria", decano);

            Programa sistemas = new Programa("ingenieria de sistemas", new Date(125, 1,23), 10, fcbi);

            Estudiante persona2 = new Estudiante("camilo",  "Londoño","camilo@example.com",123, sistemas, true,3.9);

            Curso tecnologiasAvanzadas = new Curso( "matematicas", sistemas, true);

            Inscripcion inscripcion1 = new Inscripcion(tecnologiasAvanzadas, 2025, 2, persona2);

            Profesor roger = new Profesor("Roger", "Calderon","roger@gmail.com","Planta");
            CursoProfesor cursoProfesor1 = new CursoProfesor(roger, 1, 2025, tecnologiasAvanzadas);

           personaController.guardarPersona(persona1);
           personaController.guardarPersona(decano);

            facultadController.guardarFacultad(fcbi);

            programaController.guardarPrograma(sistemas);

            estudianteController.guardarEstudiante(persona2);

            cursoController.guardarCurso(tecnologiasAvanzadas);

            inscripcionController.guardarInscripcion(inscripcion1);

            profesorController.guardarProfesor(roger);

            cursoProfesorController.guardarCursoProfesor(cursoProfesor1);

            System.out.println(cursoProfesorController.eliminarCursoProfesor(cursoProfesor1.getId()));
            
            profesorController.actualizarProfesor(roger);

            List<Persona> listadoPersonas = personaController.obtenerListaPersonas();

            for(Persona persona : listadoPersonas) {
                System.out.println(persona);
            }

            SwingUtilities.invokeLater(() -> {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
            });

        }catch (SQLException exception){
            System.out.println("Error al conectar con la base de datos: "+ exception.getMessage());
        }
    }
}