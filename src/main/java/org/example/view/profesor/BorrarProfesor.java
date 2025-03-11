package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.view.ventanasCRUD.VentanaBorrar;
import javax.swing.*;
import java.sql.SQLException;

public class BorrarProfesor extends VentanaBorrar {
    private ProfesorController profesorController;

    public BorrarProfesor(ProfesorController profesorController) {
        super("Profesor");
        this.profesorController = profesorController;
    }

    @Override
    protected void eliminar() throws SQLException {
        try {
            int id = Integer.parseInt(idField.getText());
            String mensajeEliminada = profesorController.eliminarProfesor(id);
            System.out.println(mensajeEliminada);
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
