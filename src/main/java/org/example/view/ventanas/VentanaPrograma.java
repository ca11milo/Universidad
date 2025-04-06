package org.example.view.ventanas;

import org.example.controller.FacultadController;
import org.example.controller.ProgramaController;
import org.example.view.programa.*;

import javax.swing.*;
import java.awt.*;

public class VentanaPrograma extends VentanaContenido{
    private ProgramaController programaController;
    private FacultadController facultadController;

    public VentanaPrograma(ProgramaController programaController, FacultadController facultadController, JTabbedPane tabbedPane) {
        super("Gestión de Programas", tabbedPane);
        this.programaController = programaController;
        this.facultadController = facultadController;

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
                nuevaPestaña = new GuardarPrograma(programaController, facultadController);
                break;
            case "Leer":
                nuevaPestaña = new LeerPrograma(programaController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarPrograma(programaController, facultadController);
                break;
            case "Borrar":
                nuevaPestaña = new BorrarPrograma(programaController);
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
