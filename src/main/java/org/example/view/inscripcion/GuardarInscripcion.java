package org.example.view.inscripcion;

import org.example.controller.EstudianteController;
import org.example.controller.InscripcionController;
import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Inscripcion;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;
import java.awt.*;

public class GuardarInscripcion extends VentanaGuardar<Inscripcion> {
    private InscripcionController inscripcionController;
    private EstudianteController estudianteController;
    private JComboBox<Integer> comboSemestre;

    public GuardarInscripcion(InscripcionController inscripcionController, EstudianteController estudianteController) {
        super("Registrar Inscripción", new String[]{"ID Curso", "Año", "ID Estudiante"});
        this.inscripcionController = inscripcionController;
        this.estudianteController = estudianteController;

        JPanel panel = getFormularioPanel();

        comboSemestre = new JComboBox<>(new Integer[]{1, 2});

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Semestre:"), gbc);

        gbc.gridx = 1;
        panel.add(comboSemestre, gbc);

        Component[] components = panel.getComponents();
        for (Component c : components) {
            if (c instanceof JButton) {
                GridBagConstraints existingGbc = ((GridBagLayout)panel.getLayout()).getConstraints(c);
                if (existingGbc.gridy == 3) {
                    panel.remove(c);
                    existingGbc.gridy = 4;
                    panel.add(c, existingGbc);
                    break;
                }
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    @Override
    protected void guardarEntidad() {
        try {
            String idCursoTexto = campos[0].getText().trim();
            String anioTexto = campos[1].getText().trim();
            String idEstudianteTexto = campos[2].getText().trim();

            if (idCursoTexto.isEmpty() || anioTexto.isEmpty() || idEstudianteTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idCurso = Integer.parseInt(idCursoTexto);
            int anio = Integer.parseInt(anioTexto);
            int semestre = (Integer) comboSemestre.getSelectedItem();
            int idEstudiante = Integer.parseInt(idEstudianteTexto);

            if (anio < 1974 || anio > 2100) {
                JOptionPane.showMessageDialog(this, "El año ingresado no es válido (debe ser entre 1974 y 2100).", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Estudiante estudiante = estudianteController.obtenerEstudiantePorId(idEstudiante);
            if (estudiante == null) {
                JOptionPane.showMessageDialog(this, "No existe un estudiante con el ID ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Curso curso = new Curso(idCurso);
            Inscripcion inscripcion = new Inscripcion(0, curso, anio, semestre, estudiante);

            inscripcionController.guardarInscripcion(inscripcion);
            JOptionPane.showMessageDialog(this, "Inscripción guardada exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifica que todos los valores numéricos sean válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la inscripción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}