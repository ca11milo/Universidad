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
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            boolean eliminado = cursoController.eliminarCurso(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Curso eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el curso. Es posible que esté relacionado con otros datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}