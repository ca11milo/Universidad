package org.example.view.facultad;

import org.example.controller.FacultadController;
import org.example.controller.PersonaController;
import org.example.model.Facultad;
import org.example.model.Persona;
import org.example.view.ventanasCRUD.VentanaActualizar;
import javax.swing.*;

public class ActualizarFacultad extends VentanaActualizar<Facultad> {
    private FacultadController facultadController;
    private PersonaController personaController;

    public ActualizarFacultad(FacultadController facultadController, PersonaController personaController) {
        super("Actualizar Facultad", new String[]{"Nombre", "ID Decano"});
        this.facultadController = facultadController;
        this.personaController = personaController;
    }

    @Override
    protected void guardarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String nombre = campos[0].getText().trim();
            String idDecanoTexto = campos[1].getText().trim();

            if (nombre.isBlank() || idDecanoTexto.isBlank()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idDecano = Integer.parseInt(idDecanoTexto);

            Persona decanoExistente = personaController.obtenerPersonaPorId(idDecano);
            if (decanoExistente == null) {
                JOptionPane.showMessageDialog(this, "El ID del decano no corresponde a una persona existente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Facultad facultad = new Facultad(id, nombre, decanoExistente);
            facultadController.actualizarFacultad(facultad);
            JOptionPane.showMessageDialog(this, "Facultad actualizada exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID e ID del Decano deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la facultad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    protected void cargarEntidad() {
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo ID no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Facultad facultad = facultadController.obtenerFacultadPorId(id);
            if (facultad != null) {
                if (facultad.getNombre() == null || facultad.getDecano() == null) {
                    JOptionPane.showMessageDialog(this, "La información de la facultad está incompleta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                campos[0].setText(facultad.getNombre());
                campos[1].setText(String.valueOf(facultad.getDecano().getID()));
            } else {
                JOptionPane.showMessageDialog(this, "Facultad no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al cargar la facultad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}