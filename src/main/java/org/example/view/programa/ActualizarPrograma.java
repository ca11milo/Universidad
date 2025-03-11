package org.example.view.programa;

import org.example.controller.ProgramaController;
import org.example.model.Facultad;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActualizarPrograma extends VentanaActualizar<Programa> {
    private ProgramaController programaController;
    private JFormattedTextField fechaRegistroField;

    public ActualizarPrograma(ProgramaController programaController) {
        super("Actualizar Programa", new String[]{"Nombre", "Duración", "Fecha Registro", "ID Facultad"});
        this.programaController = programaController;

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
            int id = Integer.parseInt(idField.getText());
            String nombre = campos[0].getText();
            double duracion = Double.parseDouble(campos[1].getText());
            Date registro = new SimpleDateFormat("yyyy-MM-dd").parse(fechaRegistroField.getText());
            int idFacultad = Integer.parseInt(campos[3].getText());

            Programa programa = new Programa(id, nombre, registro, duracion, new Facultad(idFacultad));
            programaController.actualizarPrograma(programa);

            JOptionPane.showMessageDialog(this, "Programa actualizado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el programa.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
