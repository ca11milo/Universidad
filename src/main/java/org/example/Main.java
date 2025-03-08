package org.example;

import org.example.controller.*;
import org.example.dao.*;
import org.example.config.DatabaseConnection;
import org.example.model.*;
import org.example.service.*;
import org.example.view.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conexion = DatabaseConnection.getConnection();

            CursoDAO cursoDAO = new CursoDAO(conexion);
            CursoProfesorDAO cursoProfesorDAO = new CursoProfesorDAO(conexion);
            EstudianteDAO estudianteDAO = new EstudianteDAO(conexion);
            FacultadDAO facultadDAO = new FacultadDAO(conexion);
            InscripcionDAO inscripcionDAO = new InscripcionDAO(conexion);
            PersonaDAO personaDAO = new PersonaDAO(conexion);
            ProfesorDAO profesorDAO = new ProfesorDAO(conexion);
            ProgramaDAO programaDAO = new ProgramaDAO(conexion);

            SwingUtilities.invokeLater(() -> {
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

                Map<String, Object> controladores = new HashMap<>();
                controladores.put("persona", personaController);
                controladores.put("profesor", profesorController);
                controladores.put("facultad", facultadController);
                controladores.put("curso", cursoController);
                controladores.put("cursoProfesor", cursoProfesorController);
                controladores.put("inscripcion", inscripcionController);
                controladores.put("estudiante", estudianteController);
                controladores.put("programa", programaController);

                JFrame ventanaPrincipal = new JFrame("Sistema de Gestión");
                ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventanaPrincipal.setSize(800, 600);
                ventanaPrincipal.setLocationRelativeTo(null);

                JTabbedPane tabbedPane = new JTabbedPane();

                JPanel panelBotones = new JPanel(new GridLayout(2, 4, 10, 10));
                panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

                String[] nombres = {"Persona", "Profesor", "Facultad", "Curso", "Curso-Profesor", "Inscripción", "Estudiante", "Programa"};

                for (String nombre : nombres) {
                    JButton boton = new JButton("Abrir " + nombre);
                    boton.addActionListener(new BotonListener(nombre, tabbedPane, controladores));
                    panelBotones.add(boton);
                }

                ventanaPrincipal.add(panelBotones, BorderLayout.NORTH);
                ventanaPrincipal.add(tabbedPane, BorderLayout.CENTER);

                ventanaPrincipal.setVisible(true);
            });

        } catch (SQLException exception) {
            System.out.println("Error al conectar con la base de datos: " + exception.getMessage());
        }
    }
}
