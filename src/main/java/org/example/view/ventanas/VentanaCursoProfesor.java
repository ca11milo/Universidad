package org.example.view.ventanas;

import org.example.controller.CursoController;
import org.example.controller.CursoProfesorController;
import org.example.controller.ProfesorController;
import org.example.view.cursoProfesor.*;

import javax.swing.*;
import java.awt.*;


public class VentanaCursoProfesor extends VentanaContenido{
    private CursoProfesorController cursoProfesorController;
    private ProfesorController profesorController;
    private  CursoController cursoController;

    public VentanaCursoProfesor(CursoProfesorController cursoProfesorController, ProfesorController profesorController, CursoController cursoController, JTabbedPane tabbedPane) {
        super("Gestión de Personas", tabbedPane);
        this.cursoProfesorController = cursoProfesorController;
        this.profesorController = profesorController;
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
                nuevaPestaña = new GuardarCursoProfesor(cursoProfesorController, profesorController, cursoController);
                break;
            case "Leer":
                nuevaPestaña = new LeerCursoProfesor(cursoProfesorController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarCursoProfesor(cursoProfesorController, profesorController, cursoController);
                break;
            case "Borrar":
                nuevaPestaña = new BorrarCursoProfesor(cursoProfesorController);
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
