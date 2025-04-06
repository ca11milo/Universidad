package org.example.view.cursoProfesor;

import org.example.controller.CursoProfesorController;
import org.example.controller.InscripcionController;
import org.example.view.ventanasCRUD.VentanaBorrar;

import javax.swing.*;
import java.sql.SQLException;

public class BorrarCursoProfesor extends VentanaBorrar {
    private CursoProfesorController cursoProfesorController;

    public BorrarCursoProfesor(CursoProfesorController cursoProfesorController) {
        super("Curso Profesor");
        this.cursoProfesorController = cursoProfesorController;
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
            boolean eliminado = cursoProfesorController.eliminarCursoProfesor(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Curso Profesor eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el CursoProfesor. Es posible que esté relacionado con otros datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}