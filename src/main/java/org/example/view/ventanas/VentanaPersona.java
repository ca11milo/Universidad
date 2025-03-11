package org.example.view.ventanas;

import org.example.controller.PersonaController;
import org.example.view.persona.*;

import javax.swing.*;
import java.awt.*;

public class VentanaPersona extends VentanaContenido {
    private PersonaController personaController;

    public VentanaPersona(PersonaController personaController, JTabbedPane tabbedPane) {
        super("Gestión de Personas", tabbedPane);
        this.personaController = personaController;

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
                nuevaPestaña = new GuardarPersona(personaController);
                break;
            case "Leer":
                nuevaPestaña = new LeerPersona(personaController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarPersona(personaController);
                break;
            case "Borrar":
                nuevaPestaña = new BorrarPersona(personaController);
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
