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
        super("Guardar Facultad", new String[]{ "Nombre", "ID Decano"});
        this.facultadController = facultadController;
        this.personaController = personaController;
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            String nombre = campos[0].getText();
            String idDecanoText = campos[1].getText();

            if (nombre.isBlank() || idDecanoText.isBlank()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idDecano = Integer.parseInt(idDecanoText);

            if (idDecano <= 0) {
                JOptionPane.showMessageDialog(this, "El ID del decano debe ser un números positivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Persona decano = personaController.obtenerPersonaPorId(idDecano);
            if (decano == null) {
                JOptionPane.showMessageDialog(this, "El ID del decano no corresponde a ninguna persona registrada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Facultad facultad = new Facultad(nombre, decano);
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