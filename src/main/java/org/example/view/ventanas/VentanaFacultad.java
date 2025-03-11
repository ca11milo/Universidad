/*package org.example.view.ventanas;

import org.example.controller.FacultadController;
import org.example.controller.PersonaController;
import org.example.view.facultad.*;

import javax.swing.*;
import java.awt.*;

public class VentanaFacultad  extends  VentanaContenido{
    private FacultadController facultadController;

    public VentanaFacultad(FacultadController facultadController, JTabbedPane tabbedPane) {
        super("Gestión de Facultades", tabbedPane);
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
                nuevaPestaña = new GuardarFacultad(facultadController);
                break;
            case "Leer":
                nuevaPestaña = new LeerFacultad(facultadController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarFacultad(facultadController);
                break;
            case "Borrar":
                nuevaPestaña = new BorrarFacultad(facultadController);
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
*/