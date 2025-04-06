package org.example.view.programa;

import org.example.controller.FacultadController;
import org.example.controller.ProgramaController;
import org.example.model.Facultad;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActualizarPrograma extends VentanaActualizar<Programa> {
    private ProgramaController programaController;
    private JFormattedTextField fechaRegistroField;
    private FacultadController facultadController;

    public ActualizarPrograma(ProgramaController programaController, FacultadController facultadController) {
        super("Actualizar Programa", new String[]{"Nombre", "Duración", "Fecha Registro", "ID Facultad"});
        this.programaController = programaController;
        this.facultadController = facultadController;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fechaRegistroField = new JFormattedTextField(dateFormat);
        fechaRegistroField.setColumns(15);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        formularioPanel.remove(campos[2]);
        formularioPanel.add(fechaRegistroField, gbc);

        formularioPanel.revalidate();
        formularioPanel.repaint();
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText());
            Programa programa = programaController.obtenerProgramaPorId(id);

            if (programa != null) {
                campos[0].setText(programa.getNombre());
                campos[1].setText(String.valueOf(programa.getDuracion()));
                fechaRegistroField.setText(new SimpleDateFormat("yyyy-MM-dd").format(programa.getRegistro()));
                campos[3].setText(String.valueOf(programa.getFacultad().getID()));
            } else {
                JOptionPane.showMessageDialog(this, "Programa no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el programa.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String nombre = campos[0].getText().trim();
            String duracionTexto = campos[1].getText().trim();
            String fechaTexto = fechaRegistroField.getText().trim();
            String idFacultadTexto = campos[3].getText().trim();

            if (nombre.isEmpty() || duracionTexto.isEmpty() || fechaTexto.isEmpty() || idFacultadTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double duracion = Double.parseDouble(duracionTexto);
            Date registro = new SimpleDateFormat("yyyy-MM-dd").parse(fechaTexto);
            int idFacultad = Integer.parseInt(idFacultadTexto);

            Facultad facultad = facultadController.obtenerFacultadPorId(idFacultad);
            if (facultad == null) {
                JOptionPane.showMessageDialog(this, "No existe una facultad con el ID proporcionado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Programa programa = new Programa(id, nombre, registro, duracion, facultad);
            programaController.actualizarPrograma(programa);

            JOptionPane.showMessageDialog(this, "Programa actualizado exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos numéricos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "El formato de fecha debe ser yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el programa: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
