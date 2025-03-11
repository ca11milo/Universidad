package org.example.view.facultad;

import org.example.controller.FacultadController;
import org.example.model.Facultad;
import org.example.model.Persona;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;

public class GuardarFacultad extends VentanaGuardar<Facultad> {
    private FacultadController facultadController;

    public GuardarFacultad(FacultadController facultadController) {
        super("Guardar Facultad", new String[]{"Nombre", "ID Decano"});
        this.facultadController = facultadController;
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            int id = Integer.parseInt(campos[0].getText());
            String nombre = campos[1].getText();
            int idDecano = Integer.parseInt(campos[2].getText());

            Facultad facultad = new Facultad(id, nombre, new Persona(idDecano));
            facultadController.guardarFacultad(facultad);

            JOptionPane.showMessageDialog(this, "Facultad guardada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la facultad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}