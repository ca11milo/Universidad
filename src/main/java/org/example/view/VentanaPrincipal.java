package org.example.view;

import org.example.view.factory.VentanaFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class VentanaPrincipal extends JFrame {
    private Map<String, Object> controladores;

    public VentanaPrincipal(Map<String, Object> controladores) {
        this.controladores = controladores;

        setTitle("Universidad - Gestión de Elementos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        String[] entidades = {
                "Persona", "Estudiante", "Profesor", "Facultad",
                "Programa", "Curso", "Inscripción", "Curso-Profesor"
        };

        for (String entidad : entidades) {
            JMenu menuEntidad = new JMenu(entidad);

            menuEntidad.add(crearItemMenu("Crear", entidad));
            menuEntidad.add(crearItemMenu("Actualizar", entidad));
            menuEntidad.add(crearItemMenu("Listar", entidad));
            menuEntidad.add(crearItemMenu("Eliminar", entidad));

            menuBar.add(menuEntidad);
        }

        setJMenuBar(menuBar);

        JLabel bienvenida = new JLabel("Bienvenido al Sistema de Gestión de Universidad", SwingConstants.CENTER);
        bienvenida.setFont(new Font("Arial", Font.BOLD, 18));
        add(bienvenida, BorderLayout.CENTER);
    }

    private JMenuItem crearItemMenu(String accion, String entidad) {
        JMenuItem item = new JMenuItem(accion);
        item.addActionListener((ActionEvent e) -> {
            abrirVentanaCrud(accion, entidad);
        });
        return item;
    }

    private void abrirVentanaCrud(String accion, String entidad) {
        JFrame ventanaCrud = new JFrame(accion + " " + entidad);
        ventanaCrud.setSize(600, 500);
        ventanaCrud.setLocationRelativeTo(this);
        ventanaCrud.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formulario = VentanaFactory.crearFormulario(accion, entidad, controladores);
        if (formulario != null) {
            ventanaCrud.add(formulario);
            ventanaCrud.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Formulario no disponible para " + accion + " " + entidad,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
