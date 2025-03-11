package org.example.view.programa;

import org.example.controller.ProfesorController;
import org.example.controller.ProgramaController;
import org.example.view.ventanasCRUD.VentanaBorrar;
import javax.swing.*;
import java.sql.SQLException;

public class BorrarPrograma extends VentanaBorrar {
    private ProgramaController programaController;

    public BorrarPrograma(ProgramaController profesorController) {
        super("Programa");
        this.programaController = programaController;
    }

    @Override
    protected void eliminar() throws SQLException {
        try {
            int id = Integer.parseInt(idField.getText());
            String mensajeEliminada = programaController.eliminarPrograma(id);
            System.out.println(mensajeEliminada);
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
