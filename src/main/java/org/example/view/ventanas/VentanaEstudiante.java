/*package org.example.view.ventanas;

import org.example.controller.EstudianteController;
import org.example.view.estudiante.*;

import javax.swing.*;
import java.awt.*;

public class VentanaEstudiante extends VentanaContenido{
    private EstudianteController estudianteController;

    public VentanaEstudiante(EstudianteController estudianteController, JTabbedPane tabbedPane) {
        super("Gestión de Estudiantes", tabbedPane);
        this.estudianteController = estudianteController;

        JPanel botonesPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        agregarBoton("Crear", botonesPanel);
        agregarBoton("Leer", botonesPanel);
        agregarBoton("Actualizar", botonesPanel);
        agregarBoton("Borrar", botonesPanel);

        add(botonesPanel, BorderLayout.CENTER);
    }

    @Override
    protected void abrirPestaña(String accion) {
        JPanel nuevaPestaña;
        switch (accion) {
            case "Crear":
                nuevaPestaña = new GuardarEstudiante(estudianteController);
                break;
            case "Leer":
                nuevaPestaña = new LeerEstudiante(estudianteController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarEstudiante(estudianteController);
                break;
            case "Borrar":
                nuevaPestaña = new BorrarEstudiante(estudianteController);
                break;
            default:
                return;
        }

        tabbedPane.addTab(accion, nuevaPestaña);
        int index = tabbedPane.indexOfComponent(nuevaPestaña);
        tabbedPane.setTabComponentAt(index, crearTituloConCerrar(accion, nuevaPestaña));
        tabbedPane.setSelectedComponent(nuevaPestaña);
    }
}*/
