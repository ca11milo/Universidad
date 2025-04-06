package org.example.view.estudiante;

import org.example.controller.EstudianteController;
import org.example.controller.ProgramaController;
import org.example.model.Estudiante;
import org.example.model.Programa;
import org.example.model.factory.PersonaFactory;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;
import java.awt.*;

public class GuardarEstudiante extends VentanaGuardar<Estudiante> {
    private EstudianteController estudianteController;
    private ProgramaController programaController;
    private JCheckBox activoCheckBox;

    public GuardarEstudiante(EstudianteController estudianteController, ProgramaController programaController) {
        super("Registrar Estudiante", new String[]{"Nombre", "Apellido", "Email", "Codigo", "ID Programa", "Activo", "Promedio"});
        this.estudianteController = estudianteController;
        this.programaController = programaController;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        formularioPanel.remove(campos[5]);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formularioPanel.add(new JLabel("Activo:"), gbc);

        gbc.gridx = 1;
        activoCheckBox = new JCheckBox();
        formularioPanel.add(activoCheckBox, gbc);

        formularioPanel.revalidate();
        formularioPanel.repaint();
    }

    @Override
    protected void guardarEntidad() {
        try {
            String nombre = campos[0].getText().trim();
            String apellido = campos[1].getText().trim();
            String email = campos[2].getText().trim();
            String codigoTexto = campos[3].getText().trim();
            String idProgramaTexto = campos[4].getText().trim();
            String promedioTexto = campos[6].getText().trim();
            boolean activo = activoCheckBox.isSelected();

            if (nombre.isBlank() || apellido.isBlank() || email.isBlank() ||
                    codigoTexto.isBlank() || idProgramaTexto.isBlank() || promedioTexto.isBlank()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Double codigo = Double.parseDouble(codigoTexto);
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

            Estudiante estudiante = PersonaFactory.crearEstudiante(0, nombre, apellido, email, codigo, programa, activo, promedio);
            estudianteController.guardarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante guardado exitosamente.");
            limpiarCampos();
            activoCheckBox.setSelected(false);


        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código, ID del programa y promedio deben ser valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el estudiante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
