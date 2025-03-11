package org.example.view.facultad;

import org.example.controller.FacultadController;
import org.example.model.Facultad;
import org.example.model.Persona;
import org.example.view.ventanasCRUD.VentanaActualizar;
import javax.swing.*;

public class ActualizarFacultad extends VentanaActualizar<Facultad> {
    private FacultadController facultadController;

    public ActualizarFacultad(FacultadController facultadController) {
        super("Actualizar Facultad", new String[]{"Nombre", "ID Decano"});
        this.facultadController = facultadController;
    }

    @Override
    protected void guardarEntidad() throws Exception {
        int id = Integer.parseInt(idField.getText());
        String nombre = campos[0].getText();
        int idDecano = Integer.parseInt(campos[1].getText());

        Facultad facultad = new Facultad(id, nombre, new Persona(idDecano));
        facultadController.actualizarFacultad(facultad);
        JOptionPane.showMessageDialog(this, "Facultad actualizada exitosamente.");
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText());
            Facultad facultad = facultadController.obtenerFacultadPorId(id);
            if (facultad != null) {
                campos[0].setText(facultad.getNombre());
                campos[1].setText(String.valueOf(facultad.getDecano().getID()));
            } else {
                JOptionPane.showMessageDialog(this, "Facultad no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}