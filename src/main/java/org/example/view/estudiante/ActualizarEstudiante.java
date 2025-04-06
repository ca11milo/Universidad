package org.example.view.estudiante;

import org.example.controller.ProgramaController;
import org.example.model.Programa;
import org.example.model.factory.PersonaFactory;
import org.example.view.ventanasCRUD.VentanaActualizar;
import org.example.controller.EstudianteController;
import org.example.model.Estudiante;

import javax.swing.*;
import java.awt.*;

public class ActualizarEstudiante extends VentanaActualizar<Estudiante> {
    private EstudianteController estudianteController;
    private ProgramaController programaController;
    private JCheckBox activoCheckBox;

    public ActualizarEstudiante(EstudianteController estudianteController, ProgramaController programaController) {
        super("Actualizar Estudiante", new String[]{
                "Nombre", "Apellido", "Email", "Codigo", "ID Programa", "Promedio"
        });

        this.estudianteController = estudianteController;
        this.programaController = programaController;

        Component actualizarButton = null;
        int filaActivo = campos.length + 1;

        for (Component comp : formularioPanel.getComponents()) {
            GridBagConstraints gbc = ((GridBagLayout)formularioPanel.getLayout()).getConstraints(comp);
            if (gbc.gridy == filaActivo) {
                actualizarButton = comp;
                formularioPanel.remove(comp);
                break;
            }
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = filaActivo;
        formularioPanel.add(new JLabel("Activo:"), gbc);

        gbc.gridx = 1;
        activoCheckBox = new JCheckBox();
        formularioPanel.add(activoCheckBox, gbc);

        if (actualizarButton != null) {
            gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = filaActivo + 1;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            formularioPanel.add(actualizarButton, gbc);
        }

        formularioPanel.revalidate();
        formularioPanel.repaint();
    }

    @Override
    protected void guardarEntidad() {
        try {
            String idTexto = idField.getText().trim();
            String nombre = campos[0].getText().trim();
            String apellido = campos[1].getText().trim();
            String email = campos[2].getText().trim();
            String codigoTexto = campos[3].getText().trim();
            String idProgramaTexto = campos[4].getText().trim();
            String promedioTexto = campos[5].getText().trim();
            boolean activo = activoCheckBox.isSelected();

            if (idTexto.isEmpty() || nombre.isBlank() || apellido.isBlank() || email.isBlank()
                    || codigoTexto.isEmpty() || idProgramaTexto.isEmpty() || promedioTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idTexto);
            double codigo = Double.parseDouble(codigoTexto);
            int idPrograma = Integer.parseInt(idProgramaTexto);
            double promedio = Double.parseDouble(promedioTexto);

            if (estudianteController.obtenerEstudiantePorCodigo(codigo) != null) {
                JOptionPane.showMessageDialog(this, "El Codigo ingresado ya pertenece a un estudiante. Ingrese otro", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Programa programa = programaController.obtenerProgramaPorId(idPrograma);
            if (programa == null) {
                JOptionPane.showMessageDialog(this, "El ID del programa no corresponde a ningún programa existente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Estudiante estudiante = PersonaFactory.crearEstudiante(id, nombre, apellido, email, codigo, programa, activo, promedio);
            estudianteController.actualizarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante actualizado exitosamente.");
            limpiarCampos();
            activoCheckBox.setSelected(false);
            idField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos en los campos correspondientes.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar estudiante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    protected void cargarEntidad() {
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Estudiante estudiante = estudianteController.obtenerEstudiantePorId(id);

            if (estudiante != null) {
                campos[0].setText(estudiante.getNombres());
                campos[1].setText(estudiante.getApellidos());
                campos[2].setText(estudiante.getEmail());
                campos[3].setText(String.valueOf(estudiante.getCodigo()));
                campos[4].setText(String.valueOf(estudiante.getPrograma().getID()));
                activoCheckBox.setSelected(estudiante.getActivo());
                campos[5].setText(String.valueOf(estudiante.getPromedio()));
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
