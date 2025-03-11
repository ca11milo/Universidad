package org.example.view.ventanas;

import org.example.controller.CursoController;
import org.example.view.curso.*;

import javax.swing.*;
import java.awt.*;

public class VentanaCurso extends VentanaContenido {
    private CursoController cursoController;

    public VentanaCurso(CursoController cursoController, JTabbedPane tabbedPane) {
        super("Gestión de Curso", tabbedPane);
        this.cursoController = cursoController;

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
                nuevaPestaña = new GuardarCurso(cursoController);
                break;
            case "Leer":
                nuevaPestaña = new LeerCurso(cursoController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarCurso(cursoController);
                break;
            case "Borrar":
                nuevaPestaña = new BorrarCurso(cursoController);
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
