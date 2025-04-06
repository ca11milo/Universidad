package org.example;

import org.example.controller.*;
import org.example.dao.*;
import org.example.config.DatabaseConnection;
import org.example.service.*;
import org.example.view.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conexion = DatabaseConnection.getInstance().getConnection();

            CursoDAO cursoDAO = new CursoDAO(conexion);
            CursoProfesorDAO cursoProfesorDAO = new CursoProfesorDAO(conexion);
            EstudianteDAO estudianteDAO = new EstudianteDAO(conexion);
            FacultadDAO facultadDAO = new FacultadDAO(conexion);
            InscripcionDAO inscripcionDAO = new InscripcionDAO(conexion);
            PersonaDAO personaDAO = new PersonaDAO(conexion);
            ProfesorDAO profesorDAO = new ProfesorDAO(conexion);
            ProgramaDAO programaDAO = new ProgramaDAO(conexion);

            facultadDAO.setPersonaService(new PersonaService(personaDAO));

            SwingUtilities.invokeLater(() -> {
                PersonaService personaService = new PersonaService(personaDAO);
                ProfesorService profesorService = new ProfesorService(profesorDAO);
                FacultadService facultadService = new FacultadService(facultadDAO, personaService);
                ProgramaService programaService = new ProgramaService(programaDAO, facultadService);
                CursoService cursoService = new CursoService(cursoDAO, programaService);
                CursoProfesorService cursoProfesorService = new CursoProfesorService(cursoProfesorDAO, profesorService, cursoService);
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

                Map<String, Object> controladores = new HashMap<>();
                controladores.put("persona", personaController);
                controladores.put("profesor", profesorController);
                controladores.put("facultad", facultadController);
                controladores.put("curso", cursoController);
                controladores.put("cursoProfesor", cursoProfesorController);
                controladores.put("inscripcion", inscripcionController);
                controladores.put("estudiante", estudianteController);
                controladores.put("programa", programaController);

                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(controladores);
                ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventanaPrincipal.setSize(800, 600);
                ventanaPrincipal.setLocationRelativeTo(null);

                JLabel bienvenida = new JLabel("Bienvenido al Sistema de Gestión de la Universidad", SwingConstants.CENTER);
                bienvenida.setFont(new Font("Arial", Font.BOLD, 18));
                ventanaPrincipal.add(bienvenida, BorderLayout.CENTER);

                ventanaPrincipal.setVisible(true);

            });

        } catch (SQLException exception) {
            System.out.println("Error al conectar con la base de datos: " + exception.getMessage());
        }
    }
}
