package org.example.view.programa;

import org.example.controller.FacultadController;
import org.example.controller.ProgramaController;
import org.example.model.Facultad;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuardarPrograma extends VentanaGuardar<Programa> {
    private ProgramaController programaController;
    private JFormattedTextField fechaRegistroField;
    private FacultadController facultadController;

    public GuardarPrograma(ProgramaController programaController, FacultadController facultadController) {
        super("Registrar Programa", new String[]{"Nombre", "Duración", "Fecha Registro", "ID Facultad"});
        this.programaController = programaController;
        this.facultadController = facultadController;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fechaRegistroField = new JFormattedTextField(dateFormat);
        fechaRegistroField.setColumns(15);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        formularioPanel.remove(campos[2]);
        formularioPanel.add(fechaRegistroField, gbc);

        formularioPanel.revalidate();
        formularioPanel.repaint();
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            String nombre = campos[0].getText().trim();
            String duracionTexto = campos[1].getText().trim();
            String fechaTexto = fechaRegistroField.getText().trim();
            String idFacultadTexto = campos[3].getText().trim();

            if (nombre.isEmpty() || duracionTexto.isEmpty() || fechaTexto.isEmpty() || idFacultadTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double duracion = Double.parseDouble(duracionTexto);
            if (duracion <= 0) {
                JOptionPane.showMessageDialog(this, "La duración debe ser mayor a 0.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Date fechaRegistro = new SimpleDateFormat("yyyy-MM-dd").parse(fechaTexto);
            int idFacultad = Integer.parseInt(idFacultadTexto);

            Facultad facultad = facultadController.obtenerFacultadPorId(idFacultad);
            if (facultad == null) {
                JOptionPane.showMessageDialog(this, "No existe una facultad con el ID proporcionado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Programa programa = new Programa(nombre, fechaRegistro, duracion, facultad);
            programaController.guardarPrograma(programa);
            JOptionPane.showMessageDialog(this, "Programa guardado exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duración e ID de facultad deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Usa yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el programa: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
