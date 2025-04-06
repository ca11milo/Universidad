package org.example.view.facultad;

import org.example.controller.FacultadController;
import org.example.controller.PersonaController;
import org.example.model.Facultad;
import org.example.model.Persona;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;

public class GuardarFacultad extends VentanaGuardar<Facultad> {
    private FacultadController facultadController;
    private PersonaController personaController;


    public GuardarFacultad(FacultadController facultadController, PersonaController personaController) {
        super("Guardar Facultad", new String[]{"Nombre", "ID Decano"});
        this.facultadController = facultadController;
        this.personaController = personaController;
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            String idText = campos[0].getText();
            String nombre = campos[1].getText();
            String idDecanoText = campos[2].getText();

            if (idText.isBlank() || nombre.isBlank() || idDecanoText.isBlank()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idText);
            int idDecano = Integer.parseInt(idDecanoText);

            if (id <= 0 || idDecano <= 0) {
                JOptionPane.showMessageDialog(this, "El ID y el ID del decano deben ser números positivos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Persona decano = personaController.obtenerPersonaPorId(idDecano);
            if (decano == null) {
                JOptionPane.showMessageDialog(this, "El ID del decano no corresponde a ninguna persona registrada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Facultad facultad = new Facultad(id, nombre, decano);
            facultadController.guardarFacultad(facultad);

            JOptionPane.showMessageDialog(this, "Facultad guardada exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID y el ID del decano deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la facultad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}