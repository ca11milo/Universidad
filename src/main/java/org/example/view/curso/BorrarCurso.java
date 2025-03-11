package org.example.view.curso;

import org.example.controller.CursoController;
import org.example.view.ventanasCRUD.VentanaBorrar;

import javax.swing.*;
import java.sql.SQLException;

public class BorrarCurso extends VentanaBorrar {
    private CursoController cursoController;

    public BorrarCurso(CursoController cursoController) {
        super("Curso");
        this.cursoController = cursoController;
    }

    @Override
    protected void eliminar() throws SQLException {
        try {
            int id = Integer.parseInt(idField.getText());
            String mensajeEliminada = cursoController.eliminarCurso(id);
            System.out.println(mensajeEliminada);
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}