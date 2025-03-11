package org.example.view.ventanas;

import org.example.controller.InscripcionController;
import org.example.view.inscripcion.ActualizarInscripcion;
import org.example.view.inscripcion.BorrarInscripcion;
import org.example.view.inscripcion.GuardarInscripcion;
import org.example.view.inscripcion.LeerInscripcion;

import javax.swing.*;
import java.awt.*;

public class VentanaInscripcion extends VentanaContenido{
    private InscripcionController inscripcionController;

    public VentanaInscripcion(InscripcionController inscripcionController, JTabbedPane tabbedPane) {
        super("Gestión de Inscipciones", tabbedPane);
        this.inscripcionController = inscripcionController;

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
                nuevaPestaña = new GuardarInscripcion(inscripcionController);
                break;
            case "Leer":
                nuevaPestaña = new LeerInscripcion(inscripcionController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarInscripcion(inscripcionController);
                break;
            case "Borrar":
                nuevaPestaña = new BorrarInscripcion(inscripcionController);
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
