package org.example.view.inscripcion;

import org.example.controller.EstudianteController;
import org.example.controller.InscripcionController;
import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Inscripcion;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;
import java.awt.*;

public class ActualizarInscripcion extends VentanaActualizar<Inscripcion> {
    private InscripcionController inscripcionController;
    private EstudianteController estudianteController;
    private JComboBox<Integer> comboSemestre;

    public ActualizarInscripcion(InscripcionController inscripcionController, EstudianteController estudianteController) {
        super("Actualizar Inscripción", new String[]{"ID Curso", "Año", "ID Estudiante"});
        this.inscripcionController = inscripcionController;
        this.estudianteController = estudianteController;

        JPanel panel = getFormularioPanel();
        comboSemestre = new JComboBox<>(new Integer[]{1, 2});

        Component actualizarButton = null;
        int posicionBoton = campos.length + 1;

        for (Component comp : panel.getComponents()) {
            GridBagConstraints gbc = ((GridBagLayout)panel.getLayout()).getConstraints(comp);
            if (gbc.gridy == posicionBoton) {
                actualizarButton = comp;
                panel.remove(comp);
                break;
            }
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = campos.length + 1;
        panel.add(new JLabel("Semestre:"), gbc);

        gbc.gridx = 1;
        panel.add(comboSemestre, gbc);

        if (actualizarButton != null) {
            gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = campos.length + 2; // Una fila después del combobox
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(actualizarButton, gbc);
        }

        panel.revalidate();
        panel.repaint();

        setSize(500, 300);
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText());
            Inscripcion inscripcion = inscripcionController.obtenerInscripcionPorId(id);
            if (inscripcion != null) {
                campos[0].setText(String.valueOf(inscripcion.getCurso().getID()));
                campos[1].setText(String.valueOf(inscripcion.getAño()));
                campos[2].setText(String.valueOf(inscripcion.getEstudiante().getID()));
                comboSemestre.setSelectedItem(inscripcion.getSemestre());
            } else {
                JOptionPane.showMessageDialog(this, "Inscripción no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void guardarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            int idCurso = Integer.parseInt(campos[0].getText().trim());
            int anio = Integer.parseInt(campos[1].getText().trim());
            int idEstudiante = Integer.parseInt(campos[2].getText().trim());
            int semestre = (Integer) comboSemestre.getSelectedItem();

            if (anio < 1974 || anio > 2100) {
                JOptionPane.showMessageDialog(this, "El año ingresado no es válido (debe ser entre 1974 y 2100).", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Estudiante estudianteExistente = estudianteController.obtenerEstudiantePorId(idEstudiante);
            if (estudianteExistente == null) {
                JOptionPane.showMessageDialog(this, "El ID del estudiante no corresponde a un estudiante registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Curso curso = new Curso(idCurso);
            Inscripcion inscripcion = new Inscripcion(id, curso, anio, semestre, estudianteExistente);
            inscripcionController.actualizarInscripcion(inscripcion);

            JOptionPane.showMessageDialog(this, "Inscripción actualizada exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifica que todos sean numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la inscripción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}