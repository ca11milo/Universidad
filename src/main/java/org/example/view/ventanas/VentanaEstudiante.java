package org.example.view.ventanas;

import org.example.controller.EstudianteController;
import org.example.controller.ProgramaController;
import org.example.view.estudiante.*;

import javax.swing.*;
import java.awt.*;

public class VentanaEstudiante extends VentanaContenido{
    private EstudianteController estudianteController;
    private ProgramaController programaController;

    public VentanaEstudiante(EstudianteController estudianteController, ProgramaController programaController, JTabbedPane tabbedPane) {
        super("Gestión de Estudiantes", tabbedPane);
        this.estudianteController = estudianteController;
        this.programaController = programaController;

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
                nuevaPestaña = new GuardarEstudiante(estudianteController, programaController);
                break;
            case "Leer":
                nuevaPestaña = new LeerEstudiante(estudianteController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarEstudiante(estudianteController, programaController);
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
}
