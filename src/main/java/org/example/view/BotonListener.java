package org.example.view;

import org.example.controller.*;
import org.example.view.ventanas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class BotonListener implements ActionListener {
    private String nombre;
    private JTabbedPane tabbedPane;
    private Map<String, Object> controladores; // Mapa de controladores

    public BotonListener(String nombre, JTabbedPane tabbedPane, Map<String, Object> controladores) {
        this.nombre = nombre;
        this.tabbedPane = tabbedPane;
        this.controladores = controladores;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel nuevaVentana = crearVentana(nombre);
        tabbedPane.addTab(nombre, nuevaVentana);

        int index = tabbedPane.indexOfComponent(nuevaVentana);
        tabbedPane.setTabComponentAt(index, crearTituloConCerrar(nombre, nuevaVentana));
        tabbedPane.setSelectedComponent(nuevaVentana);
    }

    private JPanel crearVentana(String nombre) {
        switch (nombre) {
            case "Persona":
                return new VentanaPersona((PersonaController) controladores.get("persona"),tabbedPane);
            case "Curso":
                return new VentanaCurso((CursoController) controladores.get("curso"));
            case "Curso-Profesor":
                return new VentanaCursoProfesor((CursoProfesorController) controladores.get("cursoProfesor"));
            case "Profesor":
                return new VentanaProfesor((ProfesorController) controladores.get("profesor"), tabbedPane);
            case "Estudiante":
                return new VentanaEstudiante((EstudianteController) controladores.get("estudiante"));
            case "Facultad":
                return new VentanaFacultad((FacultadController) controladores.get("facultad"));
            case "Programa":
                return new VentanaPrograma((ProgramaController) controladores.get("programa"));
            case "Inscripción":
                return new VentanaInscripcion((InscripcionController) controladores.get("inscripcion"));
            default:
                return new VentanaContenido(nombre);
        }
    }

    private JPanel crearTituloConCerrar(String titulo, JPanel panel) {
        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        tabPanel.setOpaque(false);

        JLabel tituloLabel = new JLabel(titulo);
        JButton cerrarBoton = new JButton("X");
        cerrarBoton.setMargin(new Insets(2, 5, 2, 5));
        cerrarBoton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        cerrarBoton.setForeground(Color.RED);
        cerrarBoton.setFont(new Font("Arial", Font.BOLD, 12));
        cerrarBoton.setFocusPainted(false);
        cerrarBoton.setContentAreaFilled(false);

        cerrarBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cerrarBoton.setForeground(Color.WHITE);
                cerrarBoton.setBackground(Color.RED);
                cerrarBoton.setOpaque(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                cerrarBoton.setForeground(Color.RED);
                cerrarBoton.setOpaque(false);
            }
        });

        cerrarBoton.addActionListener(e -> {
            int index = tabbedPane.indexOfComponent(panel);
            if (index != -1) {
                tabbedPane.remove(index);
            }
        });

        tabPanel.add(tituloLabel);
        tabPanel.add(cerrarBoton);
        return tabPanel;
    }
}
