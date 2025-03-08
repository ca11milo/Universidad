package org.example.view.ventanas;

import org.example.controller.ProfesorController;
import org.example.view.profesor.*;

import javax.swing.*;
import java.awt.*;

public class VentanaProfesor extends VentanaContenido {
    private ProfesorController profesorController;

    public VentanaProfesor(ProfesorController profesorController, JTabbedPane tabbedPane) {
        super("Gestión de Profesores", tabbedPane);
        this.profesorController = profesorController;

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
                nuevaPestaña = new GuardarProfesor(profesorController);
                break;
            case "Leer":
                nuevaPestaña = new TablaProfesor(profesorController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarProfesor(profesorController);
                break;
            case "Borrar":
                nuevaPestaña = new BorrarProfesor(profesorController);
                break;
            default:
                return;
        }

        tabbedPane.addTab(accion, nuevaPestaña);
        int index = tabbedPane.indexOfComponent(nuevaPestaña);
        tabbedPane.setTabComponentAt(index, crearTituloConCerrar(accion, nuevaPestaña));
        tabbedPane.setSelectedComponent(nuevaPestaña);
    }
}
